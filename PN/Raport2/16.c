#include <stdio.h>
#include <stdlib.h>

int silnia (int n)
{
    if(n == 0)
        return 1;
    else
        return n*silnia(n-1);
}

int main(int argc, char **argv)
{
    int n = atoi(argv[1]);
    int k = atoi(argv[2]);
    printf("%d", silnia(n)/(silnia(k)*silnia(n-k)));
    return 0;
}
