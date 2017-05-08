#include<stdio.h>
#include<stdlib.h>

char table[10];

void rec(int start, int stop)
{
    if(start!=stop)
        rec(start+1, stop);

    printf("%c ", table[start]);
}
 
int main(int argc, char **argv)
{
    for(int i=0; i<argc-1;i++)
        table[i] = argv[i+1][0];

    rec(0,argc-2);
    return 0;
}
