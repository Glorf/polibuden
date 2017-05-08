#include <stdio.h>
#include <stdlib.h>
#define a table[0]
#define b table[1]

int main(int argc, char **argv) {
    float table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atof(argv[i+1]);
    }

    double R = 4*a + (2*(a-b-1)/((a*a)+(b*b)+1));

    printf("%f ", R);
    return 0;
}
