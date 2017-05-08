#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int arg = atoi(argv[1]);

    for(int i=1; i<arg; i++)
    {
        int sum = 0;
        for (int j = 1; j <= (i/2); j++) 
        {
            if ((i % j) == 0)
                sum+=j; 
        }
        if(i==sum)
            printf("%d ", i);
    }
    return 0;
}
