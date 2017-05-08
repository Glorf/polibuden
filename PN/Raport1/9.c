#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define a (float)table[0]
#define b (float)table[1]
#define c (float)table[2]

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }
    float delta = pow(b,2)-(4*a*c);
    if(delta>0)
        printf("x1: %f x2: %f", (-b-sqrt(delta))/(2*a), (-b+sqrt(delta))/(2*a));
    else if(delta<0.001 && delta>-0.001) //żeby zero było zerem
        printf("x0: %f", -b/(2*a));
    else
        printf("%s","Brak pierwiastków!");

    return 0;
}
