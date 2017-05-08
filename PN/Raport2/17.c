#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char **argv)
{
    int min = atoi(argv[1]);
    int max = atoi(argv[2]);
    int c;

    for(int a = min; a < max; a++)
        for(int b = a; b < max; b++)
        {
            c = sqrt(pow(a, 2) + pow(b, 2));
            if(c-roundf(c)>-0.01 || c-roundf(c)<0.01)
                printf("a = %d b = %d c = %d\n", a, b, roundf(c));
        }
    return 0;
}
