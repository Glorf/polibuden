#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int table[argc-1];
    int a = 1;
    int b = 1;
    int fibo = atoi(argv[1]);

    while(b<fibo)
    {
        int tmp = a;
        a = b;
        b = b+tmp;
    }

    printf("%d", a);
    return 0;
}
