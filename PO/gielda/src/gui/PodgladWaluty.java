package gui;

import helpery.Tablice;

import javax.swing.*;
import java.awt.*;

/**
 * Panel podglądu waluty - jeden z widoków okna głównego
 */
public class PodgladWaluty extends JPanel {
    PodgladWaluty() {
        super(new BorderLayout());

        JTable table = new JTable(Tablice.getWalutyModel());
        JScrollPane okno = new JScrollPane(table);

        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(okno, BorderLayout.CENTER);
    }
}
