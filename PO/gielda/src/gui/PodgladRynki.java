package gui;

import helpery.Tablice;

import javax.swing.*;
import java.awt.*;

/**
 * Panel podglądu rynków - jeden z widoków okna głównego
 */
public class PodgladRynki extends JPanel {
    PodgladRynki() {
        super(new BorderLayout());

        JTable table = new JTable(Tablice.getRynkiModel());
        JScrollPane okno = new JScrollPane(table);

        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(okno, BorderLayout.CENTER);
    }
}
