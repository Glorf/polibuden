package helpery;

import aktywa.Spolka;

/**
 * Indeks z ręcznie przypisywaną listą spółek
 */
public class IndeksRecznie extends Indeks {
    public IndeksRecznie(String nazwa) {
        super(nazwa);
    }

    public void addSpolki(Spolka spolka) {
        getSpolki().add(spolka);
    }

    public void rmSpolki(Spolka spolka) {
        getSpolki().remove(spolka);
    }

}
