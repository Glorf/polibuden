#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define x table[0]

int main(int argc, char **argv) {
    double table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atof(argv[i+1]);
    }

    double N = sqrt((pow(sin(pow(x,3)),2)+1.25)/(pow(cos(pow(x,2)),3)+1.25))/((log(pow(tan(x+2),2))+2.5));

    printf("%f ", N);
    return 0;
}
