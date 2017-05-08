#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char **argv) {
    int sum = 0;
    int res = 1;
    for(int i=0; i<argc-1; i++)
    {
        sum+=atoi(argv[i+1]);
        res*=atoi(argv[i+1]);
    }

    printf("Arytmetryczna: %f, Geometryczna: %f \n", ((float)sum)/(argc-1), pow(res, 1./(argc-1)));

    return 0;
}
