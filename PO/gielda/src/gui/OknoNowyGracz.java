package gui;

import gracz.Fundusz;
import gracz.Inwestor;
import helpery.Baza;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Okno dodawania nowego gracza
 */
public class OknoNowyGracz extends JFrame {
    public OknoNowyGracz() {
        super("Dodaj gracza");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel globalPanel = new JPanel(new GridLayout(4,1));
        setContentPane(globalPanel);

        JPanel panel1 = new JPanel(new GridLayout(1,2));
        globalPanel.add(panel1,0);
        JLabel label0 = new JLabel("Rodzaj");
        panel1.add(label0);
        JComboBox<String> choice = new JComboBox<>();
        choice.addItem("Fundusz");
        choice.addItem("Inwestor");
        choice.setSelectedIndex(0);
        panel1.add(choice);

        JTextField imie = new JTextField();
        imie.setText("Warren");
        JTextField nazwisko = new JTextField();
        nazwisko.setText("Buffet");
        JTextField nazwa = new JTextField();
        nazwa.setText("Berkshire Hatchaway");
        JTextField pesel = new JTextField();
        pesel.setText("00000000000");

        JPanel panelWspolny = new JPanel(new GridLayout(2,2));
        JLabel label1 = new JLabel("Imię", JLabel.CENTER);
        panelWspolny.add(label1);
        panelWspolny.add(imie);
        JLabel label2 = new JLabel("Nazwisko", JLabel.CENTER);
        panelWspolny.add(label2);
        panelWspolny.add(nazwisko);

        globalPanel.add(panelWspolny,1);

        JPanel panelFundusz = new JPanel(new GridLayout(1,2));
        JLabel label3 = new JLabel("Nazwa", JLabel.CENTER);
        panelFundusz.add(label3);
        panelFundusz.add(nazwa);

        globalPanel.add(panelFundusz,2);

        JPanel panelInwestor = new JPanel(new GridLayout(1,2));
        JLabel label4 = new JLabel("Pesel", JLabel.CENTER);
        panelInwestor.add(label4);
        panelInwestor.add(pesel);

        JPanel panelPrzyciskow = new JPanel(new GridLayout(1,2));
        JButton anuluj = new JButton("Anuluj");
        anuluj.addActionListener(e-> {
            setVisible(false);
            dispose();
        });
        JButton zapisz = new JButton("Zapisz");
        panelPrzyciskow.add(anuluj);
        panelPrzyciskow.add(zapisz);

        zapisz.addActionListener(f-> {
            Fundusz fundusz = new Fundusz(imie.getText(), nazwisko.getText(), nazwa.getText());
            Baza.get().addGracz(fundusz);

            setVisible(false);
            dispose();
        });

        choice.addActionListener(e -> {
            String selected = (String)choice.getSelectedItem();
            if(selected.equals("Fundusz")) {
                globalPanel.remove(2);
                globalPanel.add(panelFundusz,2);
                validate();
                repaint();

                clearListeners(zapisz);
                zapisz.addActionListener(f-> {
                    Fundusz fundusz = new Fundusz(imie.getText(), nazwisko.getText(), nazwa.getText());
                    Baza.get().addGracz(fundusz);

                    setVisible(false);
                    dispose();
                });
            }
            else if(selected.equals("Inwestor")) {
                globalPanel.remove(2);
                globalPanel.add(panelInwestor, 2);
                validate();
                repaint();

                clearListeners(zapisz);
                zapisz.addActionListener(f -> {
                    Inwestor inwestor = new Inwestor(imie.getText(), nazwisko.getText(), pesel.getText());
                    Baza.get().addGracz(inwestor);

                    setVisible(false);
                    dispose();
                });
            }
        });

        globalPanel.add(panelPrzyciskow,3);

        pack();
        setLocation(100, 100);
        setVisible(true);
    }

    public void clearListeners(AbstractButton b) {
        for(ActionListener l: b.getActionListeners()) {
            b.removeActionListener(l);
        }
    }
}
