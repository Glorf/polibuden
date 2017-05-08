#include <stdio.h>
#include <stdlib.h>

void merge(int table[], int start, int end, int pivot) //SCALANIE
{
    int nElem = end-start+1;
    int tmp[nElem]; //Tworzymy tablicę pomocniczą
    int midstep = pivot+1; //iterator od za pivotem
    int tablestep = 0; //iterator nowej tablicy
    int startstep = start; //iterator od początku zakresu
    while(startstep<=pivot && midstep<=end)
    {
        if(table[midstep] < table[startstep]) //porównujemy i ustawiamy w odpowiedniej kolejności
            tmp[tablestep++] = table[midstep++];
        else
            tmp[tablestep++] = table[startstep++];
    }
    while(startstep <= pivot)
        tmp[tablestep++] = table[startstep++];
    while(midstep <= end)
        tmp[tablestep++] = table[midstep++];

    for(int i=0; i<nElem; i++)
    {
        table[start+i] = tmp[i];
    }
}

void mergesort(int table[], int start, int end)
{
    if(start<end) //dzielimy przedział na połowy - minimalnie 1 element
    {
        int pivot = (start+end)/2;
        mergesort(table, start, pivot);
        mergesort(table, pivot+1, end);
        merge(table, start, end, pivot);
    }
}

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    mergesort(table, 0, argc-2);

    /*for(int i=0; i<argc-1; i++)
    {
        printf("%d ", table[i]);
    }*/
    return 0;
}
