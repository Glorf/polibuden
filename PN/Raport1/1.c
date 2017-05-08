#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    double table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atof(argv[i+1]);
    }

    double max=0;
    for(int i=0; i<argc-1; i++)
    {
        if(table[i]>max)
            max = table[i];
    }

    printf("%f ", max);
    return 0;
}
