#include<stdio.h>
#include<math.h>

int main(int argc, char **argv)
{
    FILE *fp;
    char tmp, c [10];
    int i=0, k=0, n=0, x=0;
    fp = fopen(argv[1], "r");
    if(fp!=NULL)
    {
        while(!feof(fp))
        {
            tmp = fgetc(fp);
            while(tmp!='\n' && !feof(fp))
            {
                if(tmp=='-')
                {
                    x=1;
                    tmp = fgetc(fp);
                }
                while(tmp!='\n' && tmp!=' ' && !feof(fp))
                {
                    c[k] = tmp;
                    k++;
                    tmp = fgetc(fp);
                }
                for(int j=0;j<k;j++)
                {
                    if(x)
                        i-=((c[j]-48)*pow(10,k-j-1));
                    else
                        i+=((c[j]-48)*pow(10,k-j-1));
                }
                printf("|%d", i);
                n+=i;
                i=k=0;
                if(tmp==' ') tmp = fgetc(fp);
            }
            printf("|%d\n", n);
            n=0;
        }
    }
}
