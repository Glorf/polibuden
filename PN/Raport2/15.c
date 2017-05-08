#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int silnia(int n)
{
    if(n == 0)
        return 1;
    else
        return n*silnia(n-1);
}

int main(int argc, char **argv)
{
    int n = atoi(argv[1]);
    for(int i=2; i<n; i++)
        for(int j=2; j<n/2; j++)
            if (silnia(i)-(pow(j, 3)-j)==0)
                printf("%d!=%d*%d*%d\n", i, j-1, j, j+1);
return 0;
}
