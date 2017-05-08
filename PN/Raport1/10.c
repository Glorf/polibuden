#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#define v1 (float)table[1]
#define v2 (float)table[2]
#define v3 (float)table[3]
#define v4 (float)table[4]
#define v5 (float)table[5]

int main(int argc, char **argv) {
    float table[argc-1];
    char *arg = argv[1];
    for(int i=1; i<argc-1; i++)
    {
        table[i]=atof(argv[i+1]);
    }
    
    if(strcmp(arg, "kolo") == 0 && argc==3) printf("Obwód: %f Pole: %f", 2*M_PI*v1, M_PI*pow(v1, 2));
    else if(strcmp(arg, "kwadrat") == 0 && argc == 3) printf("Obwód: %f Pole: %f", 4*v1, pow(v1,2));
    else if(strcmp(arg, "prostokat") == 0 && argc == 4) printf("Obwód: %f Pole: %f", (2*v1)+(2*v2), v1*v2);
    else if(strcmp(arg, "trojkat") == 0 && argc == 5)
    {
        float p = (v1+v2+v3)/2;
        float P = sqrt(p*(p-v1)*(p-v2)*(p-v3));
        printf("Obwód: %f Pole: %f", v1+v2+v3, P);
    }
    else if(strcmp(arg, "trapez") == 0 && argc == 5) printf("Obwód: %f Pole: %f",v1+v2+v3+v4, ((v1+v2)/2)*v5);
    else printf("%f", "Invalid operation");

    return 0;
}
