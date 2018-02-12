package gui;

import aktywa.Surowiec;

import javax.swing.*;
import java.awt.*;

/**
 * Panel okna podglądy aktywów pozwalający na wyświetlenie Surowca, samoodświeżający się
 */
public class PodgladSurowiec extends JPanel implements AktywaPanelInterface {
    private JLabel aktualnaCena;
    private JLabel minCena;
    private JLabel maxCena;
    private Surowiec surowiec;

    public PodgladSurowiec(Surowiec s) {
        super(new GridLayout(5,2));
        surowiec = s;
        JLabel label1 = new JLabel("Jednostka");
        add(label1);
        JLabel jednostka = new JLabel(s.getJednostka());
        add(jednostka);
        JLabel label2 = new JLabel("Waluta");
        add(label2);
        JLabel waluta = new JLabel(s.getWaluta().getNazwa());
        add(waluta);
        JLabel label3 = new JLabel("Aktualna cena");
        add(label3);
        aktualnaCena = new JLabel(String.format("%.2f", s.getCena()));
        add(aktualnaCena);
        JLabel label4 = new JLabel("Minimalna cena");
        add(label4);
        minCena = new JLabel(String.format("%.2f", s.getMinWartosc()));
        add(minCena);
        JLabel label5 = new JLabel("Maksymalna cena");
        add(label5);
        maxCena = new JLabel(String.format("%.2f", s.getMaxWartosc()));
        add(maxCena);
    }

    public void refresh() {
        aktualnaCena.setText(String.format("%.2f", surowiec.getCena()));
        minCena.setText(String.format("%.2f", surowiec.getMinWartosc()));
        maxCena.setText(String.format("%.2f", surowiec.getMaxWartosc()));
    }
}
