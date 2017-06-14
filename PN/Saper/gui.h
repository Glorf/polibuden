#ifndef SAPER_GUI_H
#define SAPER_GUI_H

#include <allegro5/allegro.h>

int ibutton;
ALLEGRO_EVENT ev;
ALLEGRO_TIMEOUT timeout;
ALLEGRO_MOUSE_STATE mstate;
ALLEGRO_MOUSE_STATE hold;

struct button
{
    unsigned x;
    unsigned y;
    unsigned w;
    unsigned h;
    char text[10];
    void (*onClick)();
    //color
    int r;
    int g;
    int b;
};

struct button buttons[20];

void init_gui();
void create_button(int x, int w, int y, int h, char *text, void (*onClick)(), int r, int g, int b);
void draw_menu();
void draw_buttons();
void draw_statusbar();
void right_click_handle(int rx, int ry);
void left_click_handle(int rx, int ry);
void catch_events();

//GUI EVENTS
void restart();
void more_mines();
void less_mines();
void increase_x();
void decrease_x();
void increase_y();
void decrease_y();
void lvl_up();
void lvl_down();
void toggle_editor();

#endif
