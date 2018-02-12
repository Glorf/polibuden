package aktywa;

import helpery.Tablice;
import rynek.Rynek;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa po której dziedziczą aktywa; zapewnia podstawowe operacje na tychże
 */
public class Aktywa implements Serializable{
    private Rynek rynek;
    private String nazwa;
    private double cena;
    private List<Double> historia;

    public Aktywa(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
        historia = new ArrayList<>();
    }

    public String toString() {
        return nazwa;
    }

    public void setRynek(Rynek rynek) {
        this.rynek = rynek;
    }

    public Rynek getRynek() {
        return rynek;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getNazwa() {
        return nazwa;
    }

    public synchronized void setCena(double cena) {
        this.cena = cena;
        Tablice.getAktywaModel().fireTableDataChanged();
    }
    public double getCena() {
        return cena;
    }

    public synchronized void notujWartosc() {
        historia.add(cena);
    }
    public List<Double> getHistoria() {
        return historia;
    }
}
