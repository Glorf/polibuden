#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv) {
    int key = atoi(argv[1]);
    char *arg = argv[2];
    char res[strlen(arg)];

    for(int i=0; i<strlen(arg);i++)
    {
        while(((int)arg[i]+key)>122)
            arg[i]-=26;
        while(((int)arg[i]+key)<97)
            arg[i]+=26;

        res[i] = arg[i]+=key;
    }
    printf("%s\n", res);

    return 0;
}
