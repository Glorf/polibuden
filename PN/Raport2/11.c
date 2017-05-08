#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    int mul=2;
    int a = atoi(argv[1]);
    for(; a>1; mul++)
    {
        while(a%mul==0)
        {
            printf(" %d", mul);
            a/=mul;
        }
    }
    return 0;
}
