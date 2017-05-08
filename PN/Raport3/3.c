#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct student
{
    char imie[30];
    char nazwisko[30];
    int nr_albumu;
    float ocena;
} student;

struct student studtable[100];
int istud = 0;

void N(int nr_albumu, char *imie, char *nazwisko, float ocena) //NEW
{
    struct student nstud;
    strcpy(nstud.imie, imie);
    strcpy(nstud.nazwisko, nazwisko);
    nstud.nr_albumu = nr_albumu;
    nstud.ocena = ocena;
    studtable[istud] = nstud;
    istud++;
}

void P() //PRINT
{
    for(int i=0; i<istud; i++)
        printf("%d\t%s\t%s\t%f\n", studtable[i].nr_albumu, studtable[i].nazwisko, studtable[i].imie, studtable[i].ocena);
}

void C(int nr_albumu, char *imie, char *nazwisko, float ocena) //CHANGE
{
    for(int i=0; i<istud; i++)
    {
        if(nr_albumu!=studtable[i].nr_albumu)
            continue;
        struct student *nstud = &studtable[i];
        strcpy(nstud->imie, imie);
        strcpy(nstud->nazwisko, nazwisko);
        nstud->ocena = ocena;
        break;
    }
}

void D() //DELETE
{
    istud = 0;
}

float M() //MEDIAN
{
    float counter = 0;
    for(int i=0; i<istud; i++)
        counter += studtable[i].ocena;

    return counter/istud;
}

float H() //HIGHEST
{
    float highest = 0;
    for(int i=0; i<istud; i++)
        if(studtable[istud].ocena > highest)
            highest = studtable[istud].ocena;

    return highest;
}

float L() //LOWEST
{
    float lowest = 10;
    for(int i=0; i<istud; i++)
        if(studtable[istud].ocena < lowest)
            lowest = studtable[istud].ocena;

    return lowest;
}

int main(int argc, char **argv) {
    printf("> ");
    char func;
    char imie[20];
    char nazw[20];
    int nalb;
    float ocen;

    while(1)
    {
        scanf("%c", &func);
        switch(func)
        {
            case 'K':   return 0;
            case 'D':   D();  printf("\n> ");  break;
            case 'P':   P();  printf("\n> ");  break;
            case 'M':   printf("%f\n> ", M());  break;
            case 'H':   printf("%f\n> ", H());  break;
            case 'L':   printf("%f\n> ", L());  break;
            case 'N':   scanf("%d %s %s %f", &nalb, &imie, &nazw, &ocen);    N(nalb, imie, nazw, ocen); printf("> "); break;
            case 'C':   scanf("%d %s %s %f", &nalb, &imie, &nazw, &ocen);    C(nalb, imie, nazw, ocen); printf("> "); break;
        }
    }
    return 0;
}
