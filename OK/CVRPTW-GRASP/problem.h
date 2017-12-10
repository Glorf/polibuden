#ifndef PRIMITIVES
#include <iostream>
#include <fstream>
#include <vector>
#include <cmath>
#include <algorithm>
#include <iomanip>
#include <cfloat>
#include <map>
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
    bool mVisited;
    double szacunek;

public:
    explicit Client(int *params);
    int getQuantity();
    int getId();
    int getX();
    int getY();
    double calculateFrom(int x, int y);
    bool isVisited();
    void setVisited(bool set);
    double getDispatchTime();
    double getEstimatedTime();
    int getMaximalTime();
    double getSzacunek();
    void setSzacunek(double szac);
};

class Instance {
    std::string name;
    int K;
    int Q;
    std::vector<Client*> lista;
    double **distances;

public:
    explicit Instance(std::string filename);
    void addClient(Client *client);
    bool validate(std::string file);
    Client *getClient(int n);
    Client *getDepot();
    int getMaxQuantity();
    unsigned long getSize();
    void cleanVisits();
    double calculateTo(Client *current, Client *next);
};

class Solver {
    Instance *mInstance;
    double totalTime;
    unsigned long nRand;
    std::vector<std::vector<Client*>*> resolution;
    Client *getNextBest(Client *current, int q, double t);
    unsigned long remainingV;

public:
    double getTotalTime();
    std::vector<std::vector<Client*>*> getResolution();
    explicit Solver(Instance *instance);
    ~Solver();
    void setNRand(unsigned long nRand);
    void solve();
    void saveSolution(std::string file);
};


#endif //HEURYSTYKA_PROBLEM_H
