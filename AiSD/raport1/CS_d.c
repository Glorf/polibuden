#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    long table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atol(argv[i+1]);
    }
    long max = 0;
    for(int i=0; i<argc-1; i++) //sprawdzanie zakresu
    {
        if(table[i]>max)
            max=table[i];
    }

    long tsize = max+1;
    long *ctable = malloc(sizeof(long)*tsize); //obejście stack overflow

    for(long i=0; i<tsize; i++) //zerowanie tablic
        ctable[i] = 0;

    for(long i=0; i<argc-1; i++) //sumowanie ilości wystąpień
        ctable[table[i]]++;

    for(long i=1; i<tsize; i++) //definiowanie indeksów dla wystąpień w tablicy wynikowej
        ctable[i] = ctable[i]+ctable[i-1];

    long rtable[argc-1];
    for(long i=argc-2; i>=0; i--) //tworzenie tablicy wynikowej
    {
        rtable[ctable[table[i]]] = table[i];
        ctable[table[i]]--;
    }

    /*for(int i=1; i<argc; i++)
    {
        printf("%d ", rtable[i]);
    }*/
}
