package gui;

import aktywa.Aktywa;
import helpery.Baza;
import helpery.TablicaAktywow;
import helpery.Tablice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel podglądu aktywów - jeden z widoków okna głównego
 */
public class PodgladAktywa extends JPanel {
    PodgladAktywa() {
        super(new BorderLayout());

        JTable table = new JTable(Tablice.getAktywaModel());
        JScrollPane okno = new JScrollPane(table);

        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(okno, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2) {
                    Aktywa a = ((TablicaAktywow)Tablice.getAktywaModel()).getAktywaAt(row);
                    new OknoAktywa(a);
                }
            }
        });
    }
}
