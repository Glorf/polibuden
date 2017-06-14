#include "allegro.h"
#include "logic.h"
#include <stdio.h>
#include <stdlib.h>

int allegro_init()
{
    if(!al_init()) {
        printf("failed to initialize allegro!\n");
        return -1;
    }

    if(!al_install_mouse())
    {
        printf("failed to initialize mouse!\n");
        return -1;
    }

    if(!al_init_image_addon())
    {
        printf("failed to load image addon!\n");
        return -1;
    }

    if(!al_init_font_addon())
    {
        printf("failed to load font addon!\n");
        return -1;
    }

    if(!al_init_ttf_addon())
    {
        printf("failed to load ttf addon!\n");
        return -1;
    }

    allegro_load_display();
    if(!display) {
        printf("failed to create display!\n");
        return -1;
    }

    allegro_set_path();
    allegro_load_images();
    allegro_load_font();

    event_queue = al_create_event_queue();
    if(!event_queue) {
        printf("failed to create event_queue!\n");
        return -1;
    }
 
    al_register_event_source(event_queue, al_get_display_event_source(display));
    al_register_event_source(event_queue, al_get_mouse_event_source());
    return 1;
}

void allegro_display_refresh()
{
    al_resize_display(display, (Gx*30)+1, Gy*(30)+1+80);
}

void allegro_load_display()
{
    display = al_create_display((Gx*30)+1, Gy*(30)+1+80);
}

void allegro_load_images()
{
    for(int i=0; i<15; i++)
        images[i] = NULL;

    images[MINE] = al_load_bitmap("mine.png");
    images[EXPLODED] = al_load_bitmap("exploded.png");
    images[CHECKED] = al_load_bitmap("flag.png");
    images[INC_CHECKED] = al_load_bitmap("incorrect.png");
    images[SURR1] = al_load_bitmap("1mines.png");
    images[SURR2] = al_load_bitmap("2mines.png");
    images[SURR3] = al_load_bitmap("3mines.png");
    images[SURR4] = al_load_bitmap("4mines.png");
    images[SURR5] = al_load_bitmap("5mines.png");
    images[SURR6] = al_load_bitmap("6mines.png");
    images[SURR7] = al_load_bitmap("7mines.png");
    images[SURR8] = al_load_bitmap("8mines.png");
}

void allegro_load_font()
{
    font = al_load_ttf_font("Cantarell-Regular.otf", 17, 0);
}

void allegro_set_path()
{
    ALLEGRO_PATH *path = al_get_standard_path(ALLEGRO_RESOURCES_PATH);
    al_append_path_component(path, "data");
    al_change_directory(al_path_cstr(path, '/'));
    al_destroy_path(path);
}

void allegro_element_drawer(int x, int y, ALLEGRO_COLOR color, int state)
{
    al_draw_filled_rounded_rectangle(1+(x*30), 1+(y*30)+80, 1+(x*30)+29, 1+(y*30)+29+80, 2, 2, color);
    if(images[state] != NULL)
        al_draw_bitmap(images[state], 1+(x*30)+2, 1+(y*30)+80+2, 0);
    else if(editor && minestable[x][y].mine)
        al_draw_bitmap(images[MINE], 1+(x*30)+2, 1+(y*30)+80+2, 0);
}


void allegro_button_drawer(int x, int y, int endx, int endy, ALLEGRO_COLOR color, char *text)
{
    al_draw_filled_rounded_rectangle(x, y, endx, endy, 5, 5, color);
    al_draw_text(font, al_map_rgb(0,0,0), x, y, 0, text);
}

void allegro_finish()
{
    al_destroy_display(display);
    al_destroy_event_queue(event_queue);
}
