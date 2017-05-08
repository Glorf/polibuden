#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char **argv)
{
    srand(time(NULL));
    int min = 0, max = 10;
    int matrix_size = min + rand() % max;

    char row_or_col = *argv[1];
    int sel1 = atoi(argv[2]);
    int sel2 = atoi(argv[3]);
    int matrix[matrix_size][matrix_size];

    for(int i = 0; i < matrix_size; i++)
        for(int j = 0; j < matrix_size; j++)
            matrix[i][j] = min + rand() % max;

    for(int i = 0; i < matrix_size; i++)
    {
       for(int j = 0; j < matrix_size; j++)
       {
           printf("%d\t", matrix[i][j]);
       }
       printf("\n");
    }

    int sum1 = 0;
    int sum2 = 0;
    if(row_or_col == 'w')
    {
        for(int i = 0; i < matrix_size; i++)
            sum1 += matrix[sel1][i];
        for(int i = 0; i < matrix_size; i++)
            sum2 += matrix[sel2][i];
    }
    if(row_or_col == 'k')
    {
        for(int i = 0; i < matrix_size; i++)
            sum1 += matrix[i][sel1];
        for(int i = 0; i < matrix_size; i++)
            sum2 += matrix[i][sel2];
    }
    double proportion = (double) sum1 / sum2;
    printf("%d %d %lf\n", sum1, sum2, proportion);
}
