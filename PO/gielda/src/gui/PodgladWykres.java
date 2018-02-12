package gui;

import aktywa.Aktywa;
import helpery.Baza;
import rynek.Rynek;

import javax.swing.*;
import java.awt.*;

/**
 * Panel okna podglądu aktywów pozwalający na wyświetlenie Wykresu
 */
public class PodgladWykres extends JPanel{
    private Wykres wykres;

    public PodgladWykres() {
        super(new GridLayout(2,1));

        Timer timer = new Timer(1000, e -> refresh());
        timer.start();

        wykres = new Wykres();
        add(wykres);
        JPanel wyborAktywow = new JPanel(new GridLayout(1,3));
        add(wyborAktywow);

        DefaultListModel<Aktywa> wszystkieAktywa = new DefaultListModel<>();
        JList<Aktywa> wszystkieLista = new JList<>(wszystkieAktywa);
        JScrollPane wszystkieRozwijana = new JScrollPane(wszystkieLista);
        wyborAktywow.add(wszystkieRozwijana);

        for(Rynek<Aktywa> r: Baza.get().getRynki()) {
            for(Aktywa a: r.getAktywa()) {
                wszystkieAktywa.addElement(a);
            }
        }

        JPanel przyciski = new JPanel(new GridLayout(2,1));
        wyborAktywow.add(przyciski);

        DefaultListModel<Aktywa> wybraneAktywa = new DefaultListModel<>();
        JList<Aktywa> wybraneLista = new JList<>(wybraneAktywa);
        JScrollPane wybraneRozwijana = new JScrollPane(wybraneLista);
        wyborAktywow.add(wybraneRozwijana);

        JButton dodaj = new JButton("->");
        dodaj.addActionListener(e -> {
            Aktywa a = wszystkieLista.getSelectedValue();
            wybraneAktywa.addElement(a);
            wszystkieAktywa.removeElement(a);
            wykres.addDane(a);
        });
        przyciski.add(dodaj);

        JButton usun = new JButton("<-");
        usun.addActionListener(e -> {
            Aktywa a = wybraneLista.getSelectedValue();
            wszystkieAktywa.addElement(a);
            wybraneAktywa.removeElement(a);
            wykres.removeDane(a);
        });
        przyciski.add(usun);
    }

    public void refresh() {
        wykres.refresh();
    }
}
