#include <stdio.h>
#include <stdlib.h>
#include <time.h>

union Bag
{
    int a;
    char b;
    float c;
} bag;

int irandom(int min, int max)
{
    int tmp;
    if (max>=min)
        max-= min;
    else
    {
        tmp= min - max;
        min= max;
        max= tmp;
    }
    return max ? (rand() % max + min) : min;
}

int main(int argc, char **argv)
{
    srand(time(NULL));
    int n = irandom(1,10);
    union Bag table[n];
    for(int i=0; i<n; i++)
    {
        int choice = irandom(1,3);
        switch(choice)
        {
            case 1:
                table[i].a = irandom(0,100);
                break;
            case 2:
                table[i].b = irandom(0,100);
                break;
            case 3:
                table[i].c = irandom(0,100);
                break;
        }
    }
}
