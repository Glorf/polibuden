#ifndef SAPER_ALLEGRO_H
#define SAPER_ALLEGRO_H

#include <allegro5/allegro.h>
#include <allegro5/allegro_image.h>
#include <allegro5/allegro_primitives.h>
#include <allegro5/allegro_ttf.h>
#include <allegro5/allegro_font.h>

ALLEGRO_FONT *font;
ALLEGRO_BITMAP *images[15];
ALLEGRO_DISPLAY *display;
ALLEGRO_EVENT_QUEUE *event_queue;

int allegro_init();
void allegro_load_display();
void allegro_load_images();
void allegro_load_font();
void allegro_set_path();
void allegro_element_drawer(int x, int y, ALLEGRO_COLOR color, int state);
void allegro_display_refresh();

void allegro_button_drawer(int x, int y, int endx, int endy, ALLEGRO_COLOR color, char *text);
void draw_table_element(int x, int y);
void allegro_finish();

#endif
