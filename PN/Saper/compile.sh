#!/bin/bash

exec gcc main.c logic.c allegro.c gui.c -lallegro -lallegro_font -lallegro_ttf -lallegro_primitives -lallegro_image -o Saper -Wall
