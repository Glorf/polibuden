package helpery;

import aktywa.Spolka;
import aktywa.Waluta;
import aktywnosc.KillableThread;
import aktywnosc.Operacja;
import aktywnosc.WatekGracz;
import aktywnosc.WatekSpolka;
import gracz.Gracz;
import rynek.Gielda;
import rynek.Rynek;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa singletonowa służąca za magazyn oraz obsługująca serializację; zarządza działającymi wątkami
 */
public class Baza {
    private static Baza instancja = null;
    private List<Rynek> rynki;
    private List<Kraj> kraje;
    private List<Gracz> gracze;
    private List<Waluta> waluty;
    private String filename;
    private List<Thread> watki;
    private List<Operacja> operacje;

    private Baza() {
        rynki = new ArrayList<>();
        kraje = new ArrayList<>();
        gracze = new ArrayList<>();
        watki = new ArrayList<>();
        waluty = new ArrayList<>();
        operacje = new ArrayList<>();
    }

    public synchronized void open(String filename) {
        zatrzymajWatki();
        instancja.filename = filename;

        ObjectInputStream in = null;

        try {
            try {
                in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));
                while (true) {
                    Object obj = in.readObject();
                    if (obj instanceof Kraj)
                        addKraj((Kraj) obj);
                    else if(obj instanceof Rynek)
                        addRynek((Rynek) obj);
                    else if(obj instanceof Gracz)
                        addGracz((Gracz) obj);
                    else if(obj instanceof Waluta)
                        addWaluta((Waluta) obj);
                    else if(obj instanceof Operacja)
                        addOperacje((Operacja) obj);
                }
            } catch (ClassNotFoundException f) {
                System.out.println("Error while reading selected file!");
            } finally {
                in.close();
            }
        }
        catch(IOException e) {
            System.out.println("Loaded");

            odpalWatki();
        }
    }

    public synchronized void saveAs(String filename) {
        zatrzymajWatki();
        instancja.filename = filename;
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
            for(Kraj k: kraje) {
                out.writeObject(k);
            }
            for(Rynek r: rynki) {
                out.writeObject(r);
            }
            for(Gracz g: gracze) {
                out.writeObject(g);
            }
            for(Waluta w: waluty) {
                out.writeObject(w);
            }
            for(Operacja o: operacje) {
                out.writeObject(o);
            }
            out.close();
        }
        catch (Exception e) {
            System.out.println("Nie ma takiego pliku!");
        }
        odpalWatki();
    }

    public void close() {
        zatrzymajWatki();
        instancja = null;
    }

    public static synchronized Baza get() {
        if(instancja == null) {
            instancja = new Baza();
        }

        return instancja;
    }

    public static synchronized boolean instantiated() {
        if(instancja == null)
            return false;
        else
            return true;
    }

    public synchronized void addRynek(Rynek rynek) {
        rynki.add(rynek);
        Tablice.getRynkiModel().fireTableDataChanged();
    }
    public List<Rynek> getRynki() {
        return rynki;
    }

    public synchronized void addKraj(Kraj kraj) {
        kraje.add(kraj);
        Tablice.getKrajeModel().fireTableDataChanged();
    }
    public List<Kraj> getKraje() {
        return kraje;
    }

    public synchronized void addGracz(Gracz gracz) {
        gracze.add(gracz);
        WatekGracz watekGracz = new WatekGracz(gracz);
        nowyWatek(watekGracz);
        Tablice.getGraczeModel().fireTableDataChanged();
    }
    public List<Gracz> getGracze() {
        return gracze;
    }

    public synchronized void addWaluta(Waluta waluta) {
        waluty.add(waluta);
        Tablice.getWalutyModel().fireTableDataChanged();
    }

    public List<Waluta> getWaluty() {
        return waluty;
    }

    public String getFilename() {
        return filename;
    }

    public synchronized void nowyWatek(Thread watek) {
        watki.add(watek);
        watek.start();
    }

    public synchronized void usunWatekSpolki(Spolka s) {
        for(Thread t: watki) {
            if(t instanceof WatekSpolka) {
                if(((WatekSpolka)t).getSpolka().equals(s)) {
                    ((KillableThread)t).kill();
                    watki.remove(t);
                    return;
                }
            }
        }
    }

    public synchronized void usunGracza(Gracz g) {
        for(Thread t: watki) {
            if(t instanceof WatekGracz) {
                if(((WatekGracz)t).getGracz().equals(g)) {
                    ((KillableThread)t).kill();
                    watki.remove(t);
                    return;
                }
            }
        }

        gracze.remove(g);
    }

    public synchronized void addOperacje(Operacja operacja) {
        operacje.add(operacja);
    }

    public List<Operacja> getOperacje() {
        return operacje;
    }

    private void odpalWatkiSpolek() {
        for(Rynek<Spolka> r: rynki) {
            if(!(r instanceof Gielda))
                continue;
            for(Spolka s: r.getAktywa()) {
                WatekSpolka w = new WatekSpolka(s);
                nowyWatek(w);
            }
        }
    }

    private void odpalWatki() {
        odpalWatkiSpolek();
        for(Gracz g: gracze) {
            WatekGracz watekGracz = new WatekGracz(g);
            nowyWatek(watekGracz);
        }
    }

    private void zatrzymajWatki() {
        for(Thread t: watki) {
            ((KillableThread)t).kill();
        }
        watki = new ArrayList<>();
    }
}
