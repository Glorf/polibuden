package gui;

import aktywa.Aktywa;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Majstersztyk na złotym firmamencie dziejów JPanelu, samoprzeliczający się kolorowy wielozadaniowy ploter
 */
public class Wykres extends JPanel {
    private List<Aktywa> dane;
    private List<Color> colors;

    public Wykres() {
        super();
        dane = new ArrayList<>();

        //Kolejne kolory na wykresie
        colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.MAGENTA);
        colors.add(Color.ORANGE);
        colors.add(Color.CYAN);
        colors.add(Color.BLACK);
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D draw = (Graphics2D)g;
        draw.clearRect(0, 0, this.getWidth(), this.getHeight());
        draw.setStroke(new BasicStroke(2));

        int heightDiv = (this.getHeight()-100)/4;
        int widthDiv = (this.getWidth()-100)/6;
        //WEWNĘTRZNE RAMKI
        draw.setColor(Color.LIGHT_GRAY);
        for(int i=1; i<4; i++) {
            draw.drawLine(50, 50+(heightDiv*i), getWidth()-50, 50+(heightDiv*i));
        }
        for(int i=1; i<6; i++) {
            draw.drawLine(50+(widthDiv*i), 50, 50+(widthDiv*i), getHeight()-50);
        }

        //RAMKI
        draw.setColor(Color.BLACK);
        draw.drawLine(50,50,50, this.getHeight()-50);
        draw.drawLine(50, 50, this.getWidth()-50, 50);
        draw.drawLine(50, this.getHeight()-50, this.getWidth()-50, this.getHeight()-50);
        draw.drawLine(getWidth()-50, 50, getWidth()-50, getHeight()-50);

        int timemax = 0;
        double cenamax = 0;
        for(Aktywa a: dane) {
            timemax = Math.max(timemax, a.getHistoria().size()*5);
            for(Double dana: a.getHistoria()) {
                cenamax = Math.max(cenamax, dana);
            }
        }

        for(int i=0; i<7; i++) {
            draw.drawString(Integer.toString(i*timemax / 6), 50 + i*widthDiv - 10, getHeight() - 20);
        }

        for(int i=0; i<5; i++) {
            draw.drawString(String.format("%.2f", cenamax*i/4), 10, getHeight() - 50 - i*heightDiv);
        }

        for(int i=0; i<dane.size(); i++) {
            draw.setColor(colors.get(i));

            List<Double> data = dane.get(i).getHistoria();

            int timeDelta = (timemax-(data.size()*5))/5;

            for(int j=0; j<data.size()-1; j++) {
                int xpos1 = 50+(int)((getWidth()-100)*((double)(j+timeDelta)/timemax*5));
                int xpos2 = 50+(int)((getWidth()-100)*((double)(j+timeDelta+1)/timemax*5));
                if(xpos1 == xpos2)
                    xpos2++;
                int ypos1 = (int)(getHeight()-50-((getHeight()-100)*(data.get(j)/cenamax)));
                int ypos2 = (int)(getHeight()-50-((getHeight()-100)*(data.get(j+1)/cenamax)));

                draw.drawLine(xpos1, ypos1, xpos2, ypos2);
            }
        }

        if(dane.size()>1) {
            for(int i=0; i<dane.size(); i++) {
                draw.setColor(colors.get(i));
                draw.drawString(dane.get(i).getNazwa(),getWidth()-150, 20*(i+1));
            }
        }
    }

    public void addDane(Aktywa a) {
        dane.add(a);
    }

    public void removeDane(Aktywa a) {
        dane.remove(a);
    }

    public void refresh() {
        repaint();
    }
}
