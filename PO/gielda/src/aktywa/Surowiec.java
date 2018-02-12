package aktywa;


/**
 * Aktywo - surowiec
 */
public class Surowiec extends Aktywa {
    private String jednostka;
    private Waluta waluta;
    private double minWartosc;
    private double maxWartosc;

    public Surowiec(String nazwa, double cena, Waluta waluta, String jednostka) {
        super(nazwa, cena);
        this.waluta = waluta;
        this.jednostka = jednostka;
        minWartosc = cena;
        maxWartosc = cena;
    }

    public void setJednostka(String jednostka) {
        this.jednostka = jednostka;
    }
    public String getJednostka() {
        return jednostka;
    }

    public void setWaluta(Waluta waluta) {
        this.waluta = waluta;
    }
    public Waluta getWaluta() {
        return waluta;
    }

    public void setMinWartosc(double minWartosc) {
        this.minWartosc = minWartosc;
    }
    public double getMinWartosc() {
        return minWartosc;
    }

    public void setMaxWartosc(double maxWartosc) {
        this.maxWartosc = maxWartosc;
    }
    public double getMaxWartosc() {
        return maxWartosc;
    }
}
