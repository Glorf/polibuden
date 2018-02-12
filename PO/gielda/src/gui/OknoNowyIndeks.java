package gui;

import aktywa.Spolka;
import helpery.Baza;
import helpery.IndeksNajlepsze;
import helpery.IndeksRecznie;
import rynek.Gielda;
import rynek.Rynek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Okno dodawania nowego indeksu
 */
public class OknoNowyIndeks extends JFrame {
    public OknoNowyIndeks() {
        super("Nowy indeks");

        JPanel globalPanel = new JPanel(new GridLayout(3,1));
        setContentPane(globalPanel);

        JPanel choicePanel = new JPanel(new GridLayout(3,2));
        globalPanel.add(choicePanel);
        JLabel typ = new JLabel("Typ");
        JComboBox<String> choice = new JComboBox<>();
        choice.addItem("Ręcznie");
        choice.addItem("Top");

        JLabel label3 = new JLabel("Giełda");
        choicePanel.add(label3);
        JComboBox<Gielda> choiceGielda = new JComboBox<>();
        for(Rynek r: Baza.get().getRynki()) {
            if(r instanceof Gielda)
                choiceGielda.addItem((Gielda)r);
        }
        choicePanel.add(choiceGielda);

        choicePanel.add(typ);
        choicePanel.add(choice);
        JLabel label1 = new JLabel("Nazwa");
        choicePanel.add(label1);
        JTextField nazwa = new JTextField();
        choicePanel.add(nazwa);

        JPanel topPanel = new JPanel(new GridLayout(2,2));
        JLabel label2 = new JLabel("Ilość top");
        topPanel.add(label2);
        JTextField iloscTop = new JTextField();
        topPanel.add(iloscTop);

        JPanel reczniePanel = new JPanel(new GridLayout(1,3));
        DefaultListModel<Spolka> modelIn = new DefaultListModel<>();
        for(Rynek r: Baza.get().getRynki()) {
            if(r instanceof Gielda) {
                for(Spolka s: ((Gielda)r).getAktywa()) {
                    modelIn.addElement(s);
                }
            }
        }

        JList<Spolka> listaIn = new JList<>(modelIn);
        JScrollPane inScroll = new JScrollPane(listaIn);
        reczniePanel.add(inScroll);

        JPanel przyciskiWyboru = new JPanel(new GridLayout(2,1));
        reczniePanel.add(przyciskiWyboru);
        JButton next = new JButton("->");
        przyciskiWyboru.add(next);
        JButton prev = new JButton("<-");
        przyciskiWyboru.add(prev);

        DefaultListModel<Spolka> modelOut = new DefaultListModel<>();
        JList<Spolka> listaOut = new JList<>(modelOut);
        JScrollPane outScroll = new JScrollPane(listaOut);

        reczniePanel.add(outScroll);

        globalPanel.add(reczniePanel);

        JPanel przyciskiDolne = new JPanel(new GridLayout(2,1));
        globalPanel.add(przyciskiDolne);
        JButton anuluj = new JButton("Anuluj");
        anuluj.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        przyciskiDolne.add(anuluj);
        JButton zapisz = new JButton("Zapisz");
        przyciskiDolne.add(zapisz);

        zapisz.addActionListener(f -> {
            IndeksRecznie recznie = new IndeksRecznie(nazwa.getText());
            for(int i = 0; i<modelOut.size(); i++) {
                recznie.addSpolki(modelOut.get(i));
            }

            ((Gielda)choiceGielda.getSelectedItem()).addIndeksy(recznie);
            setVisible(false);
            dispose();
        });

        next.addActionListener(e -> {
            Spolka s = listaIn.getSelectedValue();

            modelIn.removeElement(s);
            modelOut.addElement(s);
            validate();
            repaint();
        });

        prev.addActionListener(e -> {
            Spolka s = listaOut.getSelectedValue();

            modelIn.addElement(s);
            modelOut.removeElement(s);
            validate();
            repaint();
        });

        choice.addActionListener(e -> {
            if(choice.getSelectedItem().equals("Ręcznie")) {
                globalPanel.remove(1);
                globalPanel.add(reczniePanel, 1);

                clearListeners(zapisz);

                zapisz.addActionListener(f -> {
                    IndeksRecznie recznie = new IndeksRecznie(nazwa.getText());
                    for(int i = 0; i<modelOut.size(); i++) {
                        recznie.addSpolki(modelOut.get(i));
                    }

                    ((Gielda)choiceGielda.getSelectedItem()).addIndeksy(recznie);
                    setVisible(false);
                    dispose();
                });


                validate();
                repaint();
            }
            else {
                globalPanel.remove(1);
                globalPanel.add(topPanel, 1);

                clearListeners(zapisz);

                zapisz.addActionListener(f -> {
                    Gielda g = (Gielda)choiceGielda.getSelectedItem();
                    IndeksNajlepsze indeksNajlepsze = new IndeksNajlepsze(nazwa.getText(), g,
                            Integer.parseInt(iloscTop.getText()));

                    g.addIndeksy(indeksNajlepsze);
                    setVisible(false);
                    dispose();
                });


                validate();
                repaint();
            }
        });


        pack();
        setVisible(true);
        setLocation(100, 100);
    }

    public void clearListeners(AbstractButton b) {
        for(ActionListener l: b.getActionListeners()) {
            b.removeActionListener(l);
        }
    }
}
