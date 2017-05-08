#include<stdio.h>
#include<stdlib.h>
#include<time.h>

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

int main()
{
    srand(time(0));
    int tab[10];
    int s=5;
    tab[0]=rand()%10;
    tab[5]=rand()%10;
    for(int i=1;i<s;i++)
    {
        tab[i]=tab[i-1]+rand()%10+1;
        tab[i+s]=tab[s+i-1]+rand()%10+1;
    }
    for(int i=0;i<10;i++)
        printf("%d ",tab[i]);
    printf("\n");
    mergesort(tab,0,9);
    for(int i=0;i<10;i++)
        printf("%d ",tab[i]);
    printf("\n");
}
