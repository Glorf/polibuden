package gui;

import helpery.Tablice;

import javax.swing.*;
import java.awt.*;

/**
 * Panel podglądu krajów - jeden z widoków okna głównego
 */
public class PodgladKraje extends JPanel {
    PodgladKraje() {
        super(new BorderLayout());
        fill();
    }

    public void fill() {
        JTable table = new JTable(Tablice.getKrajeModel());
        JScrollPane okno = new JScrollPane(table);

        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(okno, BorderLayout.CENTER);
    }
}
