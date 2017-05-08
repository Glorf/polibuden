#include <stdio.h>
#include <stdlib.h>
#define a (double)table[0]
#define b (double)table[1]

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    if(a!=0 && b!=0)
        printf("%f ", (a*(a+b))/((a+b)*(a+b)));
    return 0;
}
