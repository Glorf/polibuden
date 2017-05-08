#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define a1 table[0]
#define b1 table[1]
#define a2 table[2]
#define b2 table[3]

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    double x = (b1-b2)/(a2-a1);
    double y = a1*x+b1;
    double len = sqrt(pow(x,2)+pow(y,2));

    printf("%c%f%c%f%c %f", '(', x, ',', y, ')', len);
    return 0;
}
