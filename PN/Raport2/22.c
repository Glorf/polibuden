#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main()
{
    srand(time(NULL));
    int min = 0, max = 2;   
    int matrix_size = min + rand() % max;
    int matrix[matrix_size][matrix_size];

    for(int i = 0; i < matrix_size; i++)
        for(int j = 0; j < matrix_size; j++)
            matrix[i][j] =min + rand() % max;

    int is_symmetric = 1;
    for(int i = 0; i < matrix_size; i++)
        for(int j = 0; j < matrix_size; j++)
            if(matrix[i][j] != matrix[j][i])
            {
                is_symmetric = 0;
                break;
            }

    if(is_symmetric == 0)
        printf("Macierz nie jest symetryczna.\n");
    else
        printf("Macierz jest symetryczna.\n");

    int contains_empty_columns;
    for(int i = 0; i < matrix_size; i++)
    {
        contains_empty_columns = 1;
        for(int j = 0; j < matrix_size; j++)
        {
            if(matrix[j][i] != 0)
            {
                contains_empty_columns = 0;
                break;
            }
        }
        if(contains_empty_columns == 1)
            break;
    }

    if(contains_empty_columns == 0)
        printf("Nie zawiera pustych kolumn.\n");
    else
        printf("Zawiera puste kolumny.\n");

    for(int i = 0; i < matrix_size; i++)
    {
        for(int j = 0; j < matrix_size; j++)
       {
           printf("%d\t", matrix[i][j]);
       }
       printf("\n");
    }
}
