#include "problem.h"

/*
 * Parametr metaheurystyki:
 * Ilosc wybieranych wierzcholkow do losowania
 */
#define METASET 4
/*
 * Parametr metaheurystyki
 * Maksymalny czas wykonania programu w sekundach
 */
#define TIMESET 5

//USAGE: app_name in_file out_file
int main(int argc, char *argv[]) {
    std::time_t startTime = std::time(nullptr);
    srand(startTime);

    Instance *instance = new Instance(argv[1]);

    if (!instance->validate(argv[2])) {
        std::cout << -1 << std::endl;
        return -1;
    }

    std::time_t prevTime=0;
    Solver *best;

    //First: standard heuristics
    Solver *solver = new Solver(instance);
    solver->solve();
    double gtime = solver->getTotalTime();
    best = solver;
    instance->cleanVisits();

    //Now run GRASP as long as you can!
    int przelotow = 0;
    std::time_t itertime;
    if(METASET>1)
    while(std::time(nullptr)-startTime+prevTime<TIMESET)
    {
        itertime = std::time(nullptr);
        solver = new Solver(instance);
        solver->setNRand(METASET);
        solver->solve();

        if(best->getTotalTime()>solver->getTotalTime()){
            delete(best);
            best = solver;
        }
        else {
            delete(solver);
        }
        prevTime = std::time(nullptr)-itertime;
        przelotow++;
    }

    std::cout<<"Trucks: "<<best->getResolution().size()<<std::endl;
    std::cout<<"Greedy time: "<<std::fixed<<gtime<<std::endl;
    std::cout<<"Best time: "<<std::fixed<<best->getTotalTime()<<std::endl;
    std::cout<<"Improvement ratio: "<<(gtime-best->getTotalTime())/gtime*100<<"%"<<std::endl;
    best->saveSolution(argv[2]);
    std::cout<<"Iter per sec: "<< (double)przelotow/TIMESET <<std::endl;
}
