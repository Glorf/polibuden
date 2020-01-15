#include "instance.h"
#include "solver.h"

#include <iostream>

int main(int argc, char **argv) {
    if(argc!=3) {
        std::cout<<"Oczekuje 2 argumentów: pliku wejścia i pliku wyjścia"<<std::endl;
        return -1;
    }

    Instance instance(argv[1]);
    Solver solver(&instance);

    srand(time(NULL));

    Solution best = solver.solve();
    //best.print();
    best.toFile(argv[2]);

    return 0;
}
