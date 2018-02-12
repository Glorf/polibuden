package gracz;

import java.util.ArrayList;
import java.util.List;

/**
 * Prywatny inwestor - może inwestować zarówno w Aktywa jak i Fundusze
 */
public class Inwestor extends Gracz {
    private String pesel;
    private double budzet;
    private List<Fundusz> fundusze;

    public Inwestor(String imie, String nazwisko, String pesel) {
        super(imie, nazwisko);
        this.pesel = pesel;
        fundusze = new ArrayList<>();
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
    public String getPesel() {
        return pesel;
    }

    public double getBudzet() {
        return budzet;
    }

    public void setBudzet(double wartosc) {
        budzet = wartosc;
    }

    public void addFundusz(Fundusz fundusz) {
        fundusze.add(fundusz);
    }
    public List<Fundusz> getFundusze() {
        return fundusze;
    }

    public void zwiekszBudzet(double wartosc) {
        budzet+=wartosc;
    }
}
