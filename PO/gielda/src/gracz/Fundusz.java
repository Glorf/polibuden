package gracz;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasa funduszu - gracz inwestujący za cudze pieniądze
 */

public class Fundusz extends Gracz {
    private String nazwa;
    private Map<Inwestor, Double> inwestorzy;

    public Fundusz(String imie, String nazwisko, String nazwa) {
        super(imie, nazwisko);
        this.nazwa = nazwa;
        inwestorzy = new HashMap<>();
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    public String getNazwa() {
        return nazwa;
    }

    public void wplac(Inwestor inwestor, double kwota) {
        if(!inwestorzy.containsKey(inwestor))
            inwestorzy.put(inwestor, kwota);
        else
            inwestorzy.put(inwestor, inwestorzy.get(inwestor)+kwota);
    }

    public void wyplac(Inwestor inwestor, double kwota) {
        if(inwestorzy.containsKey(inwestor)) {
            inwestorzy.put(inwestor, inwestorzy.get(inwestor)-kwota);
        }
    }

    public double getAmount(Inwestor inwestor) {
        if(inwestorzy.containsKey(inwestor)) {
            return inwestorzy.get(inwestor);
        }
        else
            return 0;
    }
}
