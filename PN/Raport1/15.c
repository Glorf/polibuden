#include <stdio.h>
#include <stdlib.h>
#define x1 table[0]
#define y1 table[1]
#define x2 table[2]
#define y2 table[3]
#define x3 table[4]
#define y3 table[5]

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }
    //sprawdzenie przez wyznacznik
    int res = (x1*y2)+(x2*y3)+(x3*y1)-(x3*y2)-(x1*y3)-(x2*y1);
    if(res==0)
        printf("%s\n", "Punkty są współliniowe");
    else
        printf("%s\n", "Punkty nie są współliniowe");
}
