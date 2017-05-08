#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct lista_n
{
    int v;
    struct lista_n *nastepny;
} lista_n;

double tsToSec(struct timespec time)
{
    return ((double) time.tv_sec + (time.tv_nsec / 1000000000.0)) ;
}

double startClock()
{
    struct timespec ts;
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &ts);
    return tsToSec(ts);
}

double stopClock(double startTime)
{
    struct timespec ts;
    clock_gettime(CLOCK_PROCESS_CPUTIME_ID, &ts);
    double newclock = tsToSec(ts);
    return (newclock-startTime);
}

void doTest(char *name, void (*fptr)(), void (*cleanptr)(), int nruns)
{
    double startTime, result = 0;

    for(int i=0; i<nruns; i++)
    {
        startTime = startClock();
        fptr();
        result += stopClock(startTime);
        if(cleanptr!=NULL)
            cleanptr();
    }

    if(name!=NULL)
        printf("%s: %fs\n", name, result/(double)nruns);
}

int **macierz;
struct lista_n **nlista;
int **llista;
int *stickers_in;
int *stickers_out;
int *visited;
int etiqui;
int n;
long long m;

void dfs_etiqu(int u)
{
	visited[u] = 1; 
    etiqui++;
    stickers_in[u] = etiqui;

	struct lista_n *next = nlista[u]; 

	while (next != NULL)
    { 
		if(visited[next->v]==0)
            dfs_etiqu(next->v);
		next = next->nastepny;
	}

    etiqui++;
	stickers_out[u] = etiqui;
}

int main(int argc, char **argv)
{
    //setvbuf(stdout, NULL, _IONBF, 0); //for debug purposes only
    n = atoi(argv[1]);
    float d = atof(argv[2]);
    m = (d*n*(n-1));
    srand(time(NULL));

    //GENERATOR:
    //Macierz sąsiedztwa
    macierz = malloc(n*sizeof(int*));
    for(int i=0; i<n; i++)
        macierz[i] = malloc(n*sizeof(int));
    
    for(int i=0; i<n; i++)
        memset(macierz[i], 0, sizeof(macierz[i]));

    long ra;
    long rb;
    for(long i=m; i>0;)
    {
        ra = rand()%n;
        rb = rand()%n;
        if(!macierz[ra][rb])
        {
            macierz[ra][rb] = 1;
            i--;
        }
    }

    //Lista następników 
    nlista = malloc(n*sizeof(struct lista_n*));
    for(int i=0; i<n; i++)
        nlista[i] = NULL;

    struct lista_n *poprzedni;

    long pm = 0;
    for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)
            if(macierz[i][j])
            {
                if(nlista[i] == NULL)
                {
                    nlista[i] = malloc(sizeof(struct lista_n));
                    nlista[i]->v = j;
                    nlista[i]->nastepny = NULL;
                    poprzedni = nlista[i];
                }
                else
                {
                    poprzedni->nastepny = malloc(sizeof(struct lista_n));
                    poprzedni->nastepny->v = j;
                    poprzedni->nastepny->nastepny = NULL;
                    poprzedni = poprzedni->nastepny;
                }
            }

    //Lista łuków
    llista = malloc(m*sizeof(int*));
    for(int i=0; i<m; i++)
    {
        llista[i] = malloc(2*sizeof(int));
    }
    int pos = 0;
    for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)
            if(macierz[i][j])
            {
                llista[pos][0] = i;
                llista[pos][1] = j;
                pos++;
            }

    //DFS
    stickers_in = malloc(sizeof(int)*n);
    stickers_out = malloc(sizeof(int)*n);
    visited = malloc(sizeof(int)*n);
    for(int i=0; i<n; i++)
        visited[i] = 0;

    etiqui = 0;
    double czastop;
    czastop = startClock();
    for(int i=0; i<n; i++)
	    if(!visited[i])
            dfs_etiqu(i);
    czastop = stopClock(czastop);

    printf("Sortowanie topologiczne: %lf\n", czastop);
    
    //Zliczanie lukow lista lukow
    double lpzlicz;

    int lp=0;
    lpzlicz = startClock();
	for (int i=0; i<m; i++) //dla kazdego łuku
		if(stickers_in[llista[i][1]] < stickers_in[llista[i][0]] && 
            stickers_out[llista[i][0]] < stickers_out[llista[i][1]] &&
            stickers_in[llista[i][0]] < stickers_out[llista[i][0]]) 
			lp++;

    lpzlicz = stopClock(lpzlicz);

    printf("Luki powrotne: %d / %d\n", lp, m);
    printf("Zliczanie po LP: %lf\n", lpzlicz);

    //Zliczanie lp, lista następników
    int ln = 0;
    double lnzlicz;

    lnzlicz = startClock();
    for(int i=0; i<n; i++)
    {
        struct lista_n *ptr = nlista[i];
        while(ptr != NULL)
        {
            if(stickers_in[ptr->v] < stickers_in[i] &&
                stickers_out[i] < stickers_out[ptr->v] &&
                stickers_in[i] < stickers_out[i])
                ln++;
            ptr = ptr->nastepny;
        }
    }

    lnzlicz = stopClock(lnzlicz);

    printf("Zliczanie po LN: %lf\n", lnzlicz);


    //Zliczanie lp, macierz sąsiedztwa
    int ms = 0;
    double mszlicz;

    mszlicz = startClock();
    for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)
            if(macierz[i][j] && stickers_in[j] < stickers_in[i] &&
                stickers_out[i] < stickers_out[j] &&
                stickers_in[i] < stickers_out[i])
                ms++;   

    mszlicz = stopClock(mszlicz);

    printf("Zliczanie po MS: %lf\n", mszlicz);
}















