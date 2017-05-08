#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Plyta
{
    int np;
    char tytul[20];
    char autor[20];
    int rok;
    char rodzaj[20];
} Plyta;

struct Plyta plytable[100];
int iplyt = 0;

struct Plyta wczytajPlyte()
{
    struct Plyta nplyta;
    scanf("%s %s %d %s", &nplyta.tytul, &nplyta.autor, &nplyta.rok, &nplyta.rodzaj);
    nplyta.np = iplyt+1;
    plytable[iplyt] = nplyta;
    iplyt++;
}

void wyswietlPlyte(struct Plyta obiekt)
{
    printf("%s\t%s\t%d\t%s\n", obiekt.tytul, obiekt.autor, obiekt.rok, obiekt.rodzaj);
}

void P()
{
    for(int i=0; i<iplyt; i++)
        wyswietlPlyte(plytable[i]);
}

int porownaj(struct Plyta pl1, struct Plyta pl2)
{
    if(pl1.rok < pl2.rok)
        return 1;
    else
        return 0;
}

void B() //sort by year
{
    for(int i=0; i<iplyt; i++)
    {
        int mIndex=i;
        for(int j=i+1; j<iplyt; j++) //Szukamy najmniejszej
        {
            if(porownaj(plytable[j], plytable[mIndex]))
                mIndex = j;
        }

        struct Plyta tmp = plytable[mIndex];
        plytable[mIndex] = plytable[i];
        plytable[i] = tmp;
    }
}

int main(int argc, char **argv) {
    printf("> ");
    char func;

    while(1)
    {
        scanf("%c", &func);
        switch(func)
        {
            case 'P':   P();  printf("\n> ");  break;
            case 'B':   B();  printf("\n> ");  break;
            case 'N':   wczytajPlyte(); printf("> "); break;
        }
    }
    return 0;
}
