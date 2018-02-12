package gracz;

import aktywnosc.Inwestycja;
import aktywnosc.Operacja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Podstawowa jadnostka inwestująca na giełdzie
 */
public class Gracz implements Serializable {
    private String imie;
    private String nazwisko;
    private List<Inwestycja> portfel;
    private List<Operacja> historia;

    public Gracz(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        portfel = new ArrayList<>();
        historia = new ArrayList<>();
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
    public String getImie() {
        return imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public String getNazwisko() {
        return nazwisko;
    }

    public void addInwestycja(Inwestycja inwestycja) {
        portfel.add(inwestycja);
    }
    public List<Inwestycja> getPortfel() {
        return portfel;
    }

    public void addOperacja(Operacja operacja) {
        historia.add(operacja);
    }
    public List<Operacja> getHistoria() {
        return historia;
    }
}
