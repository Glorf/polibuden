#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    double temp = atof(argv[1]);
    char scale = argv[2][0];
    switch(scale)
    {
    case 'C':
        printf("%f %c", (9/5)*temp+32, 'F');
        break;
    case 'F':
        printf("%f %c", (5/9)*(temp-32), 'C');
        break;
    }
    return 0;
}
