#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    srand(time(NULL));
    int min = 0, max = 10;
    int matrix_size = min + rand() % max;
    int sum_main = 0;
    int sum_below = 0;

    int matrix[matrix_size][matrix_size];
    for(int i = 0; i < matrix_size; i++)
        for(int j = 0; j < matrix_size; j++)
        {
            matrix[i][j] = min + rand() % max;
            if(i == j)
                sum_main += matrix[i][j];
            else if(j < i)
                sum_below += matrix[i][j];
        }
     for(int i = 0; i < matrix_size; i++)
     {
         for(int j = 0; j < matrix_size; j++)
        {
            printf("%d\t", matrix[i][j]);
        }
        printf("\n");
     }
     double proportion = (double) sum_main/sum_below;
     printf("Sum main: %d\n", sum_main);
     printf("Sum below: %d\n", sum_below);
     printf("Proportion: %lf\n", proportion);
}
