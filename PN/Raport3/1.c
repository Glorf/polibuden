#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct pracownik
{
    char nazwisko[30];
    int pensja;
    int premia;
} pracownik;

struct pracownik practable[100];
int iprac = 0;

void N(char *nazwisko, int pensja, int premia)
{
    struct pracownik nprac;
    strcpy(nprac.nazwisko, nazwisko);
    nprac.pensja = pensja;
    nprac.premia = premia;
    practable[iprac] = nprac;
    iprac++;
}

void P(const char *nazwisko, int pensja)
{
    for(int i=0; i<=iprac; i++)
    {
        if(strcmp(nazwisko, practable[i].nazwisko))
            continue;
        practable[i].pensja = pensja;
        break;
    }
}

void R(const char *nazwisko, int premia)
{
    for(int i=0; i<=iprac; i++)
    {
        if(strcmp(nazwisko, practable[i].nazwisko))
            continue;
        practable[i].premia = premia;
        break;
    }
}

float W()
{
    float counter = 0;
    for(int i=0; i<=iprac; i++)
        counter += practable[i].pensja + ((float)(practable[i].pensja*practable[i].premia)/100);

    return counter;
}


int main(int argc, char **argv) {
    printf("> ");
    char func;
    char cname[20];
    int val, val2;

    while(1)
    {
        scanf("%c", &func);
        switch(func)
        {
            case 'K':   return 0;;
            case 'W':   printf("%f\n> ", W());  break;
            case 'P':   scanf("%s %d", &cname, &val);    P(cname, val); printf("> ");  break;
            case 'R':   scanf("%s %d", &cname, &val);    R(cname, val); printf("> ");  break;
            case 'N':   scanf("%s %d %d", &cname, &val, &val2);    N(cname, val, val2); printf("> "); break;
            //default: return 0;
        }
    }
    return 0;
}
