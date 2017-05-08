#include <stdio.h>
#include <stdlib.h>
#define m table[0]
#define n table[1]

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    double T = (5+(4+(3+(2*(m-n-1)))))/((((2*(m+n+1)-3)-4)-5));

    printf("%f ", T);
    return 0;
}
