#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
        table[i]=atoi(argv[i+1]);

    for(int i=argc-2; i>=0; i--)
        printf("%d ", table[i]);
    return 0;
}
