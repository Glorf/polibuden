package rynek;

import aktywa.Aktywa;
import aktywa.Spolka;
import aktywnosc.Operacja;
import aktywnosc.WatekSpolka;
import helpery.Baza;
import helpery.Tablice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstrakcyjny rynek dla dowolnych typów aktywów
 * @param <T> jest aktywem
 */
public class Rynek<T extends Aktywa> implements Serializable {
    private String nazwa;
    private double marzaproc;
    private List<T> aktywa;

    public Rynek(String nazwa, double marzaproc) {
        this.nazwa = nazwa;
        this.marzaproc = marzaproc;
        aktywa = new ArrayList<>();
    }

    @Override
    public String toString() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getNazwa() {
        return nazwa;
    }

    public void setMarza(double marza) {
        this.marzaproc = marza;
    }
    public double getMarza() {
        return marzaproc;
    }

    public synchronized void addAktywa(T aktywa) {
        this.aktywa.add(aktywa);
        aktywa.setRynek(this);
        Tablice.getAktywaModel().fireTableDataChanged();
    }
    public synchronized List<T> getAktywa() {
        return aktywa;
    }
    public synchronized void rmAktywa(T aktywa) {
        this.aktywa.remove(aktywa);
        Tablice.getAktywaModel().fireTableDataChanged();
        if(aktywa instanceof Spolka)
            Baza.get().usunWatekSpolki((Spolka) aktywa);
    }
}
