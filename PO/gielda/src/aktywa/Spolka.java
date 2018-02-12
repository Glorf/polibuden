package aktywa;

import java.time.Instant;

/**
 * Aktywo - spółka
 */
public class Spolka extends Aktywa implements Comparable<Spolka> {
    private Instant pierwszaWycena;
    private double kursOtwarcia;
    private double kursMin;
    private double kursMax;
    private int nAkcji;
    private double zysk;
    private double przychod;
    private double kapitalWlasny;
    private double kapitalZaklad;
    private int wolumen;
    private double obroty;

    public Spolka(String nazwa, double cena, int nAkcji, double kapitalWlasny, double kapitalZaklad) {
        super(nazwa, cena);
        pierwszaWycena = Instant.now();
        this.kursOtwarcia = cena;
        kursMin = kursOtwarcia;
        kursMax = kursOtwarcia;
        this.nAkcji = nAkcji;
        this.kapitalWlasny = kapitalWlasny;
        this.kapitalZaklad = kapitalZaklad;
        zysk = 0;
        przychod = kapitalWlasny/10;
        wolumen = 0;
        obroty = 0;
    }
    public Instant getPierwszaWycena() {
        return pierwszaWycena;
    }

    public void setKursOtwarcia(double kursOtwarcia) {
        this.kursOtwarcia = kursOtwarcia;
    }
    public double getKursOtwarcia() {
        return kursOtwarcia;
    }

    public void setKursMin(double kursMin) {
        this.kursMin = kursMin;
    }
    public double getKursMin() {
        return kursMin;
    }

    public void setKursMax(double kursMax) {
        this.kursMax = kursMax;
    }
    public double getKursMax() {
        return kursMax;
    }

    public void setnAkcji(int nAkcji) {
        this.nAkcji = nAkcji;
    }
    public int getnAkcji() {
        return nAkcji;
    }

    public void setZysk(double zysk) {
        this.zysk = zysk;
    }
    public double getZysk() {
        return zysk;
    }

    public void setPrzychod(double przychod) {
        this.przychod = przychod;
    }
    public double getPrzychod() {
        return przychod;
    }

    public synchronized void setKapitalWlasny(double kapitalWlasny) {
        this.kapitalWlasny = kapitalWlasny;
    }
    public double getKapitalWlasny() {
        return kapitalWlasny;
    }

    public void setKapitalZaklad(double kaptalZaklad) {
        this.kapitalZaklad = kaptalZaklad;
    }
    public double getKapitalZaklad() {
        return kapitalZaklad;
    }

    public synchronized void setWolumen(Integer wolumen) {
        this.wolumen = wolumen;
    }
    public Integer getWolumen() {
        return wolumen;
    }

    public synchronized void setObroty(double obroty) {
        this.obroty = obroty;
    }
    public double getObroty() {
        return obroty;
    }

    public int compareTo(Spolka s1) {
        return (int)(this.getCena() - s1.getCena());
    }
}
