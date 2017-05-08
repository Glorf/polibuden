#include<stdio.h>
#include<math.h>

int main(int argc, char **argv)
{
    FILE *fp;
    char tmp;
    int k=0, l=0;
    fp = fopen(argv[1], "r");
    while(!feof(fp))
    {
        tmp = fgetc(fp);
        if(tmp=='\n')
        {
            tmp = fgetc(fp);
            if(tmp==';')
                l=1;
            else
            {
                l=0;
                k++;
            }
        }
        else if(l==0 && (tmp==' ' || tmp=='\t'))
            k++;
    }
    printf("%d", k+1);
    fclose(fp);
}
