#include "problem.h"

/*
 * Parametr metaheurystyki:
 * Początkowa ilość feromonu na wszystkich krawędziach
 */
#define INIT_FEROMON 0.8
/*
 * Parametr metaheurystyki:
 * Wykładnik probabilistyczny atrakcyjności feromonu
 */
#define META_ALPHA 4
/*
 * Parametr metaheurystyki:
 * Wykładnik probabilistycznie nieatrakcyjności długiej drogi
 */
#define META_BETA 1.5
/*
 * Parametr metaheurystyki
 * Współczynnik wygaszania feromonu
 */
#define META_RO 0.1
/*
 * Parametr metaheurystyki
 * Maksymalny czas wykonania programu w sekundach
 */
#define TIMESET 10

//USAGE: app_name in_file out_file
int main(int argc, char *argv[]) {
    std::time_t startTime = std::time(nullptr);
    std::srand(startTime);

    Instance *instance = new Instance(argv[1], INIT_FEROMON);

    if (!instance->validate(argv[2])) {
        std::cout << -1 << std::endl;
        return -1;
    }

    //First: standard heuristics
    GreedySolver *gsolver = new GreedySolver(instance);
    gsolver->solve();
    double gtime = gsolver->getTotalTime();
    delete(gsolver);

    //Now run ACO as long as you can!
    std::time_t prevTime=0;
    Solver *best = nullptr;
    int przelotow = 0;
    std::time_t itertime;
    Solver *solver;
    while(std::time(nullptr)-startTime+prevTime<TIMESET)
    {
        itertime = std::time(nullptr);
        solver = new Solver(instance, META_ALPHA, META_BETA, META_RO);
        solver->solve();
        if(best == nullptr) {
            best = solver;
        }
        else if(best->getTotalTime()>solver->getTotalTime()){
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
