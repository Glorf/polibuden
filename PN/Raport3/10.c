#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

char *decimal_to_binary(int n)
{
   int c, d, count;
   char *pointer;
 
   count = 0;
   pointer = (char*)malloc(32+1);
 
   for (c = 31; c>=0; c--)
   {
      d = n >> c;
      if(d & 1)
         *(pointer+count) = 1 + '0';
      else
         *(pointer+count) = 0 + '0';
      count++;
   }
   *(pointer+count) = '\0';
 
   return pointer;
}

int main(int argc, char **argv)
{
    int n = atoi(argv[1]); //DEC
    char *bin = decimal_to_binary(n); //BIN
    char oct[16];
    sprintf(oct, "%o", n); //OCT
    char hex[8];
    sprintf(hex, "%x", n);
    printf("%s %s %s", bin, oct, hex);

    return 0;
}
