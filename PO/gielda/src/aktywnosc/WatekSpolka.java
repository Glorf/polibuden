package aktywnosc;

import aktywa.Spolka;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Wątek spółki uruchamiany co 1 sek, generujący przychód i zyski i generujący w losowych momentach nowe akcje
 */
public class WatekSpolka extends Thread implements KillableThread {
    private Spolka spolka;
    private boolean running;

    public WatekSpolka(Spolka spolka) {
        super();
        this.spolka = spolka;
    }

    public void run() {
        running = true;
        while (running) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }

            int rnd = ThreadLocalRandom.current().nextInt(0, 101);
            if (rnd >= 98) {
                int nAkcji = spolka.getnAkcji();
                //powiększ o 10, 20 lub 30%
                spolka.setnAkcji((nAkcji + nAkcji * (rnd - 97) / 10));
            }

            double przychod = spolka.getPrzychod();
            //zmień od -5% do +5%
            double przychod2 = przychod + (przychod * (rnd - 50) / 1000);
            spolka.setPrzychod(przychod2);

            int rndZysk = ThreadLocalRandom.current().nextInt(0, 11);
            spolka.setZysk(przychod2 * rndZysk / 100);

            spolka.setKursMax(Math.max(spolka.getKursMax(), spolka.getCena()));
            spolka.setKursMin(Math.min(spolka.getKursMin(), spolka.getCena()));
        }
    }

    public void kill() {
        running = false;
    }

    public Spolka getSpolka() {
        return spolka;
    }
}