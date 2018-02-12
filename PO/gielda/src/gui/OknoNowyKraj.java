package gui;

import helpery.Baza;
import helpery.Kraj;

import javax.swing.*;
import java.awt.*;

/**
 * Okno dodawania nowego kraju
 */
public class OknoNowyKraj extends JFrame {
    public OknoNowyKraj() {
        super("Dodaj kraj");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,2));
        add(panel);
        JLabel label1 = new JLabel("Kod kraju", JLabel.CENTER);
        panel.add(label1);
        JTextField kod = new JTextField();
        kod.setText("SE");
        panel.add(kod);
        JLabel label2 = new JLabel("Nazwa kraju", JLabel.CENTER);
        panel.add(label2);
        JTextField nazwa = new JTextField();
        nazwa.setText("San Escobar");
        panel.add(nazwa);
        JButton anuluj = new JButton("Anuluj");
        anuluj.addActionListener(e-> {
            setVisible(false);
            dispose();
        });
        panel.add(anuluj);
        JButton zapisz = new JButton("Zapisz");
        zapisz.addActionListener(e-> {
            Kraj kraj = new Kraj();
            kraj.setKod(kod.getText());
            kraj.setNazwa(nazwa.getText());
            Baza.get().addKraj(kraj);
            setVisible(false);
            dispose();
        });
        panel.add(zapisz);


        pack();
        setLocation(100, 100);
        setVisible(true);
    }
}
