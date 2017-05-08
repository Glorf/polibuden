#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    int a = atoi(argv[1]);
    int b = atoi(argv[2]);
    while (a >= b)
    {
        a = a - b;
    }
    printf("%d\n", a);
    return 0;
}
