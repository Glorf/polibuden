#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int isPrime(int number) {
    int i;
    for (i=2; i*i<=number; i++) {
        if (number % i == 0) return 0;
    }
    return 1;
}

int main(int argc, char **argv) {
    int limit = atoi(argv[1]);

    for(int i=2; i<limit; i++)
        if(isPrime(i) && isPrime(pow(2,i)-1))
            printf("%d ", (int)(pow(2,i)-1));
    return 0;
}
