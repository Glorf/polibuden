//
// Created by mbien on 14.12.2019.
//

#include <iostream>
#include <algorithm>
#include <random>

#include <chrono>
#include "solver.h"
#include "helpers.h"

#define POPULATION_SIZE 100
#define BEST_THRESHOLD 0.01
#define SURVIVAL_THRESHOLD 0.2
#define OUTSIDERS_RATIO 0
#define MUTATION_CHANCE 0.6
#define ALLEL_DAMAGE_CHANCE 0
#define ALLEL_FLIP_CHANCE 0
#define INSERTION_CHANCE 0

#define MS_PER_ITEM 10

Solver::Solver(Instance *instance): instance(instance) {

}

bool sortByCost(Solution s1, Solution s2) {
    return s1.getCost() < s2.getCost();
}

Solution Solver::solve() {
    //instance->print();

    long milis = std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::steady_clock::now().time_since_epoch()).count();
    long now = milis;
    std::vector<Solution> population;



    //Mutate the good approaches
    for(int i=0;i<POPULATION_SIZE*OUTSIDERS_RATIO; i++) {
        population.push_back(generateRandom());
    }

    while(population.size() < POPULATION_SIZE) {
        //Init with three everlasters
        population.push_back(generateSimpleR());
        population.push_back(generateSimpleD());
        population.push_back(generateSimpleP());
        population.push_back(generateWoodpecker());
    }


    /*
     * Dopuszczamy generateSimple oraz kilka instancji generateRandom.
     * Dokonujemy kopii najlepszych 10%, kolejne 40% używamy do crossover, Przeżywalność 50%, więc kolejne 50% dogenerowujemy na zasadzie insercji, delecji, mutacji
     * W każdym allelu (pracy odpowiadającej jednemu procesorowi) musi zajść insercja, delecja lub mutacja. Mutacja jest lokalna dla allelu. Insercja genu w jednym allelu
     * Implikuje usunięcie go w innym (dzięki temu utrzymujemy koherencję)
     * Crossover - na jednego z rodziców nakładana jest "maska" - losowy wektor (0,1) z prawdop. 50%.
     * Jako że relacja nie jest symetryczna, 50% jest też szans na to że konkretny wektor dostanie relację
     * Maska wpływa na poprzedzanie
     */
    bool first = true;
    while(now - milis < instance->size()*MS_PER_ITEM) {
        now = std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::steady_clock::now().time_since_epoch()).count();

        std::sort(population.begin(), population.end(), sortByCost);

        int best_threshold = POPULATION_SIZE * BEST_THRESHOLD;
        int surviving_threshold = POPULATION_SIZE * SURVIVAL_THRESHOLD;

        std::vector<Solution> new_population;
        std::vector<Solution> replicators;

        for(int i=0; i<POPULATION_SIZE; i++) {
            if(i<best_threshold) { //The solution is good enough to remain as is and be used for crossover
                new_population.push_back(population[i]);
                replicators.push_back(population[i]);
            }
            else if(i<surviving_threshold) { //The solution will not remain but will be used for crossover
                replicators.push_back(population[i]);
            }
        }

        for(int i=0; i<POPULATION_SIZE*OUTSIDERS_RATIO; i++)
            new_population.push_back(generateRandom());


        while(new_population.size()<POPULATION_SIZE) {
            int replicator_1 = rand()%(replicators.size()-1);
            int replicator_2 = rand()%(replicators.size()-1);
            while(replicator_1 == replicator_2) //Cannot self-copulate
                replicator_2 = rand()%(replicators.size()-1);

            /*
             * Crossover howto:
             * Zrobić redundancję elementów - wykonywane jest zadanie którego czas startu jest najmniejszy, co do zasady mogą i powinny się duplikować
             * Co zrobić żeby allele nie rosły? Dodać telomery - odcinają cały allel ponad wyznaczone np 10x wielkość instancji problemu
             * Dodać insercje i delecje łańcuchów, zamianę alleli
             * Jeżeli chromosom ma brakujące geny, to dodajemy je jako karniaki na końcach alleli
             * Crossover robi się banalnie prosty ale wydluża się czas weryfikacji wyniku
             */

            Solution offspring(instance);

            for(int i=0; i<4; i++) {
                auto processor1 = replicators[replicator_1].getProcessor(i);
                auto processor2 = replicators[replicator_2].getProcessor(i);
                //int maxIndex = std::max(processor1.size(), processor2.size());
                int minIndex = std::min(processor1.size(), processor2.size());

                int crossoverPoint = rand() % minIndex;

                for(int j=0; j<minIndex; j++) {
//                    if(j >= processor1.size()) {
//                        offspring.addTaskNoCalc(processor2.at(j)->getId(), i);
//                    }
//                    else if(j >= processor2.size()) {
//                        offspring.addTaskNoCalc(processor1.at(j)->getId(), i);
//                    }
//                    else {
                        if(j<crossoverPoint) {
                            offspring.addTaskNoCalc(processor1.at(j)->getId(), i);
                        }
                        else {
                            offspring.addTaskNoCalc(processor2.at(j)->getId(), i);
                        }
                }
            }

            insertString(&offspring);
            mutate(&offspring);

            offspring.recalculateCost();

            new_population.push_back(offspring);
        }

        population = new_population;
    }

    std::sort(population.begin(), population.end(), sortByCost);

    population[0].pruneDuplicated();

    return population[0];
}

void Solver::mutate(Solution *solution) {
    if(rand()%100 <= MUTATION_CHANCE*100)
        solution->mutate();
}

void Solver::damageAllel(Solution *solution) {
    std::mt19937 mt(rd());
    std::uniform_real_distribution<double> dist(0, 1);
    if(dist(mt) <= ALLEL_DAMAGE_CHANCE)
        solution->damageAllel();
}

void Solver::allelFlip(Solution *solution) {
    if(rand()%100 <= ALLEL_FLIP_CHANCE*100)
        solution->allelFlip();
}

void Solver::insertString(Solution *solution) {
    if(rand()%100 <= INSERTION_CHANCE*100)
        solution->insertString();
}

Solution Solver::generateRandom() {
    Solution solution(instance);
    std::vector<int> shuffle;
    for(int i=0; i<instance->size(); i++) {
        shuffle.push_back(i+1);
    }

    std::shuffle(std::begin(shuffle), std::end(shuffle), std::default_random_engine());

    for(int i=0; i<instance->size(); i++) {
        solution.addTask(shuffle[i], rand()%4);
    }

    return solution;
}

Solution Solver::generateSimpleR() {
    std::vector<Task*> tasks;
    for(int i=1; i<=instance->size(); i++)
        tasks.push_back(instance->getById(i));

    std::sort(tasks.begin(), tasks.end(), greedySorterR);

    Solution solution(instance);
    for(int i=0; i<tasks.size(); i++)  {
        solution.greedyAddTask(tasks[i]->getId());
    }

    return solution;
}

Solution Solver::generateSimpleD() {
    std::vector<Task*> tasks;
    for(int i=1; i<=instance->size(); i++)
        tasks.push_back(instance->getById(i));

    std::sort(tasks.begin(), tasks.end(), greedySorterD);

    Solution solution(instance);
    for(int i=0; i<tasks.size(); i++)  {
        solution.greedyAddTask(tasks[i]->getId());
    }

    return solution;
}

Solution Solver::generateSimpleP() {
    std::vector<Task*> tasks;
    for(int i=1; i<=instance->size(); i++)
        tasks.push_back(instance->getById(i));

    std::sort(tasks.begin(), tasks.end(), greedySorterP);

    Solution solution(instance);
    for(int i=0; i<tasks.size(); i++)  {
        solution.greedyAddTask(tasks[i]->getId());
    }

    return solution;
}

Solution Solver::generateWoodpecker() {
    std::vector<Task*> tasks;
    for(int i=1; i<=instance->size(); i++)
        tasks.push_back(instance->getById(i));

    std::sort(tasks.begin(), tasks.end(), [](Task *t1, Task *t2) {
        return t1->getR() < t2->getR() || (t1->getR() == t2->getR() && t1->getD() < t2->getD());
    });

    Solution solution(instance);

    while(!tasks.empty()) {
        int activeCpu = -1;
        int min = INT32_MAX;
        for(int i=0; i<4; i++) {
            if(i!=activeCpu && solution.getProcessorMoment(i)<min) {
                min = solution.getProcessorMoment(i);
                activeCpu = i;
            }
        }

        long currentCriteria = INT32_MAX;
        int currentTask = 0;
        for(int i=0; i<tasks.size(); i++) {
            Task *activeTask = tasks.at(i);
            long criteria = activeTask->getP() + std::max(0, activeTask->getD() - solution.getProcessorMoment(activeCpu)) +
                    std::max(0, activeTask->getD() - activeTask->getP() - std::max(solution.getProcessorMoment(activeCpu), activeTask->getR()));

            if(criteria < currentCriteria && activeTask->getR() <= solution.getProcessorMoment(activeCpu)) {
                currentCriteria = criteria;
                currentTask = i;
            }

        }

        solution.addTask(tasks[currentTask]->getId(), activeCpu);
        tasks.erase(tasks.begin() + currentTask);
    }

    return solution;
}
