#ifndef SAPER_LOGIC_H
#define SAPER_LOGIC_H


int Gx;
int Gy;
int Gn;
int GameOver;
int started;
int flags;
int run;
int level;
int *records;
double start_time;
int editor;
int last_time;
int record;

enum showstate
{
    COVERED,
    CHECKED,
    INC_CHECKED,
    MINE,
    EXPLODED,
    EMPTY,
    SURR1,
    SURR2,
    SURR3,
    SURR4,
    SURR5,
    SURR6,
    SURR7,
    SURR8
};

struct pole
{
    unsigned mine:1;
    enum showstate state;
};

struct pole minestable[100][100];

void load_config();
void save_config();
void load_records();
void save_records();
void init_logic();
int get_xtable(int xpos);
int get_ytable(int ypos);
int count_approx_mines(int x, int y);
void fill_mines(int x, int y);
void uncover_tile(int x, int y);
void recursive_uncover_empty(int x, int y);
void set_success();

#endif
