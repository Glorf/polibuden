#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

struct owlist //one-way list
{
    int value;
    struct owlist *nextPtr;
};

struct bst //binary-search tree
{
    int value;
    struct bst *tl;
    struct bst *tr;
};

int *A; //unsorted list
int *B; //sorted list
int *C; //helper table for CTB
int Ci;
int n; //number of elements
struct owlist *rootPtr;
struct bst *trRoot;
struct bst *tbRoot;

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

void quicksort(int table[], int start, int end)
{
    int startstep = start;
    int endstep = end;
    int vpivot = table[(start+end)/2];
    while(startstep <= endstep)
    {
        while(table[startstep] < vpivot)
            startstep++;
        while(table[endstep] > vpivot)
            endstep--;

        if(startstep<=endstep)
        {
            int tmp = table[endstep];
            table[endstep] = table[startstep];
            table[startstep] = tmp;
            startstep++;
            endstep--;
        }
    }

    if(startstep<end)
        quicksort(table, startstep, end);
    if(endstep>start)
        quicksort(table, start, endstep);
}

void CB()
{
    //Create table B
    B = malloc(n*sizeof(int));
    memcpy(B, A, n*sizeof(int));

    //Sort table B
    quicksort(B, 0, n-1);
}

void CBfree()
{
    free(B);
}

void SSB()
{
    //Simple search in B
    for(int i=0; i<n; i++)
    {
        for(int j=0; j<n; j++)
        {
            if(A[i] == B[j])
                break;
        }
    }
}

void SBBrecursion(int start, int end, int searched)
{
    int pivot = (start+end)/2;
    if(searched == B[pivot])
        return;
    else if((end-start)>1 && searched > B[pivot])
        SBBrecursion(pivot+1, end, searched);
    else if((end-start)>1)
        SBBrecursion(start, pivot-1, searched);
}

void SBB()
{
    //Division search in B
    for(int i=0; i<n; i++)
    {
        SBBrecursion(0, n-1, A[i]);
    }
}

void CL()
{
    struct owlist *lastPtr = malloc(sizeof(struct owlist));
    lastPtr->value = A[0];
    rootPtr = lastPtr;
    for(int i=1; i<n; i++)
    {
        struct owlist *next = malloc(sizeof(struct owlist));
        next->value = A[i];
        next->nextPtr = NULL;
        lastPtr->nextPtr = next;
        lastPtr = next;
    }
}

void CLfreeRecursion(struct owlist *ptr)
{
    if(ptr->nextPtr != NULL)
        CLfreeRecursion(ptr->nextPtr);
    free(ptr);
}

void CLfree()
{
    CLfreeRecursion(rootPtr);
}

void SL()
{
    struct owlist *ptr;
    for(int i=0; i<n; i++)
    {
        ptr = rootPtr;
        while(ptr->value != A[i])
        {
            ptr = ptr->nextPtr;
        }
    }
}

void BSTfreeRecursion(struct bst *ptr)
{
    if(ptr->tl != NULL)
        BSTfreeRecursion(ptr->tl);
    if(ptr->tr != NULL)
        BSTfreeRecursion(ptr->tr);
    free(ptr);
}

void CTRfree() //LPk listing removal
{
    BSTfreeRecursion(trRoot);
}

void CTRrecursion(struct bst *lastTr, int value)
{
    if(value > lastTr->value)
    {
        if(lastTr->tr==NULL)
        {
            struct bst *nextTr = malloc(sizeof(struct bst));
            nextTr->value = value;
            nextTr->tl = NULL;
            nextTr->tr = NULL;
            lastTr->tr = nextTr;
        }
        else
            CTRrecursion(lastTr->tr, value);
    }
    else
    {
        if(lastTr->tl==NULL)
        {
            struct bst *nextTr = malloc(sizeof(struct bst));
            nextTr->value = value;
            nextTr->tl = NULL;
            nextTr->tr = NULL;
            lastTr->tl = nextTr;
        }
        else
            CTRrecursion(lastTr->tl, value);
    }
}

void CTR()
{
    trRoot = malloc(sizeof(struct bst));
    trRoot->value = A[0];
    trRoot->tl = NULL;
    trRoot->tr = NULL;
    for(int i=1; i<n; i++)
    {
        CTRrecursion(trRoot, A[i]);
    }
}

int HTRrecursion(struct bst *lastTr, int h)
{
    int newh = h;
    int new2h = h;
    if(lastTr->tl != NULL)
        newh = HTRrecursion(lastTr->tl, h+1); 
    if(lastTr->tr != NULL)
        new2h = HTRrecursion(lastTr->tr, h+1);

    if(newh > new2h)
        return newh;
    else
        return new2h;
}

void HTR()
{
    printf("HTR: %d\n", HTRrecursion(trRoot, 1));
}

void STR()
{
    struct bst *ptr;
    for(int i=0; i<n; i++)
    {
        ptr = trRoot;
        while(ptr->value != A[i])
        {
            if(A[i] > ptr->value)
                ptr = ptr->tr;
            else if(A[i] < ptr->value)
                ptr = ptr->tl;
        }
    }
}

void CTBhelperRecursion(int start, int end)
{
    int pivot = (end+start)/2;
    C[Ci] = B[pivot];
    Ci++;
    if(start < pivot)
        CTBhelperRecursion(start, pivot-1);
    if(end > pivot)
        CTBhelperRecursion(pivot+1, end);
}

void CTBhelper()
{
    C = malloc(n*sizeof(int));
    Ci=0;
    CTBhelperRecursion(0, n-1);
}

void CTBfree()
{
    BSTfreeRecursion(tbRoot);
}

void CTB()
{
    tbRoot = malloc(sizeof(struct bst));
    tbRoot->value = C[0];
    tbRoot->tl = NULL;
    tbRoot->tr = NULL;
    for(int i=1; i<n; i++)
    {
        CTRrecursion(tbRoot, C[i]);
    }
}

void HTB()
{
    printf("HTB: %d\n", HTRrecursion(tbRoot, 1));
}

void STB()
{
    struct bst *ptr;
    for(int i=0; i<n; i++)
    {
        ptr = tbRoot;
        while(ptr->value != A[i])
        {
            if(A[i] > ptr->value)
                ptr = ptr->tr;
            else if(A[i] < ptr->value)
                ptr = ptr->tl;
        }
    }
}

int main(int argc, char **argv) {
    setvbuf(stdout, NULL, _IONBF, 0); //for debug purposes only
    n = atoi(argv[1]);
    A = malloc(n*sizeof(int));
    int si = 0;
    while (si<n && scanf("%d ", &A[si]) == 1)
        si++;

    doTest("CB", CB, CBfree, 10); //test with cleaning up
    CB(); //not a test, but list creator
    doTest("SSB", SSB, NULL, 10);
    doTest("SBB", SBB, NULL, 10);
    doTest("CL", CL, CLfree, 10); //test with cleaning up
    CL(); //not a test, but list creator
    doTest("SL", SL, NULL, 10);
    CLfree();
    doTest("CTR", CTR, CTRfree, 10); //test with cleaning up
    CTR(); //not a test, but tree creator
    HTR(); //listh
    doTest("STR", STR, NULL, 10);
    CTRfree();
    CTBhelper(); //fill CBT helper table C
    doTest("CTB", CTB, CTBfree, 10);
    CTB();
    HTB();
    doTest("STB", STB, NULL, 10);
    CTBfree();
}
