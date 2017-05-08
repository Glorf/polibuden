#include <stdlib.h>
#include <stdio.h>
#include <string.h>

struct element
{
    double value;
    struct element *next;
} element;

int val(char tab[])
{
    int value=0;
    for(int i=0; i<strlen(tab); i++)
    {
        value*=10;
        value+=(int)(tab[i]-'0');
    }
    return value;
}

int main()
{
    char table[100];
    struct element *d,*p=NULL;
    double b,v,g;
    scanf("%s",table);
    while(table[0]<'a'||table[0]>'z')
    {
        v=p->value;
        d=p;
        p=p->next;
        switch(table[0])
        {
            case '+':
                p->value+=v;
                break;
            case '*':            
                p->value*=v;
                break;
            case '-':
                p->value-=v;
                break;
            case '/':
                p->value/=v;
                break;
            default:
                b=val(table);
                d=malloc(sizeof(element));
                d->value=b;
                d->next=p;
                p=d;
                break;
        }
        scanf("%s",table);
    }
    while(p)
    {
        printf("%lf" ,p->value);
        d=p;
        p=p->next;
        free(d);
    }
}

