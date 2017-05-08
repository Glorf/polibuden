#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    int j = argc-1;
    while(j>0)
    {
        for(int i=1; i<j; i++)
        {
            if(table[i]<table[i-1]) //sprawdzamy pary elementów od początku
            {
                int tmp = table[i];
                table[i] = table[i-1];
                table[i-1] = tmp;
            }
        }
        j--; //"Odcinamy" ostatni element, już sprawdzony
    }
    /*for(int i=0; i<argc-1; i++)
    {
        printf("%d ", table[i]);
    }*/
}
