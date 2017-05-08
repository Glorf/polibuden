#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    int n = atoi(argv[1]);
    int a;
    if(n > 2)
    {
        a = 2;
        for(int i = 3; i<=n; i++)
            a = a*i;
    }
    else
        a = n;
    
    printf("%d", a);
    return 0;
}
