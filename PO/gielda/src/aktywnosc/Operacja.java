package aktywnosc;

import aktywa.Aktywa;
import gracz.Gracz;

/**
 * Operacja to inwestycja poszerzona o rodzaj działania. Podstawowy typ zgłaszany Bazie jako chęć dokonania
 * operacji giełdowej, a także składnik historii kazdego Gracza
 */
public class Operacja extends Inwestycja {
    private TypOperacji typ;

    public Operacja(double ilosc, Gracz gracz, double cena, Aktywa aktywa, TypOperacji typOperacji) {
        super(ilosc, gracz, cena, aktywa);
        typ = typOperacji;
    }

    public void setTyp(TypOperacji typ) {
        this.typ = typ;
    }
    public TypOperacji getTyp() {
        return typ;
    }
}
