#include <mpi.h>
#include "guide.h"

#define M 10
#define P 3
#define S 4
#define T 1


int main(int argc, char **argv) {
    MPI::Init_thread(MPI_THREAD_MULTIPLE);

    Guide guide(P, M, S, T);
    guide.createMonitorThread();
    guide.runPerformThread();

    MPI::Finalize();
}