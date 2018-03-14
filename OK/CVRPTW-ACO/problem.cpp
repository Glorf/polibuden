#include "problem.h"


Client::Client(int *params) {
    mid=params[0];
    mx=params[1];
    my=params[2];
    mq=params[3];
    me=params[4];
    ml=params[5];
    md=params[6]; //NASZE S
}

//RETURNS: calculated path double
double Client::calculateFrom(int x, int y) {
    return sqrt(std::pow(x-mx, 2)+std::pow(y-my, 2));
}

int Client::getQuantity() {
    return mq;
}

int Client::getId() {
    return mid;
}

int Client::getX() {
    return mx;
}

int Client::getY() {
    return my;
}

double Client::getDispatchTime() {
    return md;
}

double Client::getEstimatedTime() {
    return (double)me;
}

int Client::getMaximalTime() {
    return ml;
}

bool Client::isVisited() {
    if(this->getId()==0)
        return false;
    else
        return visited;
}

void Client::setVisited(bool visited) {
    this->visited = visited;
}

void Client::setProbability(long double p) {
    this->probability = p;
}

long double Client::getProbability() {
    return probability;
}

double Client::getSzacunek() {
    return szacunek;
}

void Client::setSzacunek(double szac) {
    this->szacunek = szac;
}

bool greedyCmp(Client *c1, Client *c2)
{
    return c1->getSzacunek() < c2->getSzacunek();
}

Instance::Instance(std::string file, int initFeromon) {
    this->initFeromon = initFeromon;
    std::string dummy;
    std::ifstream in;
    in.open(file);
    if(!in.good())
    {
        std::cout<<"File not found!"<<std::endl;
        return;
    }
    in >> name >> dummy >> dummy >> dummy;
    in >> K;
    in >> Q;
    for(int i=0; i<12; i++)
        in >> dummy;

    while(!in.eof())
    {
        int params[7];
        for(int i=0; i<7; i++) {
            in >> params[i];
        }
        if(!in.eof()) {
            Client *client = new Client(params);
            addClient(client);
        }
    }

    in.close();

    distances = new double*[getSize()];
    for(int i=0; i<getSize(); i++) {
        distances[i] = new double[getSize()];
        for(int j=0; j<getSize(); j++)
            distances[i][j] = 0;
    }
}

void Instance::addClient(Client *client) {
    lista.push_back(client);
}

bool Instance::validate(std::string file) {
    bool correct = true;

    for(int i = 1; i<lista.size(); i++)
    {
        Client *client = lista.at(i);
        double d = client->calculateFrom(getDepot()->getX(), getDepot()->getY());
        double ret = 2*d+client->getDispatchTime()+std::max(client->getEstimatedTime()-d, (double)0);

        if(d>client->getMaximalTime() || Q<client->getQuantity() || ret>getDepot()->getMaximalTime())
        {
            correct = false;
            std::ofstream out;
            out.open(file);
            out<<"-1";
            out.close();
            return correct;
        }
    }

    return correct;
}

Client* Instance::getClient(int n) {
    return lista.at((unsigned long)n);
}

Client* Instance::getDepot() {
    return lista.at(0);
}

int Instance::getMaxQuantity() {
    return Q;
}

unsigned long Instance::getSize() {
    return lista.size();
}

double Instance::getFeromon(Client *current, Client *next) {
    int smallerId;
    int largerId;
    if(current->getId()>next->getId()) {
        smallerId = next->getId();
        largerId = current->getId();
    }
    else {
        smallerId = current->getId();
        largerId = next->getId();
    }

    return distances[largerId][smallerId];
}

void Instance::setFeromon(double f, Client *current, Client *next) {
    int smallerId;
    int largerId;
    if(current->getId()>next->getId()) {
        smallerId = next->getId();
        largerId = current->getId();
    }
    else {
        smallerId = current->getId();
        largerId = next->getId();
    }

    distances[largerId][smallerId] = f;
}

Solver::Solver(Instance *instance, float alpha, float beta, float ro) {
    mInstance = instance;
    this->alpha = alpha;
    this->beta = beta;
    this->ro = ro;
    totalTime = 0;
    remainingV = (instance->getSize()-1);
}

Solver::~Solver() {
    for(int i=0;i<resolution.size();i++) {
        delete(resolution.at(i));
    }
    resolution.clear();
}

bool cmp(Client *c1, Client *c2)
{
    return c1->getProbability() > c2->getProbability();
}

Client* Solver::getNext(Client *current, int q, double t) {
    long double totalProbability = 0;
    std::vector<Client*> possible;
    Client *ret = nullptr;
    for (int i = 1; i < mInstance->getSize(); i++) {
        Client *next = mInstance->getClient(i);
        double d = mInstance->calculateTo(current, next);
        if(next->isVisited() ||
           q+next->getQuantity()>mInstance->getMaxQuantity() ||
           t+d>next->getMaximalTime() ||
           d +mInstance->calculateTo(next, mInstance->getDepot()) + next->getDispatchTime() > mInstance->getDepot()->getMaximalTime() - t)
            continue;
        double nieatrakcyjnosc = d+d+std::max(t-next->getDispatchTime(), (double)0);
        long double licznikp = std::pow(mInstance->getFeromon(current, mInstance->getClient(i)),alpha)*
                            std::pow((double)1/nieatrakcyjnosc, beta);
        totalProbability+=licznikp;
        next->setProbability(licznikp);
        possible.push_back(next);
    }

    if(possible.empty())
        return nullptr;

    std::sort(possible.begin(), possible.end(), cmp);

    std::uniform_real_distribution<long double> unif(0, totalProbability);
    std::default_random_engine re;
    long double losow = unif(re);
    long double dystryb= 0;
    for (int i = 0; i < possible.size(); i++) {
        dystryb += possible.at(i)->getProbability();
        if(dystryb<losow)
            continue;
        ret = possible.at(i);
        break;
    }

    return ret;
}

double Solver::getTotalTime(){
    return totalTime;
}

std::vector<std::vector<Client*>*> Solver::getResolution(){
    return resolution;
}

double Instance::calculateTo(Client *current, Client *next) {
    /*
     * Z mniejszego do większego i - długość drogi; z większego do mniejszego i - wartość feromonu (jak u Radoma)
     */
    int smallerId;
    int largerId;
    if(current->getId()>next->getId()) {
        smallerId = next->getId();
        largerId = current->getId();
    }
    else {
        smallerId = current->getId();
        largerId = next->getId();
    }

    double val = distances[smallerId][largerId];

    if(distances[smallerId][largerId] == 0)
    {
        distances[largerId][smallerId] = initFeromon;
        double res = current->calculateFrom(next->getX(), next->getY());
        distances[smallerId][largerId] = res;
        return res;
    }
    else
        return val;

}

void Solver::solve() {
    Client *current = mInstance->getDepot();
    int q = 0;
    double t = 0;

    double prevd = 0;

    std::vector<Client*> *res = new std::vector<Client*>;
    resolution.push_back(res);

    while(current != nullptr)
    {
        current->setVisited(true);
        res->push_back(current);

        q += current->getQuantity();
        t += prevd;
        t += current->getDispatchTime() + std::max((double)0, current->getEstimatedTime()-t);

        Client *next = getNext(current, q, t);
        if(next!=nullptr) {
            prevd = mInstance->calculateTo(current, next);
            remainingV--;
        }
        else if(remainingV == 0) {
            totalTime+=t+mInstance->calculateTo(current,mInstance->getDepot());
            res->push_back(mInstance->getDepot());
            break;
        }
        else {
            next = mInstance->getDepot();
            totalTime+=t+mInstance->calculateTo(current,next);
            res->push_back(mInstance->getDepot());
            res = new std::vector<Client*>;
            resolution.push_back(res);
            q=0;
            t=0;
            prevd=0;
        }
        current = next;
    }

    /*
     * Tu paruje
     */
    for(int i=0; i<mInstance->getSize(); i++) {
        for(int j=0; j<mInstance->getSize(); j++) {
            double nf = (1-ro)*mInstance->getFeromon(mInstance->getClient(i), mInstance->getClient(j));
            mInstance->setFeromon(nf, mInstance->getClient(i), mInstance->getClient(j));
        }
    }

    /*
     * Tu mrówka składa nowe na fajnej trasie
     */
    for(int i=0; i<resolution.size(); i++) {
        std::vector<Client*> *resv = resolution.at(i);
        for(int j=0; j<resv->size()-1; j++) {
            Client *currentv = resv->at(j);
            Client *nextv = resv->at(j+1);
            currentv->setVisited(false);
            nextv->setVisited(false);
            mInstance->setFeromon(mInstance->getFeromon(current, nextv)+((double)1/totalTime), currentv, nextv);
        }
    }
}

void Solver::saveSolution(std::string file) {
    std::ofstream out;
    out.open(file);
    out<<resolution.size()<<" "<<std::fixed<<std::setprecision(5)<<totalTime<<std::endl;
    for(int i=0; i<resolution.size(); i++)
    {
        std::vector<Client*> *res = resolution.at(i);
        for(int j=1; j<res->size()-1; j++)
        {
            //std::cout<<res->at(j)->getId()<<" ";
            out<<res->at(j)->getId()<<" ";
        }

        //std::cout<<std::endl;
        out<<std::endl;
    }
    out.close();
}

/* GREEDY SOLVER - FOR COMPARISION USE ONLY! */

Client* GreedySolver::getNextBest(Client *current, int q, double t) {
    std::vector<Client*> *dostepni = new std::vector<Client*>;
    for (int i = 1; i < mInstance->getSize(); i++) {
        Client *next = mInstance->getClient(i);
        double d = mInstance->calculateTo(current, next);
        if(next->isVisited() ||
           q+next->getQuantity()>mInstance->getMaxQuantity() ||
           t+d>next->getMaximalTime()||
           d +mInstance->calculateTo(next, mInstance->getDepot()) + next->getDispatchTime() > mInstance->getDepot()->getMaximalTime() - t)
            continue;

        next->setSzacunek(d+(d+t-next->getDispatchTime()));
        dostepni->push_back(next);
    }
    Client *ret = nullptr;
    if(!dostepni->empty()) {
        std::sort(dostepni->begin(), dostepni->end(), greedyCmp);

        ret = dostepni->at(0);
    }
    delete(dostepni);
    return ret;
}

GreedySolver::GreedySolver(Instance *instance) {
    mInstance = instance;
    totalTime = 0;
    remainingV = (instance->getSize()-1);
}

GreedySolver::~GreedySolver() {
    for(int i=0; i<mInstance->getSize(); i++) {
        mInstance->getClient(i)->setVisited(false);
    }
    for(int i=0;i<resolution.size();i++) {
        delete(resolution.at(i));
    }
    resolution.clear();
}

double GreedySolver::getTotalTime(){
    return totalTime;
}

void GreedySolver::solve() {
    Client *current = mInstance->getDepot();
    int q = 0;
    double t = 0;

    double prevd = 0;

    std::vector<Client *> *res = new std::vector<Client *>;
    resolution.push_back(res);

    while (current != nullptr) {
        current->setVisited(true);
        res->push_back(current);

        q += current->getQuantity();
        t += prevd;
        t += current->getDispatchTime() + std::max((double) 0, current->getEstimatedTime() - t);

        Client *next = getNextBest(current, q, t);
        if (next != nullptr) {
            prevd = mInstance->calculateTo(current, next);
            remainingV--;
        } else if (remainingV == 0) {
            totalTime += t + mInstance->calculateTo(current, mInstance->getDepot());
            res->push_back(mInstance->getDepot());
            return;
        } else {
            next = mInstance->getDepot();
            totalTime += t + mInstance->calculateTo(current, next);
            res->push_back(mInstance->getDepot());
            res = new std::vector<Client *>;
            resolution.push_back(res);
            q = 0;
            t = 0;
            prevd = 0;
        }
        current = next;
    }
}