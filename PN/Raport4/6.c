#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
int main (int argc, char **argv)
{
    FILE *fp;
    if ((fp=fopen(argv[1], "r"))==NULL) {
        printf ("Nie mogę otworzyć pliku!\n");
        return -1;
    }
    char tmp[100];
    while(fgets(tmp, 100, fp))
    {
        printf("%d\n", abs(atoi(tmp)));
    }
    fclose (fp);
    return 0;
 }
