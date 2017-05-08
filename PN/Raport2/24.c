#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv)
{
    char* base = argv[1];
    char* searched = argv[2];
    char first = searched[0];

    int matches = 0;
    for(int i = 0; i < strlen(base); i++)
    {
        if(base[i] == first)
        {
            for(int j = 0; j < strlen(searched); j++)
            {
                if(base[i+j] != searched[j])
                    break;
                if(searched[j+1] == '\0')
                    matches++;
            }
        }
    }

    printf("%d\n", matches);
}
