#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv)
{
    int n = 0;
    int c[5] = {0, 0, 0, 0, 0}; 
    char tab [1000][40];

    FILE *fp = fopen(argv[1], "rw");
    for(;fscanf(fp, "%s", tab[n])!= EOF; n++);
    for(int i=0;i<n;i++)
    {
        int j=0;
        while(tab[i][j]=='*') j++;
        switch(j)
        {
            case 1:
            printf("%d. ", ++c[0]);
            break;
            case 2:
            printf("  %d.%d. ", c[0], ++c[1]);
            break;
            case 3:
            printf("    %d.%d.%d. ", c[0], c[1], ++c[2]);
            break;
            case 4:
            printf("      %d.%d.%d.%d. ", c[0], c[1], c[2], ++c[3]);
            break;
            case 5:
            printf("        %d.%d.%d.%d.%d. ", c[0], c[1], c[2], c[3], ++c[4]);
            break;
        }
        for(int h=j;h<5;h++) c[h] = 0;
            
        for(int h=j;h<strlen(tab[i]);h++)
            printf("%c", tab[i][h]);
        if(i%3==2)
            printf("\n");
        else if(i%3==1)
            printf(" ");
    }
    c[0]=0;
    printf("\n");
    for(int i=0;i<n;i++)
    {
        int j=0;
        while(tab[i][j]=='*') j++;
        switch(j)
        {
            case 1:
            printf("%d. ", ++c[0]+64);
            break;
            case 2:
            printf("  %d.%d. ", c[0], ++c[1]);
            break;
            case 3:
            printf("    %d.%d.%d. ", c[0], c[1], ++c[2]+96);
            break;
            case 4:
            printf("      %d.%d.%d.%d. ", c[0], c[1], c[2], ++c[3]);
            break;
            case 5:
            printf("        %d.%d.%d.%d.%d. ", c[0], c[1], c[2], c[3], ++c[4]+96);
            break;
        }
        for(int h=j;h<5;h++) c[h] = 0;
        for(int h=j;h<strlen(tab[i]);h++)
            printf("%c", tab[i][h]);
        if(i%3==2)
            printf("\n");
        else if(i%3==1)
            printf(" ");
    }
}
