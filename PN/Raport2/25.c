#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv)
{
    int alphlen = 'z' - 'a' + 1;
    char alphabet[alphlen];
    char *input = argv[1];

    for(int i = 0; i < alphlen; i++)
    {
        alphabet[i] = 0;
    }

    int inputlen = strlen(input);
    int imax = 0;
    for(int i = 0; i < inputlen; i++)
    {
        char c = input[i];
        alphabet[(int)(c - 'a')] += 1;
        if(alphabet[(int)(c - 'a')] > alphabet[imax])
            imax = (int)(c - 'a');
    }

    printf("%c occured: %d times.\n", 'a' + imax, alphabet[imax]);
}
