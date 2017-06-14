#include "gui.h"
#include "allegro.h"
#include "logic.h"
#include <string.h>
#include <stdio.h>

void init_gui()
{
    ibutton = 0;
    create_button(10, 60, 50, 25, " Restart", &restart, 167, 167, 167);
    create_button(70, 30, 19, 18, "  –", &less_mines, 167, 167, 167);
    create_button(70, 30, 0, 18, "  +", &more_mines, 167, 167, 167);
    create_button(140, 30, 19, 18, "  –", &decrease_x, 167, 167, 167);
    create_button(140, 30, 0, 18, "  +", &increase_x, 167, 167, 167);
    create_button(210, 30, 19, 18, "  –", &decrease_y, 167, 167, 167);
    create_button(210, 30, 0, 18, "  +", &increase_y, 167, 167, 167);
    create_button(130, 30, 59, 18, "  –", &lvl_down, 167, 167, 167);
    create_button(130, 30, 40, 18, "  +", &lvl_up, 167, 167, 167);
    create_button(10, 30, 30, 18, "Edit", &toggle_editor, 167, 167, 167);
}

void create_button(int x, int w, int y, int h, char *text, void (*onClick)(), int r, int g, int b)
{
    struct button butt;
    butt.x = x;
    butt.y = y;
    butt.w = w;
    butt.h = h;
    butt.onClick = onClick;
    strcpy(butt.text, text);
    butt.r = r;
    butt.g = g;
    butt.b = b;

    buttons[ibutton] = butt;
    ibutton++;
}

void draw_buttons()
{
    struct button *butt;
    for(int i=0; i<ibutton; i++)
    {
        butt = &buttons[i];
        allegro_button_drawer(butt->x, butt->y, butt->x+butt->w, butt->y+butt->h, al_map_rgb(butt->r, butt->g, butt->b), butt->text);
    }
}

void right_click_handle(int rx, int ry)
{
    int x = get_xtable(rx);
    int y = get_ytable(ry);
    if(editor && x>=0 && y>=0)
    {
        minestable[x][y].mine = 0;
        less_mines();
    }
    else if(x>=0 && y>=0 && !GameOver)
    {
        if(minestable[x][y].state == COVERED)
        {
            flags++;
            minestable[x][y].state = CHECKED;
        }
        else if(minestable[x][y].state == CHECKED)
        {
            minestable[x][y].state = COVERED;
            flags--;
        }
    }
}

void left_click_handle(int rx, int ry)
{
    int x = get_xtable(rx);
    int y = get_ytable(ry);
    if(editor && x>=0 && y>=0)
    {
        minestable[x][y].mine = 1;
        more_mines();
    }
    else if(x>=0 && y>=0 && minestable[x][y].state == COVERED && !GameOver)
        uncover_tile(x,y);
    else if(x>=0 && y<0)
        for(int i=0; i<ibutton; i++)
            if(rx >= buttons[i].x && rx <=buttons[i].x+buttons[i].w &&
                    ry >= buttons[i].y && ry <=buttons[i].y+buttons[i].h)
                buttons[i].onClick();
}

void draw_menu()
{
    al_draw_filled_rectangle(0, 0, Gx*30, 80, al_map_rgb(255,255,255));
    draw_statusbar();
    draw_buttons();
}

void draw_statusbar()
{
    char str[8];
    //BOMBS
    sprintf(str, "%d/%d", flags, Gn);
    al_draw_bitmap(images[CHECKED], 1, 5, 0);
    al_draw_text(font, al_map_rgb(0,0,0), 25, 5, 0, str);

    //SIZE
    sprintf(str, "X: %d", Gx);
    al_draw_text(font, al_map_rgb(0,0,0), 100, 5, 0, str);
    sprintf(str, "Y: %d", Gy);
    al_draw_text(font, al_map_rgb(0,0,0), 170, 5, 0, str);

    //TIMER
    int time;
    if(!started)
        time = 0;
    else if(GameOver == 0)
        time = (int)(al_get_time()-start_time);
    else if(GameOver == -1)
        time = last_time;
    else
        time = 0;
    sprintf(str, "%d", time);
    al_draw_filled_rectangle(180, 50, 230, 75, al_map_rgb(0,0,0));
    al_draw_text(font, al_map_rgb(0,255,0), 190, 50, 0, str);

    //LVL
    if(level == 0)
        sprintf(str, "LVL: ?");
    else
        sprintf(str, "LVL: %d", level);
    al_draw_text(font, al_map_rgb(0,0,0), 80, 50, 0, str);

    if(GameOver == -1)
    {
        al_draw_filled_rectangle(((Gx*30)+1)/2-30, (Gy*(30)+1+80)/2, ((Gx*30)+1)/2+75, (Gy*(30)+1+80)/2+20, al_map_rgb(255,255,0));
        al_draw_text(font, al_map_rgb(255,0,0), ((Gx*30)+1)/2-30, (Gy*(30)+1+80)/2, 0, "GRATULACJE!");
        if(record)
        {
            al_draw_filled_rectangle(((Gx*30)+1)/2-30, (Gy*(30)+1+80)/2+20, ((Gx*30)+1)/2+90, (Gy*(30)+1+80)/2+40, al_map_rgb(255,255,0));
            al_draw_text(font, al_map_rgb(255,0,0), ((Gx*30)+1)/2-30, (Gy*(30)+1+80)/2+20, 0, "NOWY REKORD!");
        }
    }
    else if(GameOver)
    {
        started = 0;
        al_draw_filled_rectangle(((Gx*30)+1)/2-30, (Gy*(30)+1+80)/2, ((Gx*30)+1)/2+75, (Gy*(30)+1+80)/2+20, al_map_rgb(255,255,0));
        al_draw_text(font, al_map_rgb(255,0,0), ((Gx*30)+1)/2-30, (Gy*(30)+1+80)/2, 0, "PRZEGRANA!");
    }
}

void catch_events()
{
    al_init_timeout(&timeout, 0.06);
    int get_event = al_wait_for_event_until(event_queue, &ev, &timeout);

    al_get_mouse_state(&mstate);

    if(get_event)
        switch(ev.type)
        {
            case ALLEGRO_EVENT_DISPLAY_CLOSE:
                run = 0;
            case ALLEGRO_EVENT_MOUSE_BUTTON_DOWN:
                al_get_mouse_state(&hold);
                break;
            case ALLEGRO_EVENT_MOUSE_BUTTON_UP:
                if(hold.buttons & 1)
                    left_click_handle(mstate.x, mstate.y);
                else if(hold.buttons & 2)
                    right_click_handle(mstate.x, mstate.y);
                break;
        }
}

//GUI EVENTS
void restart()
{
    allegro_display_refresh();
    GameOver = 0;
    flags = 0;
    started = 0;
    record = 0;
    for(int i=0; i<Gx; i++)
        for(int j=0; j<Gy; j++)
            minestable[i][j].state = COVERED;
}

void more_mines()
{
    if(Gn<=Gx*Gy)
    {
        Gn++;
        level = 0;
        save_config();
    }
    restart();
}

void less_mines()
{
    if(Gn>1)
    {
        Gn--;
        level = 0;
        save_config();
    }
    restart();
}

void increase_x()
{
    Gx++;
    level = 0;
    save_config();
    restart();
}

void decrease_x()
{
    if(Gx>8)
    {
        Gx--;
        level = 0;
        save_config();
    }
    restart();
}

void increase_y()
{
    Gy++;
    level = 0;
    save_config();
    restart();
}

void decrease_y()
{
    if(Gy>8)
    {
        Gy--;
        level = 0;
        save_config();
    }
    restart();
}

void recalc_lvl()
{
    Gx = Gy = 8 + (level-1)*3;
    Gn = (int)(((float)(level)/20)*Gx*Gy);
}

void lvl_up()
{
    if(level<8)
    {
        level++;
        recalc_lvl();
        save_config();
    }
    restart();
}

void lvl_down()
{
    if(level>1)
    {
        level--;
        recalc_lvl();
        save_config();
    }
    restart();
}

void toggle_editor()
{
    if(!editor)
    {
        editor = 1;
        Gn = 0;
        fill_mines(-1,-1);
        restart();
    }
    else if(editor)
    {
        editor = 0;
        GameOver = 0;
        restart();
        started = 1;
        start_time = al_get_time();
    }
}
