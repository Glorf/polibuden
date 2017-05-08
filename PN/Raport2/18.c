#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    //LOSUJEMY MACIERZ

    srand(time(NULL));
    int min = 0, max = 10;
    int matrix_size = min + rand() % max;
    int matrix[matrix_size][matrix_size];
    int sum = 0;

    for(int i = 0; i < matrix_size; i++)
    {
        for(int j = 0; j < matrix_size; j++)
        {
            matrix[i][j] = min + rand() % max;
            if(i == j)
                sum += matrix[i][j];
        }
    }

    for(int i = 0; i < matrix_size; i++)
    {
        for(int j = 0; j < matrix_size; j++)
        {
            printf("%d\t", matrix[i][j]);
        }
        printf("\n");
    }
    printf("%d\n", sum);
}
