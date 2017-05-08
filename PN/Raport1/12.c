#include <stdio.h>
#include <stdlib.h>
#define a table[0]
#define b table[1]
#define c table[2]

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }
    if(a+b<=c||b+c<=a||a+c<=b)
        printf("%s", "To nie jest trójkąt!");
    else
        printf("%s", "To może być trójkąt");
    

    return 0;
}
