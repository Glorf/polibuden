#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void sub(char *stary, char *nowy, char *tablica)
{
    char *p = strstr(tablica, stary);
    int found = 0;
    do  
    {   
        if(p)
        {
            char buf[1024];
            memset(buf,'\0',strlen(buf));

            if(tablica == p)
            {
                strcpy(buf,nowy);
                strcat(buf,p+strlen(stary));  
            }
            else
            {
                strncpy(buf,tablica,strlen(tablica) - strlen(p));
                strcat(buf,nowy);
                strcat(buf,p+strlen(stary));
            }

            memset(tablica,'\0',strlen(tablica));
            strcpy(tablica,buf);
            found = 1;
        }   

    } while(!found && p && (p = strstr(tablica, stary)));
}

int index_n(char *stary, char *nowy, char *tablica)
{
    char *p = strstr(tablica, stary);
    int result = 0;
    int found = 0;
    do  
    {   
        if(p)
        {
            char buf[1024];
            memset(buf,'\0',strlen(buf));

            if(tablica == p)
            {
                strcpy(buf,nowy);
                strcat(buf,p+strlen(stary));  
            }
            else
            {
                strncpy(buf,tablica,strlen(tablica) - strlen(p));
                strcat(buf,nowy);
                strcat(buf,p+strlen(stary));
            }

            memset(tablica,'\0',strlen(tablica));
            strcpy(tablica,buf);
            found = 1;
            return result;
        }
        result++;   

    } while(!found && p && (p = strstr(tablica, stary)));
}

void split(char *tablica, char *delim, char *wynik)
{
    wynik = strtok(tablica, delim);
}


void gsub(char *stary, char *nowy, char *tablica)
{
    char *p = strstr(tablica, stary);
    do  
    {   
        if(p)
        {
            char buf[1024];
            memset(buf,'\0',strlen(buf));

            if(tablica == p)
            {
                strcpy(buf,nowy);
                strcat(buf,p+strlen(stary));  
            }
            else
            {
                strncpy(buf,tablica,strlen(tablica) - strlen(p));
                strcat(buf,nowy);
                strcat(buf,p+strlen(stary));
            }

            memset(tablica,'\0',strlen(tablica));
            strcpy(tablica,buf);
        }   

    }while(p && (p = strstr(tablica, stary)));
}

char *gensub(char *stary, char *nowy, char *tablica)
{
    char *result = malloc(sizeof(char)*1024);
    char *p = strstr(tablica, stary);
    do  
    {   
        if(p)
        {
            char buf[1024];
            memset(buf,'\0',strlen(buf));

            if(tablica == p)
            {
                strcpy(buf,nowy);
                strcat(buf,p+strlen(stary));  
            }
            else
            {
                strncpy(buf,tablica,strlen(tablica) - strlen(p));
                strcat(buf,nowy);
                strcat(buf,p+strlen(stary));
            }

            memset(tablica,'\0',strlen(tablica));
            strcpy(result,buf);
        }   

    }while(p && (p = strstr(tablica, stary)));
    return result;
}

int main(){}
