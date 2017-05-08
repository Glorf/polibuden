#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    srand(time(NULL));
    int min = 0, max = 10;
    int matrix_size = min + rand() % max;

    int matrix1[matrix_size][matrix_size];
    int matrix2[matrix_size][matrix_size];
    int multiplied_matrix[matrix_size][matrix_size];

    for(int i = 0; i < matrix_size; i++)
        for(int j = 0; j < matrix_size; j++)
            matrix1[i][j] = min + rand() % max;
    for(int i = 0; i < matrix_size; i++)
        for(int j = 0; j < matrix_size; j++)
            matrix2[i][j] = min + rand() % max;

    int sum = 0;
    for(int i = 0; i < matrix_size; i++)
    {
        for(int j = 0; j < matrix_size; j++)
        {
            for(int k = 0; k < matrix_size; k++)
                sum += matrix1[i][k] * matrix2[k][j];

            multiplied_matrix[i][j] = sum;
            sum = 0;
        }
    }
    printf("\n");

    for(int i = 0; i < matrix_size; i++)
    {
        for(int j = 0; j < matrix_size; j++)
       {
           printf("%d\t", multiplied_matrix[i][j]);
       }
       printf("\n");
    }
}
