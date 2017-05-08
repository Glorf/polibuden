#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
    int n = atoi(argv[1])+1;
    int tablica[n];
    for(int i = 2; i < n; i++)
        tablica[i] = 0;

    for(int i = 2; i < n; i++)    
        for(int l = i+1; l < n; l++)
            if(l % i == 0)
                tablica[l] = 0;

    for(int i = 0; i < n;i++)
        for(int l = 0;l < n; l++)
            if(tablica[i] == 1 && tablica[l] == 1)
                if(i - l == 2)
                    printf("%d %d\n", i, l);
    return 0;
}
