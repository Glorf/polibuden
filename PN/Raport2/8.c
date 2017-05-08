#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int liczba = atoi(argv[1]);
    int druga = 1;

    while(liczba%druga==0)
        druga*=2;

    printf("%d %d", liczba/(druga/2), druga/2);

    return 0;
}
