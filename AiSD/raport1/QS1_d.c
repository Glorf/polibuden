#include <stdio.h>
#include <stdlib.h>

void quicksort(int table[], int start, int end)
{
    int startstep = start;
    int endstep = end;
    int vpivot = table[start];
    while(startstep <= endstep)
    {
        while(table[startstep] < vpivot)
            startstep++;
        while(table[endstep] > vpivot)
            endstep--;

        if(startstep<=endstep)
        {
            int tmp = table[endstep];
            table[endstep] = table[startstep];
            table[startstep] = tmp;
            startstep++;
            endstep--;
        }
    }

    if(startstep<end)
        quicksort(table, startstep, end);
    if(endstep>start)
        quicksort(table, start, endstep);
}

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    quicksort(table, 0, argc-2);

    /*for(int i=0; i<argc-1; i++)
    {
        printf("%d ", table[i]);
    }*/
}
