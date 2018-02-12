package gui;

import gracz.Gracz;
import helpery.Baza;
import helpery.Tablice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel podglądu graczy - jeden z widoków okna głównego
 */
public class PodgladGracze extends JPanel {
    PodgladGracze() {
        super(new BorderLayout());

        JTable table = new JTable(Tablice.getGraczeModel());
        JScrollPane okno = new JScrollPane(table);

        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(okno, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2) {
                    Gracz g = Baza.get().getGracze().get(row);
                    new OknoGracz(g);
                }
            }
        });
    }
}
