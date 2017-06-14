#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

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

int **macierz;
int **macierz_robocza;
int *visited;
long n;
long m;

void dfs(int u)
{
    visited[u] = 1;  
    for(int i=0; i<n; i++)
        if(macierz[u][i] && !visited[i])
            dfs(i);
}

void euler(long u)
{
    for(long i=0; i<n; i++)
		if(macierz_robocza[u][i])
        {
			macierz_robocza[u][i] = 0;
            macierz_robocza[i][u] = 0;
			euler(i);
		}
}

int create()
{
    //GENERATOR:
    //Macierz sąsiedztwa
    macierz = malloc(n*sizeof(int*));
    for(int i=0; i<n; i++)
        macierz[i] = malloc(n*sizeof(int));
    
    for(int i=0; i<n; i++)
        for(int j=0; j<n; j++)
            macierz[i][j]=0;

    long ra;
    long rb;
    for(long i=m; i>0;)
    {
        ra = rand()%n;
        rb = rand()%n;
        if(!macierz[ra][rb] && ra!=rb)
        {
            macierz[ra][rb] = 1;
            macierz[rb][ra] = 1;
            i--;
        }
    }

    //Sprawdzanie parzystości wierzchołków
    int add =1; //raz dodaje, raz odejmuje, żeby zachować m
    for(int i=0; i<n; i++)
    {
        int deg = 0;
        for(int j=0; j<n; j++)
            deg += macierz[i][j];

        while(deg%2) //sprawdzanie parzystości stopnia każdego wierzchołka
        {
            int r = rand()%n;
            if(add && !macierz[i][r] && i!=r) //losowe dodawanie krawędzi
            {
                macierz[i][r] = 1;
                macierz[r][i] = 1;
                deg++;
                m++;
                i=0;
                add = 0;
            }
            else if(!add && macierz[i][r] && i!=r) //losowe odejmowanie krawędzi
            {
                macierz[i][r] = 0;
                macierz[r][i] = 0;
                deg--;
                m--;
                i=0;
                add = 1;
            }
        }
    }

    //Sprawdzanie spójności grafu
    visited = malloc(sizeof(int)*n);
    memset(visited, 0, sizeof(visited));
    dfs(0);
    for(int i=0; i<n; i++)
        if(!visited[i])
        {
            //printf("BŁĄD: Graf niespójny!\n");
            return 0;
        }

    /*for(int i=0; i<n; i++)
    {
        for(int j=0; j<n; j++)
            printf("%d ", macierz[i][j]);
        printf("\n");
    }*/

    return 1;
}

double czash1;
int nvisited;
int found;
void hamilton1(int u)
{
    nvisited++;
    if(!found && macierz[u][0] && nvisited == n)
    {
        found = 1;
        czash1 = stopClock(czash1);
        printf("H1: %lf\n", czash1);
    }
    else if(!found)
    {
        visited[u] = 1; 
        for(int i=0; i<n; i++)
            if(macierz[u][i] && !visited[i])
                hamilton1(i);
        visited[u] = 0;
    }
    nvisited--;
}

void hamiltonA(int u)
{
    nvisited++;
    if(macierz[u][0] && nvisited == n)
        found++;
    else
    {
        visited[u] = 1; 
        for(int i=0; i<n; i++)
            if(macierz[u][i] && !visited[i])
                hamiltonA(i);
        visited[u] = 0;
    }
    nvisited--;
}

int main(int argc, char **argv)
{
    setvbuf(stdout, NULL, _IONBF, 0); //for debug purposes only
    n = atoi(argv[1]);
    float d = atof(argv[2]);
    m = (d*n*(n-1))/2;
    srand(time(NULL));

    while(!create())
        m = (d*n*(n-1))/2;

    printf("#### n=%d d=0.%d ####\n", n, (int)(d*10));

    //Szukanie cyklu Eulera
    macierz_robocza = malloc(n*sizeof(int*));
    for(int i=0; i<n; i++)
    {
        macierz_robocza[i] = malloc(n*sizeof(int));
        memcpy(macierz_robocza[i], macierz[i], sizeof(int)*n);
    }

    double czaseuler;
    czaseuler = startClock();
    euler(0);
    czaseuler = stopClock(czaseuler);
    printf("tE: %lf\n", czaseuler);

    //Szukanie jednego cyklu Hamiltona
    memset(visited, 0, n*sizeof(int));
    found=0;
    nvisited = 0;
    czash1 = startClock();
    hamilton1(0);
    //printf("tH1: %lf\n", czash1);

    //Szukanie wszystkich cykli Hamiltona
    for(int i=0;i<n;i++)
        visited[i] = 0;
    found=0;
    nvisited = 0;
    double czashA;
    czashA = startClock();
    hamiltonA(0);
    czashA = stopClock(czashA);
    printf("tHA: %lf\n", czashA);
    printf("cH: %d\n", found);
}
