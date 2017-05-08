#include <stdio.h>
#include <stdlib.h>

#define am table[0]
#define bm table[1]

int euklid(int a, int b)
{
    if (b==0) return a;
    else return euklid(b,(a%b));
}

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
        table[i]=atoi(argv[i+1]);

    printf("%d", euklid(am, bm));
    return 0;
}
