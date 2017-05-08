#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define n table[0]
#define max table[1]

int main(int argc, char **argv) {
    int table[argc-1];
    srand(time(NULL));
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    for(int i=0; i<n; i++)
    {
        int divisor = RAND_MAX/(max+1);
        int retval;

        do { 
            retval = rand() / divisor;
        } while (retval > max);
        printf("%d ", retval);
    }
}
