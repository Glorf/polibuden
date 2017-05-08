#include<stdio.h>
#include<stdlib.h>

int potega(int a,int b)
{
    if(b==0)
        return 1;
    else
        return a = a*potega(a,--b);
}
 
int main(int argc, char **argv)
{
    printf("%d\n", potega(atoi(argv[1]),atoi(argv[2])));
    return 0;
}
