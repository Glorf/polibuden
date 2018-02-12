package gui;

import rynek.Gielda;
import helpery.Baza;
import helpery.Kraj;
import aktywa.Waluta;
import rynek.Rynek;
import rynek.RynekSurowcow;
import rynek.RynekWalut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Okno dodawania nowego rynku
 */
public class OknoNowyRynek extends JFrame {
    public OknoNowyRynek() {
        super("Dodaj rynek");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel globalPanel = new JPanel(new GridLayout(3,1));
        setContentPane(globalPanel);

        JPanel panel1 = new JPanel(new GridLayout(1,2));
        globalPanel.add(panel1,0);
        JLabel label0 = new JLabel("Rodzaj");
        panel1.add(label0);
        JComboBox<String> choice = new JComboBox<>();
        choice.addItem("Giełda");
        choice.addItem("Rynek walut");
        choice.addItem("Rynek surowców");
        choice.setSelectedIndex(0);
        panel1.add(choice);

        JTextField nazwa = new JTextField();
        nazwa.setText("NYSE");
        JComboBox<Kraj> kraj = new JComboBox<>();
        for(Kraj k: Baza.get().getKraje())
            kraj.addItem(k);
        JComboBox<Waluta> waluta = new JComboBox<>();
        for(Waluta w: Baza.get().getWaluty()) {
            waluta.addItem(w);
        }
        JTextField miasto = new JTextField();
        miasto.setText("Nowy York");
        JTextField adres = new JTextField();
        adres.setText("Wall Street 1");
        JTextField marza = new JTextField();
        marza.setText("0.5");

        JPanel panelGieldy = new JPanel(new GridLayout(6,2));
        JLabel label1 = new JLabel("Nazwa", JLabel.CENTER);
        panelGieldy.add(label1);
        nazwa.setText("Wall Street");
        panelGieldy.add(nazwa);
        JLabel label2 = new JLabel("Kraj", JLabel.CENTER);
        panelGieldy.add(label2);
        panelGieldy.add(kraj);
        JLabel label3 = new JLabel("Waluta", JLabel.CENTER);
        panelGieldy.add(label3);
        panelGieldy.add(waluta);
        JLabel label4 = new JLabel("Miasto", JLabel.CENTER);
        panelGieldy.add(label4);
        panelGieldy.add(miasto);
        JLabel label5 = new JLabel("Adres", JLabel.CENTER);
        panelGieldy.add(label5);
        panelGieldy.add(adres);
        JLabel label6 = new JLabel("Marża procentowa", JLabel.CENTER);
        panelGieldy.add(label6);
        panelGieldy.add(marza);

        JPanel panelRynku = new JPanel(new GridLayout(2,2));
        JLabel label7 = new JLabel("Nazwa");
        panelRynku.add(label7);
        JTextField nazwa2 = new JTextField();
        nazwa2.setText("Forex-Surowce");
        panelRynku.add(nazwa2);
        JLabel label8 = new JLabel("Marża procentowa", JLabel.CENTER);
        panelRynku.add(label8);
        JTextField marza2 = new JTextField();
        marza2.setText("0.5");
        panelRynku.add(marza2);

        globalPanel.add(panelGieldy, 1);

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
            Gielda gielda = new Gielda(nazwa.getText(), Double.parseDouble(marza.getText()),
                    (Waluta)waluta.getSelectedItem(), miasto.getText(), adres.getText());
            Baza.get().addRynek(gielda);

            setVisible(false);
            dispose();
        });


        choice.addActionListener(e -> {
            String selected = (String)choice.getSelectedItem();
            if(selected.equals("Giełda")) {
                globalPanel.remove(1);
                globalPanel.add(panelGieldy,1);
                validate();
                repaint();

                clearListeners(zapisz);
                zapisz.addActionListener(f-> {
                    Gielda gielda = new Gielda(nazwa.getText(), Double.parseDouble(marza.getText()),
                            (Waluta)waluta.getSelectedItem(), miasto.getText(), adres.getText());
                    Baza.get().addRynek(gielda);

                    setVisible(false);
                    dispose();
                });
            }
            else if(selected.equals("Rynek walut")) {
                globalPanel.remove(1);
                globalPanel.add(panelRynku, 1);
                validate();
                repaint();

                clearListeners(zapisz);
                zapisz.addActionListener(f -> {
                    RynekWalut rynekWalut = new RynekWalut(nazwa2.getText(), Double.parseDouble(marza2.getText()));
                    Baza.get().addRynek(rynekWalut);

                    setVisible(false);
                    dispose();
                });
            }
            else {
                globalPanel.remove(1);
                globalPanel.add(panelRynku,1);

                clearListeners(zapisz);
                zapisz.addActionListener(f -> {
                    RynekSurowcow rynekSurowcow = new RynekSurowcow(nazwa2.getText(), Double.parseDouble(marza2.getText()));
                    Baza.get().addRynek(rynekSurowcow);

                    setVisible(false);
                    dispose();
                });

                validate();
                repaint();
            }
        });

        globalPanel.add(panelPrzyciskow,2);

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
