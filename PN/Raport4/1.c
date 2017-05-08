#include <stdlib.h>
#include <stdio.h>

int main(int argc, char **argv)
{
    char *file = argv[1];
    char searched = argv[2][0];
    FILE *filePtr = fopen(file, "r");
    int count = 0;
    char tmp;
    while((tmp = getc(filePtr)) != EOF)
        if (tmp == searched)
            count++;
    if(file)
        printf("%c wystąpiło %d razy\n", searched, count, file);
}

