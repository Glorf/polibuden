#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    for(int i=1; i<argc-1; i++) //Idź do przodu
    {
        int j=i;
        while(table[j]<table[j-1] && j>0) //Jeśli mniejszy, zamieniaj do tyłu tak długi jak się da
        {
            int tmp = table[j];
            table[j] = table[j-1];
            table[j-1]= tmp;
            j--;
        }
    }
    /*for(int i=0; i<argc-1; i++)
    {
        printf("%d ", table[i]);
    }*/
}
