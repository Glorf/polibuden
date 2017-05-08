#include<stdio.h>
#include<math.h>

int main(int argc, char **argv)
{
    FILE *fp;
    char tmp, c [10];
    int t[100][2];
    int n=0;
    fp = fopen(argv[1], "r");
    if(fp==NULL)
        return -1;

    while(!feof(fp))
    {
        int k = 0;
        tmp = fgetc(fp);
        for(int i=0;tmp!='\n' && !feof(fp); i++)
        {
            while(tmp!='\n' && tmp!=' ' && !feof(fp))
            {
                c[k] = tmp;
                k++;
                tmp = fgetc(fp);
            }
            for(int j=0;j<k;j++)
                t[i][0]+=((c[j]-48)*pow(10,k-j-1));
            k=0;
            t[i][1]++;
            if(tmp==32)
                tmp = fgetc(fp);
        }
    }
    printf("Suma: ");
    for(int i=0;i<n;i++)
        printf("%d ", t[i][0]);
    printf("\nSrednia: ");
    for(int i=0;i<n;i++)
        printf("%lf ", (t[i][0]*1.0)/(t[i][1]*1.0));
    fclose(fp);
}
