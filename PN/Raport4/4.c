#include <stdio.h>
#include <stdlib.h>
#include <string.h>
 
int main (int argc, char **argv)
{
    char cmin[100];
    char cmax[100];
    int min = 1000;
    int max = 0;
    FILE *fp;
    if ((fp=fopen(argv[1], "r"))==NULL) {
        printf ("Nie mogę otworzyć pliku!\n");
        return -1;
    }
    char tmp[100];
    while(fgets(tmp, 100, fp))
    {
        int l = strlen(tmp);
        if(l>max)
        {
            max = l;
            strcpy(cmax, tmp);
        }
        else if(l<min)
        {
            min = l;
            strcpy(cmin, tmp);
        }
    }
    fclose (fp);
    printf("%s\n%s\n", cmin,cmax);
    return 0;
 }
