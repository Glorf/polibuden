#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct
{
    char character;
    int occurences;
} longest_sequence;

int main(int argc, char **argv)
{
    char* input_string = argv[1];
    longest_sequence longest, current;

    longest.character = '\0';
    longest.occurences = 0;

    for(int i = 0; i < strlen(input_string); i++)
    {
        current.character = input_string[i];
        current.occurences = 1;

        while(current.character == input_string[i+1])
        {
                current.occurences += 1;
                i++;
                if(i == '\0')
                    break;
        }

        if(current.occurences > longest.occurences)
        {
            longest.character = current.character;
            longest.occurences = current.occurences;
        }
    }

    printf("%c has occured most often: %d\n times", longest.character, longest.occurences);
}
