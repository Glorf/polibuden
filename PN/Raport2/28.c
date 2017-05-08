#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) { //DONE
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    for(int i=0; i<=argc-1; i++)
    {
        int mIndex=i;
        for(int j=i+1; j<argc-1; j++) //Szukamy najmniejszej
        {
            if(table[j] < table[mIndex])
                mIndex = j;
        }

        int tmp = table[mIndex];
        table[mIndex] = table[i];
        table[i] = tmp;
    }
    /*for(int i=0; i<argc-1; i++)
    {
        printf("%d ", table[i]);
    }*/
}
