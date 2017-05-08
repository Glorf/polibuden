#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int main(int argc, char **argv)
{
    int a = atoi(argv[1]);
    int b = atoi(argv[2]);
    for(int i=a; i<=b;i++)
    {
        char str[10];
        sprintf(str, "%d", i*i);
        char r = str[strlen(str)-1];
        if((int)(r)-'0' == i)
            printf("%d ",i);
    }

    return 0;
}
