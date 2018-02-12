package aktywnosc;

import aktywa.Aktywa;
import aktywa.Spolka;
import gracz.Fundusz;
import gracz.Gracz;
import gracz.Inwestor;
import helpery.Baza;
import rynek.Rynek;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Wątek gracza uruchamiany co 1 sek i realizujący w losowych momentach operacje kupna-sprzedaży
 */

public class WatekGracz extends Thread implements KillableThread {
    private Gracz gracz;
    private boolean running;

    public WatekGracz(Gracz g) {
        super();
        gracz = g;
    }

    public void run() {
        running = true;
        while(running) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }

            int rnd = ThreadLocalRandom.current().nextInt(0, 101);

            if(rnd>70 && rnd<95) {
                if(rnd<80 && gracz instanceof Inwestor) {
                    Inwestor inwestor = (Inwestor)gracz;
                    List<Fundusz> fundusze = new ArrayList<>();
                    for(Gracz g: Baza.get().getGracze()) {
                        if(g instanceof Fundusz)
                            fundusze.add((Fundusz)g);
                    }
                    if(!fundusze.isEmpty()) {
                        //fundusz
                        if (rnd < 75) { //kup
                            Fundusz f = fundusze.get(ThreadLocalRandom.current().nextInt(0, fundusze.size()));
                            double cena = inwestor.getBudzet()*ThreadLocalRandom.current().nextDouble(0,0.2);
                            f.wplac(inwestor, cena);
                            inwestor.setBudzet(inwestor.getBudzet()-cena);
                            inwestor.addFundusz(f);
                        } else if(!inwestor.getFundusze().isEmpty()) { //sprzedawaj
                            Fundusz f = inwestor.getFundusze().get(ThreadLocalRandom.current().nextInt(0, inwestor.getFundusze().size()));
                            double cena = f.getAmount(inwestor)*ThreadLocalRandom.current().nextDouble(0,0.2);
                            f.wyplac(inwestor, cena);
                        }
                    }
                }
                else {
                    //aktywa
                    if(rnd>=80 && rnd<88) { //kupuj
                        Rynek<Aktywa> rynek = Baza.get().getRynki().get(ThreadLocalRandom.current().nextInt(0, Baza.get().getRynki().size()));
                        Aktywa aktywa;
                        if(!rynek.getAktywa().isEmpty()) {
                            aktywa = rynek.getAktywa().get(ThreadLocalRandom.current().nextInt(0, rynek.getAktywa().size()));
                            int ilosc = ThreadLocalRandom.current().nextInt(0, 100);
                            Operacja operacja = new Operacja(ilosc, gracz, aktywa.getCena(), aktywa, TypOperacji.KUP);
                            gracz.addOperacja(operacja);
                            Baza.get().addOperacje(operacja);
                        }
                    }
                    else if(!gracz.getPortfel().isEmpty()) //sprzedawaj
                    {
                        Inwestycja inwestycja = gracz.getPortfel().get(ThreadLocalRandom.current().nextInt(0, gracz.getPortfel().size()));
                        if(inwestycja.getIlosc()<=0) {
                            gracz.getPortfel().remove(inwestycja);
                            continue;
                        }
                        double ilosc = ThreadLocalRandom.current().nextDouble(0, inwestycja.getIlosc());
                        if(inwestycja.getAktywa() instanceof Spolka) {
                            ilosc = (int)ilosc;
                        }
                        Operacja operacja = new Operacja(ilosc, gracz, inwestycja.getAktywa().getCena(),
                                inwestycja.getAktywa(), TypOperacji.SPRZEDAJ);
                        gracz.addOperacja(operacja);
                        Baza.get().addOperacje(operacja);
                    }
                }
            }
            else if(rnd>=95 && gracz instanceof Inwestor) {
                Inwestor i = (Inwestor) gracz;
                double ilosc = ThreadLocalRandom.current().nextDouble(0, 1000);
                i.zwiekszBudzet(ilosc);
            }
        }
    }

    public void kill() {
        running = false;
    }

    public Gracz getGracz() {
        return gracz;
    }
}
