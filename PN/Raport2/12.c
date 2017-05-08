#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    int a = atoi(argv[1]);
    int power = atoi(argv[2]);
    int tmp = 1;
    for (int i = 1; i < power; i++)
    {
        tmp *= a;
    }
    int result = a;
    for (int i = 1; i < tmp; i++)
    {
        result += a;
    }
    printf("%d\n", result);
    return 0;
}
