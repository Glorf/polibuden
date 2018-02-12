package aktywnosc;

import aktywa.Aktywa;
import aktywa.Spolka;
import gracz.Gracz;
import gracz.Inwestor;

import java.io.Serializable;
import java.time.Instant;

/**
 * Inwestycja jest składnikiem portfela każdego gracza
 */
public class Inwestycja implements Serializable {
    private Instant data;
    private double ilosc;
    private Gracz inwestor;
    private double cena;
    private Aktywa aktywa;

    public Inwestycja(double ilosc, Gracz inwestor, double cena, Aktywa aktywa) {
        this.ilosc = ilosc;
        this.inwestor = inwestor;
        this.cena = cena;
        this.aktywa = aktywa;
        this.data = Instant.now();
    }

    public void setData(Instant data) {
        this.data = data;
    }
    public Instant getData() {
        return data;
    }

    public void setIlosc(double ilosc) {
        if(aktywa instanceof Spolka) {
            this.ilosc = (int)ilosc;
        }
        else {
            this.ilosc = ilosc;
        }
    }
    public double getIlosc() {
        return ilosc;
    }

    public void setInwestor(Inwestor inwestor) {
        this.inwestor = inwestor;
    }
    public Gracz getInwestor() {
        return inwestor;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
    public double getCena() {
        return cena;
    }

    public void setAktywa(Aktywa aktywa) {
        this.aktywa = aktywa;
    }
    public Aktywa getAktywa() {
        return aktywa;
    }
}
