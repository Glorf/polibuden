package rynek;

import java.util.ArrayList;
import java.util.List;
import aktywa.Spolka;
import aktywa.Waluta;
import aktywnosc.WatekSpolka;
import helpery.Baza;
import helpery.Indeks;
import helpery.Kraj;

/**
 * Klasa zarządzająca aktywami i indeksami na giełdzie
 */
public class Gielda extends Rynek<Spolka> {
    private Kraj kraj;
    private Waluta waluta;
    private String miasto;
    private String adres;
    private List<Indeks> indeksy;

    public Gielda(String nazwa, double marzaproc, Waluta waluta, String miasto, String adres) {
        super(nazwa, marzaproc);

        this.waluta = waluta;
        this.miasto = miasto;
        this.adres = adres;
        indeksy = new ArrayList<>();
    }

    public void addAktywa(Spolka aktywa) {
        super.addAktywa(aktywa);

        WatekSpolka watek = new WatekSpolka(aktywa);
        Baza.get().nowyWatek(watek);
    }

    public void setKraj(Kraj kraj) {
        this.kraj = kraj;
    }
    public Kraj getKraj() {
        return kraj;
    }

    public void setWaluta(Waluta waluta) {
        this.waluta = waluta;
    }
    public Waluta getWaluta() {
        return waluta;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }
    public String getMiasto() {
        return miasto;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
    public String getAdres() {
        return adres;
    }

    public void addIndeksy(Indeks indeks) {
        indeksy.add(indeks);
    }
    public List<Indeks> getIndeksy() {
        return indeksy;
    }
}
