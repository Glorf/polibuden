#include<stdio.h>
#include<stdlib.h>

int silnia (int n)
{
    if(n == 0)
        return 1;
    else
        return n*silnia(n-1);
}
 
int main(int argc, char **argv)
{
    printf("%d\n", silnia(atoi(argv[1])));
    return 0;
}
