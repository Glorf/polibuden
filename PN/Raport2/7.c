#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main(int argc, char **argv) {
    int A = atoi(argv[1]);
    int B = atoi(argv[2]);

    int table[B+1];

    for(int i=2; i<=B; i++) 
        table[i]=1;
    for(int i=2; i<=floor(sqrt(B)); i++)
    {
        if (table[i] != 0)
        {
            int j = 2*i;
            while (j<=B)
            {
                table[j] = 0;
                j += i;
            }
        }
    }
    for(int i=A; i<=B; i++)
    {
        if(table[i] == 1)
            printf("%d ", i);
    }
    return 0;
}
