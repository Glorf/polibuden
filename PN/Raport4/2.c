#include <stdlib.h>
#include <stdio.h>

int main(int argc, char **argv)
{
    int num1 = strtol(argv[1], NULL, 16);
    char operation = argv[2][0];
    int num2 = strtol(argv[3], NULL, 16);
    switch (operation)
    {
    case '+':
        printf("%x\n", num1+num2);
        break;
    case '-':
        printf("%x\n", num1-num2);
        break;
    case '*':
        printf("%x\n", num1*num2);
        break;
    }
}
