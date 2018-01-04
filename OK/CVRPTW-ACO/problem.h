#ifndef PRIMITIVES
#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <iomanip>
#include <cfloat>
#include <map>
#include <random>
#define PRIMITIVES
#endif

#ifndef HEURYSTYKA_PROBLEM_H
#define HEURYSTYKA_PROBLEM_H

class Client {
    int mid;
    int mx;
    int my;
    int mq;
    int me;
    int ml;
    int md;
    bool visited;
    long double probability;
    double szacunek;

public:
    explicit Client(int *params);
    int getQuantity();
    int getId();
    int getX();
    int getY();
    double calculateFrom(int x, int y);
    double getDispatchTime();
    double getEstimatedTime();
    int getMaximalTime();
    bool isVisited();
    void setVisited(bool visited);
    long double getProbability();
    void setProbability(long double p);
    double getSzacunek();
    void setSzacunek(double szacunek);
};

class Instance {
    std::string name;
    int initFeromon;
    int K;
    int Q;
    std::vector<Client*> lista;
    double **distances;

public:
    explicit Instance(std::string filename, int initFeromon);
    void addClient(Client *client);
    bool validate(std::string file);
    Client *getClient(int n);
    Client *getDepot();
    int getMaxQuantity();
    unsigned long getSize();
    double calculateTo(Client *current, Client *next);
    double getFeromon(Client *current, Client *next);
    void setFeromon(double f, Client *current, Client *next);
};

class Solver {
    Instance *mInstance;
    double totalTime;
    float alpha;
    float beta;
    float ro;
    std::vector<std::vector<Client*>*> resolution;
    unsigned long remainingV;
    std::vector<Client*> *odwiedzone;

    Client *getNext(Client *current, int q, double t);

public:
    double getTotalTime();
    std::vector<std::vector<Client*>*> getResolution();
    explicit Solver(Instance *instance, float alpha, float beta, float ro);
    ~Solver();
    void solve();
    void saveSolution(std::string file);
};

class GreedySolver {
    Instance *mInstance;
    double totalTime;
    std::vector<std::vector<Client*>*> resolution;
    unsigned long remainingV;

    Client *getNextBest(Client *current, int q, double t);

public:
    double getTotalTime();
    explicit GreedySolver(Instance *instance);
    ~GreedySolver();
    void solve();
};

#endif //HEURYSTYKA_PROBLEM_H