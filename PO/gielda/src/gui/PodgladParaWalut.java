package gui;

import aktywa.ParaWalut;

import javax.swing.*;
import java.awt.*;

/**
 * Panel okna podglądy aktywów pozwalający na wyświetlenie ParWalut, samoodświeżający się
 */
public class PodgladParaWalut extends JPanel implements AktywaPanelInterface {
    private JLabel cena;
    private ParaWalut paraWalut;

    public PodgladParaWalut(ParaWalut w) {
        super(new GridLayout(1,2));

        paraWalut = w;
        JLabel label1 = new JLabel("Cena");
        add(label1);
        cena = new JLabel(String.format("%.2f", w.getCena()));
        add(cena);
    }

    public void refresh() {
        cena.setText(String.format("%.2f", paraWalut.getCena()));
        validate();
        repaint();
    }
}
