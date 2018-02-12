package helpery;

import aktywa.Spolka;

import java.util.List;

/**
 * Abstrakcyjny indeks
 */

public class Indeks {
    private String nazwa;

    private List<Spolka> spolki;

    public Indeks(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getNazwa() {
        return nazwa;
    }

    List<Spolka> getSpolki() {
        return spolki;
    }
}
