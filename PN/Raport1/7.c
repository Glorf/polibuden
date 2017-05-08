#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define x1 table[0]
#define y1 table[1]
#define x2 table[2]
#define y2 table[3]
#define x3 table[4]
#define y3 table[5]
#define x4 table[6]
#define y4 table[7]

int main(int argc, char **argv) {
    int table[argc-1];
    for(int i=0; i<argc-1; i++)
    {
        table[i]=atoi(argv[i+1]);
    }

    float a = sqrt(pow((x1 - x2), 2) + pow((y1 - y2), 2));
    float b = sqrt(pow((x2 - x3), 2) + pow((y2 - y3), 2));
    float c = sqrt(pow((x3 - x4), 2) + pow((y3 - y4), 2));
    float d = sqrt(pow((x4 - x1), 2) + pow((y4 - y1), 2));
    float e = sqrt(pow((x2 - x4), 2) + pow((y2 - y4), 2));
    float f = sqrt(pow((x1 - x3), 2) + pow((y1 - y3), 2));

    if(a==b && b==c && c==d && d==a && e==f)
        printf("%s\n", "To kwadrat");
    else if(a==b && b==c && c==d && d==a)
        printf("%s\n", "To prostokąt");
    else if(e==f)
        printf("%d\n", "To romb");
    return 0;
}
