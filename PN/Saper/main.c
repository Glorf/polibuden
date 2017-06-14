#include <stdio.h>
#include "allegro.h"
#include "logic.h"
#include "gui.h"

int main(int argc, char **argv)
{
    init_logic();
    if(allegro_init() == -1)
        return -1;
    init_gui();

    while(run)
    {
        al_clear_to_color(al_map_rgb(0,0,0));
        catch_events();

        int tabx = get_xtable(mstate.x);
        int taby = get_ytable(mstate.y);

        int uncovered = 0;
        for(int i=0; i<Gx; i++)
            for(int j=0; j<Gy; j++)
            {
                int state = minestable[i][j].state;
                if(state >= EMPTY)
                    uncovered++;

                if(state <= MINE)
                    allegro_element_drawer(i, j, al_map_rgb(167,167,167), state);
                else
                    allegro_element_drawer(i, j, al_map_rgb(230, 230, 230), state);
            }

        if(!GameOver)
        {
            if(tabx >= 0 && taby >= 0 && minestable[tabx][taby].state == COVERED)
                allegro_element_drawer(tabx, taby, al_map_rgb(200,200,200), minestable[tabx][taby].state);
            if(uncovered == Gx*Gy-Gn)
                set_success();
        }

        draw_menu();

        al_flip_display();
    }

    allegro_finish();

    return 0;
}

