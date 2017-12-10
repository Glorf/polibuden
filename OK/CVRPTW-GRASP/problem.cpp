#include "problem.h"

Client::Client(int *params) {
    mid=params[0];
    mx=params[1];
    my=params[2];
    mq=params[3];
    me=params[4];
    ml=params[5];
    md=params[6]; //NASZE S
    mVisited = false;
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

bool Client::isVisited() {
    return mVisited;
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

void Client::setVisited(bool set) {
    if(mid != 0)
        mVisited = set;
}

Instance::Instance(std::string file) {
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
    for(int i=0; i<getSize(); i++)
        distances[i] = new double[getSize()];

    std::fill(&distances[0][0], &distances[0][0]+getSize(), 0);
}

void Instance::addClient(Client *client) {
    lista.push_back(client);
}

void Instance::cleanVisits() {
    for(int i=0; i<lista.size();i++) {
        lista.at(i)->setVisited(false);
    }
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

double Client::getSzacunek() {
    return szacunek;
}

void Client::setSzacunek(double szac) {
    this->szacunek = szac;
}

bool cmp(Client *c1, Client *c2)
{
    return c1->getSzacunek() < c2->getSzacunek();
}

int Instance::getMaxQuantity() {
    return Q;
}

Client* Solver::getNextBest(Client *current, int q, double t) {
    std::vector<Client*> *dostepni = new std::vector<Client*>;
    for (int i = 1; i < mInstance->getSize(); i++) {
        Client *next = mInstance->getClient(i);
        double d = mInstance->calculateTo(current, next);
        if(next->isVisited() ||
           q+next->getQuantity()>mInstance->getMaxQuantity() ||
           t+d>next->getMaximalTime()+0.000001 /*DOUBLE HACK*/ ||
           d +mInstance->calculateTo(next, mInstance->getDepot()) + next->getDispatchTime() > mInstance->getDepot()->getMaximalTime() - t + 0.000001 /*DOUBLE HACK*/)
            continue;

        next->setSzacunek(d+(d+t-next->getDispatchTime()));
        dostepni->push_back(next);
    }
    Client *ret = nullptr;
    if(!dostepni->empty()) {
        nRand = std::min(nRand,dostepni->size());
        std::sort(dostepni->begin(), dostepni->end(), cmp);

        if(nRand<2)
            ret = dostepni->at(0);
        else
            ret = dostepni->at(std::rand()%nRand);
    }
    delete(dostepni);
    return ret;
}

unsigned long Instance::getSize() {
    return lista.size();
}

Solver::Solver(Instance *instance) {
    mInstance = instance;
    nRand = 1;
    totalTime = 0;
    remainingV = (instance->getSize()-1);
}

Solver::~Solver() {
    mInstance->cleanVisits();
    for(int i=0;i<resolution.size();i++) {
        delete(resolution.at(i));
    }
    resolution.clear();
}

void Solver::setNRand(unsigned long nRand) {
    this->nRand = nRand;
}

double Solver::getTotalTime(){
    return totalTime;
}

std::vector<std::vector<Client*>*> Solver::getResolution(){
    return resolution;
}

double Instance::calculateTo(Client *current, Client *next) {
    double val = distances[current->getId()][next->getId()];
    if(val<0.000001) {
        double res = current->calculateFrom(next->getX(), next->getY());
        distances[current->getId()][next->getId()] = res;
        distances[next->getId()][current->getId()] = res;
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

        Client *next = getNextBest(current, q, t);
        if(next!=nullptr) {
            prevd = mInstance->calculateTo(current, next);
            remainingV--;
        }
        else if(remainingV == 0) {
            totalTime+=t+mInstance->calculateTo(current,mInstance->getDepot());
            res->push_back(mInstance->getDepot());
            return;
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
            std::cout<<res->at(j)->getId()<<" ";
            out<<res->at(j)->getId()<<" ";
        }

        std::cout<<std::endl;
        out<<std::endl;
    }
    out.close();
}
