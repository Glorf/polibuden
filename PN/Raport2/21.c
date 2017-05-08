#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char **argv)
{
    int matrix_size = atoi(argv[1]);
    char matrix[matrix_size][matrix_size];

    for(int i = 0; i < matrix_size; i++)
    {
        for(int j = 0; j < matrix_size; j++)
        {
            if(i >= j)
                matrix[i][j] = 'X';
            else
                matrix[i][j] = ' ';
        }
    }

    for(int i = 0; i < matrix_size; i++)
    {
        for(int j = 0; j < matrix_size; j++)
       {
           printf("%c\t", matrix[i][j]);
       }
       printf("\n");
    }
    printf("\n");
    for(int i = matrix_size-1; i >= 0; i--)
    {
       for(int j = matrix_size-1; j >= 0; j--)
       {
           printf("%c\t", matrix[i][j]);
       }
       printf("\n");
    }
}
