package gui;

import aktywa.*;
import helpery.Baza;
import helpery.Kraj;
import helpery.Tablice;
import rynek.Gielda;
import rynek.Rynek;
import rynek.RynekSurowcow;
import rynek.RynekWalut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Okno dodawania nowych aktywów
 */
public class OknoNoweAktywa extends JFrame {
    public OknoNoweAktywa() {
        super("Dodaj aktywa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel globalPanel = new JPanel(new GridLayout(3,1));
        setContentPane(globalPanel);

        JPanel choicePanel = new JPanel(new GridLayout(1,1));
        globalPanel.add(choicePanel,0);
        JLabel label0 = new JLabel("Typ", JLabel.CENTER);
        choicePanel.add(label0);
        JComboBox<String> choice = new JComboBox<>();
        choice.addItem("Spółka");
        choice.addItem("Para walut");
        choice.addItem("Surowiec");
        choice.setSelectedIndex(0);
        choicePanel.add(choice);


        JPanel panelSpolka = new JPanel(new GridLayout(6,2));
        globalPanel.add(panelSpolka, 1);
        JLabel label01 = new JLabel("Giełda", JLabel.CENTER);
        panelSpolka.add(label01);
        JComboBox<Rynek> gieldaChoice = new JComboBox<>();
        for(Rynek r: Baza.get().getRynki()) {
            if(r instanceof Gielda)
                gieldaChoice.addItem(r);
        }
        panelSpolka.add(gieldaChoice);
        JLabel label1 = new JLabel("Nazwa", JLabel.CENTER);
        panelSpolka.add(label1);
        JTextField nazwa = new JTextField();
        nazwa.setText("KGHM Polska Miedź");
        panelSpolka.add(nazwa);
        JLabel label2 = new JLabel("Kurs otwarcia", JLabel.CENTER);
        panelSpolka.add(label2);
        JTextField cena = new JTextField();
        cena.setText("10");
        panelSpolka.add(cena);
        JLabel label3 = new JLabel("Ilość akcji", JLabel.CENTER);
        panelSpolka.add(label3);
        JTextField nAkcji = new JTextField();
        nAkcji.setText("100");
        panelSpolka.add(nAkcji);
        JLabel label4 = new JLabel("Kapitał zakładowy", JLabel.CENTER);
        panelSpolka.add(label4);
        JTextField kapitalZaklad = new JTextField();
        kapitalZaklad.setText("50000");
        panelSpolka.add(kapitalZaklad);
        JLabel label5 = new JLabel("Kapitał własny", JLabel.CENTER);
        panelSpolka.add(label5);
        JTextField kapitalWlasny = new JTextField();
        kapitalWlasny.setText("100000");
        panelSpolka.add(kapitalWlasny);


        JPanel panelWaluta = new JPanel(new GridLayout(4,2));
        JLabel label6 = new JLabel("Rynek", JLabel.CENTER);
        panelWaluta.add(label6);
        JComboBox<Rynek> rynekWalutChoice = new JComboBox<>();
        for(Rynek r: Baza.get().getRynki()) {
            if(r instanceof RynekWalut)
                rynekWalutChoice.addItem(r);
        }
        panelWaluta.add(rynekWalutChoice);
        JLabel label8 = new JLabel("Waluta z");
        panelWaluta.add(label8);
        JComboBox<Waluta> walutaZ = new JComboBox<>();
        panelWaluta.add(walutaZ);
        JLabel label85 = new JLabel("Waluta na");
        panelWaluta.add(label85);
        JComboBox<Waluta> walutaNa = new JComboBox<>();
        panelWaluta.add(walutaNa);
        for(Waluta w: Baza.get().getWaluty()) {
            walutaZ.addItem(w);
            walutaNa.addItem(w);
        }
        JLabel label89 = new JLabel("Cena");
        panelWaluta.add(label89);
        JTextField cenaWaluty = new JTextField();
        cenaWaluty.setText("1");
        panelWaluta.add(cenaWaluty);


        JPanel panelSurowiec = new JPanel(new GridLayout(5,2));
        JLabel label9 = new JLabel("Rynek", JLabel.CENTER);
        panelSurowiec.add(label9);
        JComboBox<Rynek> rynekSurowcowChoice = new JComboBox<>();
        for(Rynek r: Baza.get().getRynki()) {
            if(r instanceof RynekSurowcow)
                rynekSurowcowChoice.addItem(r);
        }
        panelSurowiec.add(rynekSurowcowChoice);
        JLabel label95 = new JLabel("Nazwa");
        panelSurowiec.add(label95);
        JTextField nazwaSurowca = new JTextField();
        nazwaSurowca.setText("Ropa naftowa");
        panelSurowiec.add(nazwaSurowca);
        JLabel label10 = new JLabel("Cena");
        panelSurowiec.add(label10);
        JTextField cenaSurowca = new JTextField();
        cenaSurowca.setText("1000");
        panelSurowiec.add(cenaSurowca);
        JLabel label11 = new JLabel("Jednostka");
        panelSurowiec.add(label11);
        JTextField jednostka = new JTextField();
        jednostka.setText("Baryłka");
        panelSurowiec.add(jednostka);
        JLabel label12 = new JLabel("Waluta");
        panelSurowiec.add(label12);
        JComboBox<Waluta> walutaSurowca = new JComboBox<>();
        for(Waluta w: Baza.get().getWaluty()) {
            walutaSurowca.addItem(w);
        }
        panelSurowiec.add(walutaSurowca);

        JPanel przyciskiPanel = new JPanel(new GridLayout(1,2));
        globalPanel.add(przyciskiPanel,2);
        JButton anuluj = new JButton("Anuluj");
        anuluj.addActionListener(e-> {
            setVisible(false);
            dispose();
        });
        przyciskiPanel.add(anuluj);
        JButton zapisz = new JButton("Zapisz");
        zapisz.addActionListener(e-> {


            setVisible(false);
            dispose();
        });
        przyciskiPanel.add(zapisz);

        zapisz.addActionListener(f -> {
            if(gieldaChoice.getSelectedItem() != null) {
                Spolka spolka = new Spolka(nazwa.getText(), Double.parseDouble(cena.getText()),
                        Integer.parseInt(nAkcji.getText()), Double.parseDouble(kapitalWlasny.getText()),
                        Double.parseDouble(kapitalZaklad.getText()));
                ((Gielda) gieldaChoice.getSelectedItem()).addAktywa(spolka);

                setVisible(false);
                dispose();
            }
        });

        choice.addActionListener(e -> {
            String selected = (String)choice.getSelectedItem();
            if(selected.equals("Spółka")) {
                globalPanel.remove(1);
                globalPanel.add(panelSpolka,1);
                validate();
                repaint();

                clearListeners(zapisz);
                zapisz.addActionListener(f -> {
                    if(gieldaChoice.getSelectedItem() != null) {
                        Spolka spolka = new Spolka(nazwa.getText(), Double.parseDouble(cena.getText()),
                                Integer.parseInt(nAkcji.getText()), Double.parseDouble(kapitalWlasny.getText()),
                                Double.parseDouble(kapitalZaklad.getText()));
                        ((Gielda) gieldaChoice.getSelectedItem()).addAktywa(spolka);

                        setVisible(false);
                        dispose();
                    }
                });


            }
            else if(selected.equals("Para walut")) {
                globalPanel.remove(1);
                globalPanel.add(panelWaluta,1);
                validate();
                repaint();

                clearListeners(zapisz);
                zapisz.addActionListener(f -> {
                    if(rynekWalutChoice.getSelectedItem() != null) {
                        ParaWalut paraWalut = new ParaWalut((Waluta)walutaZ.getSelectedItem(), (Waluta)walutaNa.getSelectedItem(),
                                Double.parseDouble(cenaWaluty.getText()));
                        ((RynekWalut)rynekWalutChoice.getSelectedItem()).addAktywa(paraWalut);

                        setVisible(false);
                        dispose();
                    }
                });
            }

            else if(selected.equals("Surowiec")) {
                globalPanel.remove(1);
                globalPanel.add(panelSurowiec,1);
                validate();
                repaint();

                clearListeners(zapisz);
                zapisz.addActionListener(f -> {
                    if(rynekSurowcowChoice.getSelectedItem() != null) {
                        Surowiec surowiec = new Surowiec(nazwaSurowca.getText(),
                                Double.parseDouble(cenaSurowca.getText()),
                                (Waluta)walutaSurowca.getSelectedItem(),
                                jednostka.getText());
                        ((RynekSurowcow)rynekSurowcowChoice.getSelectedItem()).addAktywa(surowiec);

                        setVisible(false);
                        dispose();
                    }
                });
            }
        });

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
