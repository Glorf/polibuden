#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int result(char *str, int step)
{
    int sum = 0;
    if(step<strlen(str)-1)
        sum=result(str, step+1);
    sum += ((int)(str[step])-'0');
    return sum;
}

int main(int argc, char **argv)
{
    char *n = argv[1];
    printf("%d", result(n, 0));

    return 0;
}
