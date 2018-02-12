package gui;

import aktywa.Aktywa;
import aktywa.ParaWalut;
import aktywa.Spolka;
import aktywa.Surowiec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Okno wyświetlające aktywa przy pomocy customowych JPaneli i Wykresu
 */

public class OknoAktywa extends JFrame implements ActionListener {
    Timer timer = new Timer(1000, this);

    AktywaPanelInterface panelAktywa;
    Wykres wykres;

    OknoAktywa(Aktywa a) {
        super(a.getNazwa());

        JMenuBar menuBar = new JMenuBar();
        JMenu plik = new JMenu("Plik");
        JMenuItem usun = new JMenuItem("Usuń");
        usun.addActionListener(e -> {
            setVisible(false);
            a.getRynek().rmAktywa(a);
            dispose();
        });
        plik.add(usun);
        menuBar.add(plik);
        setJMenuBar(menuBar);

        timer.start();
        JPanel globalPanel = new JPanel(new GridLayout(1,2));
        setContentPane(globalPanel);

        JPanel dataPanel = new JPanel(new GridLayout(1,1));
        globalPanel.add(dataPanel);
        wykres = new Wykres();
        wykres.addDane(a);
        globalPanel.add(wykres);

        if(a instanceof Spolka) {
            Spolka s = (Spolka)a;

            PodgladSpolka podgladSpolka = new PodgladSpolka(s);
            dataPanel.add(podgladSpolka);
            panelAktywa = podgladSpolka;
        }
        else if(a instanceof Surowiec) {
            Surowiec s = (Surowiec)a;

            PodgladSurowiec podgladSurowiec = new PodgladSurowiec(s);
            dataPanel.add(podgladSurowiec);
            panelAktywa = podgladSurowiec;
        }
        else if(a instanceof ParaWalut) {
            ParaWalut p = (ParaWalut)a;

            PodgladParaWalut podgladWaluta = new PodgladParaWalut(p);
            dataPanel.add(podgladWaluta);
            panelAktywa = podgladWaluta;
        }


        setSize(800,600);
        setLocation(100,100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ev){
        if(ev.getSource()==timer){
            panelAktywa.refresh();
            wykres.refresh();
        }

    }
}
