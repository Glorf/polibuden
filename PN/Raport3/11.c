#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
int n; //number of element
struct owlist *rootPtr;
struct bst *trRoot;
struct bst *tbRoot;

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
