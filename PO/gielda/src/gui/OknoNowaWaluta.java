package gui;

import aktywa.Waluta;
import helpery.Baza;
import helpery.Kraj;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Okno dodawania nowej waluty
 */
public class OknoNowaWaluta extends JFrame {
    public OknoNowaWaluta() {
        super("Dodaj walutę");

        JPanel panel = new JPanel(new GridLayout(4,2));
        setContentPane(panel);
        JLabel label1 = new JLabel("Nazwa");
        panel.add(label1);
        JTextField nazwa = new JTextField();
        nazwa.setText("USD");
        panel.add(nazwa);
        JLabel label2 = new JLabel("Kraje");
        panel.add(label2);
        List<Kraj> wybraneKraje = new ArrayList<>();
        DefaultListModel<Kraj> listModel = new DefaultListModel<>();
        JList<Kraj> kraje = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(kraje);
        panel.add(scrollPane);
        JPanel krajePanel = new JPanel(new GridLayout(1,3));
        JComboBox<Kraj> listaKrajow = new JComboBox<>();
        panel.add(listaKrajow);
        for(Kraj k: Baza.get().getKraje()) {
            listaKrajow.addItem(k);
        }
        JButton append = new JButton("+");
        append.addActionListener(e -> {
            Kraj kraj = (Kraj)listaKrajow.getSelectedItem();
            wybraneKraje.add(kraj);
            listModel.addElement(kraj);
        });
        krajePanel.add(append);
        JButton remove = new JButton("-");
        remove.addActionListener(e -> {
            Kraj kraj = (Kraj)listaKrajow.getSelectedItem();
            wybraneKraje.remove(kraj);
            listModel.removeElement(kraj);
        });
        krajePanel.add(remove);
        panel.add(krajePanel);
        JButton anuluj = new JButton("Anuluj");
        anuluj.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        panel.add(anuluj);
        JButton zapisz = new JButton("Zapisz");
        zapisz.addActionListener(e -> {
            Waluta waluta = new Waluta(nazwa.getText());
            for(Kraj k: wybraneKraje) {
                waluta.addKraje(k);
            }
            Baza.get().addWaluta(waluta);
            setVisible(false);
            dispose();
        });
        panel.add(zapisz);

        setLocation(100,100);
        pack();
        setVisible(true);
    }
}
