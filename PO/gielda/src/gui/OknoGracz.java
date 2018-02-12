package gui;

import aktywnosc.Inwestycja;
import aktywnosc.Operacja;
import aktywnosc.TypOperacji;
import gracz.Fundusz;
import gracz.Gracz;
import gracz.Inwestor;
import helpery.Baza;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoGracz extends JFrame implements ActionListener {
    private Timer timer = new Timer(1000, this);
    private Gracz gracz;
    private AbstractTableModel portfelModel;
    private AbstractTableModel historiaModel;
    private JTable portfelTablica;
    private JTable historiaTablica;
    private JLabel budzet;


    OknoGracz(Gracz g) {
        super(g.getImie() + " " + g.getNazwisko());

        JMenuBar menuBar = new JMenuBar();
        JMenu plik = new JMenu("Plik");
        JMenuItem usun = new JMenuItem("Usuń");
        usun.addActionListener(e -> {
            setVisible(false);
            Baza.get().usunGracza(g);
            dispose();
        });
        plik.add(usun);
        menuBar.add(plik);
        setJMenuBar(menuBar);

        gracz = g;
        JPanel globalPanel = new JPanel(new GridLayout(1,2));
        add(globalPanel);

        if(gracz instanceof Fundusz) {
            JPanel fundusz = new JPanel(new GridLayout(1,2));
            JLabel label1 = new JLabel("Nazwa");
            fundusz.add(label1);
            JLabel nazwa = new JLabel(((Fundusz)gracz).getNazwa());
            fundusz.add(nazwa);
            globalPanel.add(fundusz);
        }
        else if(gracz instanceof Inwestor) {
            JPanel inwestor = new JPanel(new GridLayout(2,2));
            JLabel label1 = new JLabel("Pesel");
            inwestor.add(label1);
            JLabel pesel = new JLabel(((Inwestor)gracz).getPesel());
            inwestor.add(pesel);
            JLabel label2 = new JLabel("Budzet");
            inwestor.add(label2);
            budzet = new JLabel(String.format("%.2f", ((Inwestor)gracz).getBudzet()));
            inwestor.add(budzet);
            globalPanel.add(inwestor);
        }

        JPanel scrollBarsPanel = new JPanel(new GridLayout(2,1));
        globalPanel.add(scrollBarsPanel);

        portfelModel = new AbstractTableModel() {
            String[] columnNames = {"aktywa", "ilość"};
            @Override
            public void fireTableDataChanged() {
                super.fireTableDataChanged();
            }

            public int getRowCount() {
                return gracz.getPortfel().size();
            }

            public String getColumnName(int column) {
                this.fireTableDataChanged();
                return columnNames[column];
            }

            public int getColumnCount() {
                return columnNames.length;
            }

            public Object getValueAt(int row, int column) {
                Inwestycja i = gracz.getPortfel().get(row);
                switch (column) {
                    case 0:
                        return i.getAktywa().getNazwa();
                    default:
                        return i.getIlosc();
                }
            }
        };
        portfelTablica = new JTable(portfelModel);
        JScrollPane portfelPrzesuwana = new JScrollPane(portfelTablica);
        scrollBarsPanel.add(portfelPrzesuwana);

        historiaModel = new AbstractTableModel() {
            String[] columnNames = {"operacja", "aktywa", "ilość"};
            @Override
            public void fireTableDataChanged() {
                super.fireTableDataChanged();
            }

            public int getRowCount() {
                return gracz.getHistoria().size();
            }

            public String getColumnName(int column) {
                this.fireTableDataChanged();
                return columnNames[column];
            }

            public int getColumnCount() {
                return columnNames.length;
            }

            public Object getValueAt(int row, int column) {
                Operacja o = gracz.getHistoria().get(gracz.getHistoria().size()-row-1);
                switch (column) {
                    case 0:
                        if(o.getTyp().equals(TypOperacji.KUP))
                            return "Kupno";
                        else return "Sprzedaż";
                    case 1:
                        return o.getAktywa().getNazwa();
                    default:
                        return o.getIlosc();
                }
            }
        };
        historiaTablica = new JTable(historiaModel);
        JScrollPane historiaPrzesuwana = new JScrollPane(historiaTablica);
        scrollBarsPanel.add(historiaPrzesuwana);

        setLocation(100,100);
        setSize(500,400);
        setVisible(true);

        timer.start();
    }

    public void actionPerformed(ActionEvent ev){
        if(gracz instanceof Inwestor)
            budzet.setText(String.format("%.2f",((Inwestor)gracz).getBudzet()));

        portfelModel.fireTableDataChanged();
        historiaModel.fireTableDataChanged();

        validate();
        repaint();
    }
}
