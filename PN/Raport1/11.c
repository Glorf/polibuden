#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#define n1 (float)table[1]
#define n2 (float)table[2]

int main(int argc, char **argv) {
    float table[argc-1];
    char *arg = argv[1];
    for(int i=1; i<argc-1; i++)
    {
        table[i]=atof(argv[i+1]);
    }
    
    if(strcmp(arg, "pow") == 0 && argc==4) printf("%f", pow(n1, n2));
    else if(strcmp(arg, "odw") == 0 && argc == 3) printf("%f", 1/n1);
    else if(strcmp(arg, "ln") == 0 && argc == 3) printf("%f", log(n1));
    else if(strcmp(arg, "sin") == 0 && argc == 3) printf("%f", sin(n1));
    else if(strcmp(arg, "tg") == 0 && argc == 3) printf("%f", tan(n1));
    else printf("%f", "Invalid operation");

    return 0;
}
