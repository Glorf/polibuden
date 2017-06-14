#include "logic.h"
#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <allegro5/allegro.h>

void load_config()
{
    FILE *config = fopen("./data/config", "r");
    if(!config)
        printf("READ ERROR!");
    fscanf(config, "%d %d %d %d", &Gx, &Gy, &Gn, &level);
    fclose(config);
}

void load_records()
{
    FILE *frecords = fopen("./data/records", "r");
    if(!frecords)
        printf("READ ERROR!");
    for(int i=0;i<8;i++)
        fscanf(frecords, "%d", &records[i]);
    fclose(frecords);
}

void save_records()
{
    FILE *frecords = fopen("records", "w");
    if(!frecords)
        printf("WRITE ERROR!");
    for(int i=0;i<8;i++)
        fprintf(frecords, "%d ", records[i]);
    fclose(frecords);
}

void save_config()
{
    FILE *config = fopen("config", "w");
    if(!config)
        printf("WRITE ERROR!");
    fprintf(config, "%d %d %d %d", Gx, Gy, Gn, level);
    fclose(config);
}

void init_logic()
{
    srand(time(NULL));
    load_config();
    started = 0;
    GameOver = 0;
    editor = 0;
    flags = 0;
    run = 1;
    record = 0;
    last_time = 0;
    records = malloc(sizeof(int)*8);
    load_records();
}

int get_xtable(int xpos)
{
    return (xpos-1)/30;
}

int get_ytable(int ypos)
{
    if(ypos-82 < 0)
        return -1;
    else
        return (ypos-82)/30;
}

int count_approx_mines(int x, int y)
{
    int counter = 0;
    for(int i=-1; i<2; i++)
        for(int j=-1; j<2; j++)
        {
            if(x+i>=Gx||y+j>=Gy||x+i<0||y+j<0)
                continue;
            counter+=minestable[x+i][y+j].mine;
        }
    return counter;
}

void fill_mines(int x, int y)
{
    for(int i = 0; i<Gx; i++)
        for(int j=0; j<Gy; j++)
        {
            minestable[i][j].mine = 0;
        }

    for(int i = 0; i<Gn;)
    {
        int rx = rand()%Gx;
        int ry = rand()%Gy;
        if((rx != x || ry!= y) && minestable[rx][ry].mine == 0)
        {
            minestable[rx][ry].mine = 1;
            i++;
        }
    }
}

void uncover_tile(int x, int y)
{
    if(!started)
    {
        if(!editor)
            fill_mines(x,y);
        started = 1;
        start_time = al_get_time();
    }
    if(minestable[x][y].mine)
    {
        minestable[x][y].state = EXPLODED;
        GameOver = 1;
        for(int i=0; i<Gx; i++)
            for(int j=0; j<Gy; j++)
            {
                if(minestable[i][j].mine && (i!=x || j!=y) && minestable[i][j].state != CHECKED)
                    minestable[i][j].state = MINE;
                else if(minestable[i][j].state == CHECKED && !minestable[i][j].mine)
                    minestable[i][j].state = INC_CHECKED;
            }
    }
    else if(!minestable[x][y].mine)
    {
        int mines = count_approx_mines(x,y);
        minestable[x][y].state = EMPTY+mines;
        if(mines == 0)
            recursive_uncover_empty(x,y);
    }
}

void recursive_uncover_empty(int x, int y)
{
    for(int i=-1; i<2; i++)
        for(int j=-1; j<2; j++)
        {
            if(x+i>=Gx||y+j>=Gy||x+i<0||y+j<0)
                continue;
            if(minestable[x+i][y+j].state == COVERED || minestable[x+i][y+j].state == CHECKED)
            {
                if(minestable[x+i][y+j].state == CHECKED)
                    flags--;
                if(count_approx_mines(x+i, y+j) == 0)
                {
                    minestable[x+i][y+j].state = EMPTY;
                    recursive_uncover_empty(x+i, y+j);
                }
                else
                    minestable[x+i][y+j].state = EMPTY+count_approx_mines(x+i, y+j);
            }
        }
}

void set_success()
{
    if(!GameOver)
        for(int i=0; i<Gx; i++)
            for(int j=0; j<Gy; j++)
                if(minestable[i][j].mine && minestable[i][j].state != CHECKED)
                    minestable[i][j].state = MINE;
    last_time = (int)(al_get_time()-start_time);
    if(level!=0 && (int)(al_get_time()-start_time)<records[level-1])
    {
        record = 1;
        records[level-1] = (int)(al_get_time()-start_time);
        save_records();
    }
    GameOver = -1;
}
