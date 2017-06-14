#ifndef max
    #define max(a,b) ((a) > (b) ? (a) : (b))
#endif

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <math.h>

struct paczka
{
    int w;
    int s;
    float ratio;
} paczka;

struct paczka *paczki;

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

void genratio(int n)
{
    for(int i=0; i<n; i++)
        paczki[i].ratio = ((float)paczki[i].w)/((float)paczki[i].s);
}

void sminsort(int start, int end)
{
    int startstep = start;
    int endstep = end;
    int vpivot = paczki[(start+end)/2].s;
    while(startstep <= endstep)
    {
        while(paczki[startstep].s < vpivot)
            startstep++;
        while(paczki[endstep].s > vpivot)
            endstep--;

        if(startstep<=endstep)
        {
            struct paczka tmp = paczki[endstep];
            paczki[endstep] = paczki[startstep];
            paczki[startstep] = tmp;
            startstep++;
            endstep--;
        }
    }

    if(startstep<end)
        sminsort(startstep, end);
    if(endstep>start)
        sminsort(start, endstep);
}

void wmaxsort(int start, int end)
{
    int startstep = start;
    int endstep = end;
    int vpivot = paczki[(start+end)/2].w;
    while(startstep <= endstep)
    {
        while(paczki[startstep].w < vpivot)
            startstep++;
        while(paczki[endstep].w > vpivot)
            endstep--;

        if(startstep<=endstep)
        {
            struct paczka tmp = paczki[endstep];
            paczki[endstep] = paczki[startstep];
            paczki[startstep] = tmp;
            startstep++;
            endstep--;
        }
    }

    if(startstep<end)
        wmaxsort(startstep, end);
    if(endstep>start)
        wmaxsort(start, endstep);
}

void maxratiosort(int start, int end)
{
    int startstep = start;
    int endstep = end;
    float vpivot = paczki[(start+end)/2].ratio;
    while(startstep <= endstep)
    {
        while(paczki[startstep].ratio < vpivot)
            startstep++;
        while(paczki[endstep].ratio > vpivot)
            endstep--;

        if(startstep<=endstep)
        {
            struct paczka tmp = paczki[endstep];
            paczki[endstep] = paczki[startstep];
            paczki[startstep] = tmp;
            startstep++;
            endstep--;
        }
    }

    if(startstep<end)
        maxratiosort(startstep, end);
    if(endstep>start)
        maxratiosort(start, endstep);
}

void dynamiczne(int n, int b)
{
    float pdtime = startClock();
    int **chart = malloc(sizeof(int*)*(b+1));
    for(int i=0; i<=b; i++)
    {
        chart[i] = malloc(sizeof(int)*(n+1));
        for(int j=0; j<=n; j++)
        {
            if(i==0 || j==0)
                chart[i][j] = 0;
            else if(paczki[j-1].s > i)
                chart[i][j] = chart[i][j-1];
            else
                chart[i][j] = max(chart[i][j-1],(chart[i-paczki[j-1].s][j-1] + paczki[j-1].w));
        }
    }

    for(int i=n, j=b; i>0; i--)
    {
        if(chart[j][i] != chart[j][i-1])
            j-=paczki[i-1].s;
    }

    pdtime = stopClock(pdtime);
    printf("PD: t: %lf, w: %d\n", pdtime, chart[b][n]);
}

void bf1(int n, int b)
{
    float bf1time = startClock();
    int maxw = 0;
    long p = pow(2,n);
    int iter = 0;
    int tw = 0;
    int ts = 0;
    for(long i=0; i<p; i++)
    {
        iter = 0;
        tw = 0;
        ts = 0;
        for(long j=i; j>0; j/=2)
        {
            if(j%2)
            {
                tw+=paczki[iter].w;
                ts+=paczki[iter].s;
            }
            iter++;
        }
        if(ts<=b && tw>maxw)
            maxw = tw;
    }
    bf1time = stopClock(bf1time);
    printf("BF1: t: %lf, w: %d\n", bf1time, maxw);
}

int result;
void bf2recur(int i, int ts, int tw, int n, int b)
{
    if(i>=n)
    {
        if(tw > result)
            result = tw;
        return;
    }
    if(ts+paczki[i].s <= b)
        bf2recur(i+1, ts+paczki[i].s, tw+paczki[i].w, n, b);
    bf2recur(i+1, ts, tw, n, b);
    if(tw > result)
        result = tw;
}

void bf2(int n, int b)
{
    float bf2time = startClock();
    result = 0;
    bf2recur(0, 0, 0, n, b);
    bf2time = stopClock(bf2time);
    printf("BF2: t: %lf, w: %d\n", bf2time, result);
}

void gh1(int n, int b)
{
    float gh1time = startClock();
    int ts = 0;
    int tw = 0;
    int ti;
    int *odwiedz = malloc(sizeof(int)*n);
    for(int i=0;i<n;i++)odwiedz[i]=0;

    int nodwiedz = 1;
    while(ts<b)
    {
        ti=rand()%n;
        if((ts+paczki[ti].s)<=b)
        {
            if(!odwiedz[ti])
            {
                tw+=paczki[ti].w;
                ts+=paczki[ti].s;
            }
        }
        else
            break;

        odwiedz[ti]=1;
        for(int i=0;i<n;i++)
            if(!odwiedz[i]) nodwiedz=0;

        if(!nodwiedz)
        {
            nodwiedz = 1;
            break;
        }
    }
    gh1time = stopClock(gh1time);
    printf("GH1: t: %lf, w: %d\n", gh1time, tw);
    free(odwiedz);
}

void gh2(int n, int b)
{
    float gh2time = startClock();
    sminsort(0,n-1);
    int ts=0;
    int tw=0;
    for(int i=0; ts<b && i<n; i++)
        if(ts+paczki[i].s<b)
        {
            tw+=paczki[i].w;
            ts+=paczki[i].s;
        }
    gh2time = stopClock(gh2time);
    printf("GH2: t: %lf, w: %d\n", gh2time, tw);
}

void gh3(int n, int b)
{
    float gh3time = startClock();
    wmaxsort(0,n-1);
    int ts=0;
    int tw=0;
    for(int i=n-1; ts<b && i>=0; i--)
        if(ts+paczki[i].s<b)
        {
            tw+=paczki[i].w;
            ts+=paczki[i].s;
        }
    gh3time = stopClock(gh3time);
    printf("GH3: t: %lf, w: %d\n", gh3time, tw);
}

void gh4(int n, int b)
{
    float gh4time = startClock();
    genratio(n);
    maxratiosort(0,n-1);
    int ts=0;
    int tw=0;
    for(int i=n-1; ts<b && i>=0; i--)
        if(ts+paczki[i].s<b)
        {
            tw+=paczki[i].w;
            ts+=paczki[i].s;
        }
    gh4time = stopClock(gh4time);
    printf("GH4: t: %lf, w: %d\n", gh4time, tw);
}

int main(int argc, char **argv)
{
    srand(time(NULL));
    int n = atoi(argv[1]); //ilość paczek
    float bratio = atof(argv[2]); //procentowy rozmiar plecaka
    int sums = 0;
    paczki = malloc(sizeof(struct paczka)*n);
    for(int i=0; i<n; i++)
    {
        paczki[i].s = (rand()%990)+10; //Wg zadania: s=[10,1000]
        sums += paczki[i].s;
        paczki[i].w = (rand()%9900)+100; //Pomyliło się, inaczej niż w zadaniu w=[10, 9910]
    }
    int b = (int)(sums*bratio);
    printf("#####TEST n: %d, d: %d%%#####\n", n, (int)(bratio*100));
    dynamiczne(n, b);
    bf1(n,b);
    bf2(n,b);
    gh1(n,b);
    gh2(n,b);
    gh3(n,b);
    gh4(n,b);
}
