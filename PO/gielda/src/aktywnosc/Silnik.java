package aktywnosc;

import aktywa.Aktywa;
import aktywa.Spolka;
import gracz.Fundusz;
import gracz.Gracz;
import gracz.Inwestor;
import helpery.Baza;
import helpery.Indeks;
import helpery.IndeksNajlepsze;
import rynek.Gielda;
import rynek.Rynek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Odświeżany co 5 sek wątek utrzymujący giełdę - dba o pojawianie się nowych graczy, logowanie wartości aktywów w
 * czasie (niezbędne do plotowania), realizuje zlecenia, pilnuje aktualizacji min i max wartości aktywów, aktualizuje indeksy
 */

public class Silnik extends Thread {
    public void run() {
        if(!Baza.instantiated())
            return;

        //DODAJEMY NOWYCH GRACZY
        int nAktywow = 0;
        for(Rynek r: Baza.get().getRynki()) {
            nAktywow += r.getAktywa().size();
        }

        if(Baza.get().getGracze().size()<nAktywow) {
            int rnd = ThreadLocalRandom.current().nextInt(0,11);

            if(rnd>8) {
                String imiona[] = {"Adam", "Andrzej", "Adrian", "Jarosław", "Antoni", "Jerzy", "Mariusz", "Witold", "Karol"};
                String nazwiska[] = {"Kaczyński", "Waszczykowski", "Duda", "Macierewicz", "Błaszczak", "Małysz", "Wojtyła"};
                String suffixy[] = {"ex", "imex", "impex", "polex","pol","ownia","arnia"};


                String imie = imiona[ThreadLocalRandom.current().nextInt(0, imiona.length)];
                String nazwisko = nazwiska[ThreadLocalRandom.current().nextInt(0, nazwiska.length)];
                if (rnd == 9) {
                    String nazwa = imie.substring(0,3)+ suffixy[ThreadLocalRandom.current().nextInt(0, suffixy.length)];
                    Fundusz f = new Fundusz(imie, nazwisko, nazwa);
                    Baza.get().addGracz(f);
                }
                else if (rnd == 10) {
                    String pesel = "";
                    for(int i=0; i<11; i++){
                        pesel += Integer.toString(ThreadLocalRandom.current().nextInt(0,10));
                    }
                    Inwestor i = new Inwestor(imie, nazwisko, pesel);
                    Baza.get().addGracz(i);
                }
            }
        }

        //ZAPISUJEMY WARTOŚCI AKTYWÓW W CZASIE
        for(Rynek<Aktywa> r: Baza.get().getRynki()) {
            for(Aktywa a: r.getAktywa()) {
                a.notujWartosc();
            }
        }

        //REALIZUJEMY ZLECENIA
        for(Operacja o: Baza.get().getOperacje()) {
            if(o.getTyp() == TypOperacji.KUP) {
                if(o.getInwestor() == null) {
                    Spolka s = (Spolka)o.getAktywa();
                    s.setnAkcji(s.getnAkcji()-(int)o.getIlosc());
                    s.setKapitalWlasny(Math.max(0, s.getKapitalWlasny()-(s.getCena()*o.getIlosc())));
                    double wzrost = o.getIlosc()/s.getnAkcji()*s.getCena();
                    s.setCena(s.getCena()+wzrost);
                }
                else {
                    Aktywa a = o.getAktywa();
                    Gracz g = o.getInwestor();

                    Inwestycja inwestycja = null;
                    for(Inwestycja i: o.getInwestor().getPortfel()) {
                        if(i.getAktywa().equals(o.getAktywa())) {
                            inwestycja = i;
                            inwestycja.setIlosc(inwestycja.getIlosc()+o.getIlosc());
                            inwestycja.setCena(o.getCena());
                            break;
                        }
                    }

                    if(inwestycja == null) {
                        inwestycja = new Inwestycja(o.getIlosc(), g, o.getAktywa().getCena(), o.getAktywa());
                        g.addInwestycja(inwestycja);
                    }
                    double wzrost = a.getCena()*ThreadLocalRandom.current().nextDouble(0, 0.15);
                    a.setCena(a.getCena()+wzrost);
                    if(a instanceof Spolka) {
                        Spolka s = (Spolka)a;
                        s.setObroty(s.getObroty()+(s.getCena()*o.getIlosc()));
                        s.setWolumen(s.getWolumen()+(int)o.getIlosc());
                    }
                }
            }
            else {
                Inwestycja inwestycja = null;
                for(Inwestycja i: o.getInwestor().getPortfel()) {
                    if(i.getAktywa().equals(o.getAktywa())) {
                        inwestycja = i;
                        break;
                    }
                }

                inwestycja.setIlosc(inwestycja.getIlosc()-o.getIlosc());
                inwestycja.setCena(o.getCena());
                double malej = o.getAktywa().getCena()*ThreadLocalRandom.current().nextDouble(0, 0.10);
                o.getAktywa().setCena(o.getAktywa().getCena()-malej);
            }
        }
        Baza.get().getOperacje().clear();

        //AKTUALIZUJEMY INDEKSY TOP
        for(Rynek r: Baza.get().getRynki()) {
            if(r instanceof Gielda) {
                Gielda g = (Gielda)r;
                for(Indeks i: g.getIndeksy()) {
                    if(i instanceof IndeksNajlepsze) {
                        ((IndeksNajlepsze)i).refresh();
                    }
                }
            }
        }
    }
}
