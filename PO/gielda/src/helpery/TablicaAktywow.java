package helpery;

import aktywa.Aktywa;
import rynek.Rynek;

import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * Model tablicy do wyświetlenia tablicy wszystkich możliwych aktywów
 */
public class TablicaAktywow extends AbstractTableModel {
    private String columnNames[] = new String[] {"nazwa", "cena", "rynek"};

    @Override
    public void fireTableDataChanged() {
        super.fireTableDataChanged();
    }
    public int getRowCount() {
        int c = 0;
        for(Rynek r: Baza.get().getRynki())
            c+=r.getAktywa().size();

        return c;
    }
    public String getColumnName(int column) {
        this.fireTableDataChanged();
        return columnNames[column];
    }
    public int getColumnCount() {
        return columnNames.length;
    }
    public Aktywa getAktywaAt(int row) {
        Aktywa requested = null;
        int c = 0;
        for(Rynek<Aktywa> r: Baza.get().getRynki()) {
            if (c + r.getAktywa().size() > row) {
                requested = r.getAktywa().get(row - c);
                break;
            }
            else
                c += r.getAktywa().size();
        }

        return requested;
    }

    public Rynek getRynekAt(int row) {
        Rynek rynek = null;
        int c = 0;
        for(Rynek<Aktywa> r: Baza.get().getRynki()) {
            if (c + r.getAktywa().size() > row) {
                rynek = r;
                break;
            }
            else
                c += r.getAktywa().size();
        }

        return rynek;
    }

    public Object getValueAt(int row, int column) {
        Aktywa requested = getAktywaAt(row);

        switch (column){
            case 0:
                return requested.getNazwa();
            case 1:
                return String.format("%.2f", requested.getCena());
            default:
                return getRynekAt(row).getNazwa();
        }
    }
}
