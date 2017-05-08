#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char **argv) {
    srand(time(NULL));
    int n=atoi(argv[1]);

    int table[n];
    for(int i=0; i<n; i++)
        table[i]=0;

    for(int i=1; i<n+1; i++)
    {
        int divisor = RAND_MAX/(n);
        int retval;

        do { 
            retval = rand() / divisor;
        } while (retval > n-1 || table[retval]!=0);
        table[retval]=i;
    }
    for(int i=0; i<n; i++)
        printf("%d ", table[i]);
}
