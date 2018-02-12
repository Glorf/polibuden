package system;

import aktywnosc.Silnik;
import gui.OknoStartowe;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Uruchamia Silnik, wyświetla okno startowe i idzie po popcorn
 */
public class Main {

    public static void main(String[] args) {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Silnik silnik = new Silnik();
        executor.scheduleAtFixedRate(silnik, 0, 5, TimeUnit.SECONDS);
        new OknoStartowe();
    }
}
