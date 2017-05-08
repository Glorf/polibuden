#include <stdio.h>
#include <stdlib.h>

void przesiej(int table[], int rozmiar, int pos) //odbuduj stos z poprzesiewanymi wartościami
{
    int max = pos;
    int l = 2*pos;
    int r = 2*pos+1;
    if(l<rozmiar && table[l]>table[pos])
        max = l;
    if(r<rozmiar && table[r]>table[max])
        max = r;
    if(max!=pos)
    {
        int tmp = table[pos];
        table[pos] = table[max];
        table[max] = tmp;
        przesiej(table, rozmiar, max);
    }
}

void buildheap(int table[], int rozmiar)
{
    for(int i=(rozmiar/2)-1; i>=0; i--)
        przesiej(table, rozmiar, i);
}

void heapsort(int table[], int rozmiar) //Wykonaj sortowanie
{
    buildheap(table, rozmiar);
    for(int i=rozmiar-1; i>0; i--)
    {
        int tmp = table[0];
        table[0] = table[i];
        table[i] = tmp;
        rozmiar--;
        przesiej(table, rozmiar, 0);
    }
}

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    heapsort(table, argc-1);
    /*for(int i=0; i<argc-1; i++)
    {
        printf("%d ", table[i]);
    }*/
}
