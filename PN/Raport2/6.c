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
        int sum2 = 0;
        for (int j = 1; j <= (sum/2); j++) 
        {
            if ((sum % j) == 0)
                sum2+=j; 
        }
        if(sum2!= 0 && sum2==sum && sum2!=i)
            printf("%d %d\n", i, sum2);
    }
    return 0;
}
