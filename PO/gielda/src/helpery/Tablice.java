package helpery;

import aktywa.Waluta;
import gracz.Fundusz;
import gracz.Gracz;
import rynek.Gielda;
import rynek.Rynek;
import rynek.RynekWalut;

import javax.swing.table.AbstractTableModel;

/**
 * Modele tablic do wyświetlania w widokach okna głównego
 */

public class Tablice {
    private static AbstractTableModel tablicaKrajow;
    private static AbstractTableModel tablicaAktywow;
    private static AbstractTableModel tablicaGraczy;
    private static AbstractTableModel tablicaRynkow;
    private static AbstractTableModel tablicaWalut;

    private static void ustawTabliceKrajow() {
        String columnNames[] = new String[] {"kod", "nazwa"};
        tablicaKrajow = new AbstractTableModel() {
            @Override
            public void fireTableDataChanged() {
                super.fireTableDataChanged();
            }
            public int getRowCount() {
                return Baza.get().getKraje().size();
            }
            public String getColumnName(int column) {
                this.fireTableDataChanged();
                return columnNames[column];
            }
            public int getColumnCount() {
                return columnNames.length;
            }
            public Object getValueAt(int row, int column) {
                if(column == 0)
                    return Baza.get().getKraje().get(row).getKod();
                else
                    return Baza.get().getKraje().get(row).getNazwa();
            }
        };
    }

    private static void ustawTabliceAktywow() {
        tablicaAktywow = new TablicaAktywow();
    }

    private static void ustawTabliceGraczy() {
        String columnNames[] = new String[]{"imie", "nazwisko", "fundusz"};
        tablicaGraczy = new AbstractTableModel() {
            @Override
            public void fireTableDataChanged() {
                super.fireTableDataChanged();
            }

            public int getRowCount() {
                return Baza.get().getGracze().size();
            }

            public String getColumnName(int column) {
                this.fireTableDataChanged();
                return columnNames[column];
            }

            public int getColumnCount() {
                return columnNames.length;
            }

            public Object getValueAt(int row, int column) {
                Gracz gracz = Baza.get().getGracze().get(row);
                switch (column) {
                    case 0:
                        return gracz.getImie();
                    case 1:
                        return gracz.getNazwisko();
                    default:
                        if(gracz instanceof Fundusz)
                            return ((Fundusz) gracz).getNazwa();
                        else
                            return "nie";
                }
            }
        };
    }

    private static void ustawTabliceRynkow() {
        String columnNames[] = new String[]{"nazwa", "marża", "typ"};
        tablicaRynkow = new AbstractTableModel() {
            @Override
            public void fireTableDataChanged() {
                super.fireTableDataChanged();
            }

            public int getRowCount() {
                return Baza.get().getRynki().size();
            }

            public String getColumnName(int column) {
                this.fireTableDataChanged();
                return columnNames[column];
            }

            public int getColumnCount() {
                return columnNames.length;
            }

            public Object getValueAt(int row, int column) {
                Rynek rynek = Baza.get().getRynki().get(row);
                switch (column) {
                    case 0:
                        return rynek.getNazwa();
                    case 1:
                        return rynek.getMarza();
                    default:
                        if(rynek instanceof Gielda)
                            return "Giełda";
                        else if(rynek instanceof RynekWalut)
                            return "Rynek walut";
                        else
                            return "Rynek surowców";
                }
            }
        };
    }

    private static void ustawTabliceWalut() {
        String columnNames[] = new String[]{"nazwa", "kraje"};
        tablicaWalut = new AbstractTableModel() {
            @Override
            public void fireTableDataChanged() {
                super.fireTableDataChanged();
            }

            public int getRowCount() {
                return Baza.get().getWaluty().size();
            }

            public String getColumnName(int column) {
                this.fireTableDataChanged();
                return columnNames[column];
            }

            public int getColumnCount() {
                return columnNames.length;
            }

            public Object getValueAt(int row, int column) {
                Waluta w = Baza.get().getWaluty().get(row);
                switch (column) {
                    case 0:
                        return w.getNazwa();
                    default:
                        String kraje = "";
                        for(Kraj k: w.getKraje()) {
                            kraje += k.getKod()+" ";
                        }
                        return kraje;
                }
            }
        };
    }


    public static synchronized AbstractTableModel getRynkiModel() {
        if(tablicaRynkow == null)
            ustawTabliceRynkow();
        return tablicaRynkow;
    }

    public static synchronized AbstractTableModel getAktywaModel() {
        if(tablicaAktywow == null)
            ustawTabliceAktywow();
        return tablicaAktywow;
    }

    public static synchronized AbstractTableModel getKrajeModel() {
        if(tablicaKrajow == null)
            ustawTabliceKrajow();
        return tablicaKrajow;
    }

    public static synchronized AbstractTableModel getGraczeModel() {
        if(tablicaGraczy == null)
            ustawTabliceGraczy();
        return tablicaGraczy;
    }

    public static synchronized AbstractTableModel getWalutyModel() {
        if(tablicaWalut == null)
            ustawTabliceWalut();
        return tablicaWalut;
    }
}