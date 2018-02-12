package gui;

import helpery.Baza;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Główne okno programu z załadowaną instancją Bazy, pozwala wykonywać wszelkiego rodzaju akcje oraz śledzić działania graczy
 */
public class OknoGlowne extends JFrame {

    public OknoGlowne() {
        super("Podgląd");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setMenuBar();

        showAktywa();

        setSize(800, 600);
        setLocation(100, 100);
        setVisible(true);
    }

    public void setMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu plik = new JMenu("Plik");
        JMenu nowy = new JMenu("Dodaj...");
        JMenuItem nkraj = new JMenuItem("Kraj");
        nkraj.addActionListener(e -> new OknoNowyKraj());
        nowy.add(nkraj);
        JMenuItem nWaluta = new JMenuItem("Waluta");
        nWaluta.addActionListener(e -> new OknoNowaWaluta());
        nowy.add(nWaluta);
        JMenuItem ngielda = new JMenuItem("Rynek");
        ngielda.addActionListener(e -> new OknoNowyRynek());
        nowy.add(ngielda);
        JMenuItem nindeks = new JMenuItem("Indeks");
        nindeks.addActionListener(e -> new OknoNowyIndeks());
        nowy.add(nindeks);
        JMenuItem naktywa = new JMenuItem("Aktywa");
        naktywa.addActionListener(e -> new OknoNoweAktywa());
        nowy.add(naktywa);
        JMenuItem ngracz = new JMenuItem("Gracz");
        ngracz.addActionListener(e -> new OknoNowyGracz());
        nowy.add(ngracz);
        plik.add(nowy);
        JMenuItem otworz = new JMenuItem("Otwórz");
        otworz.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                Baza.get().close();
                Baza.get().open(chooser.getSelectedFile().getPath());
                this.setTitle(Baza.get().getFilename());
            }
        });
        plik.add(otworz);
        plik.addSeparator();
        JMenuItem zapisz = new JMenuItem("Zapisz");
        zapisz.addActionListener(e -> {
            if(Baza.get().getFilename() != null) {
                Baza.get().saveAs(Baza.get().getFilename());
            }
            else {
                DialogZapisz chooser = new DialogZapisz();
                int returnVal = chooser.showSaveDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    Baza.get().saveAs(chooser.getSelectedFile().getPath());
                    this.setTitle(Baza.get().getFilename());
                }
            }
        });
        plik.add(zapisz);
        JMenuItem zapiszjako = new JMenuItem("Zapisz jako...");
        zapiszjako.addActionListener(e -> {
            DialogZapisz chooser = new DialogZapisz();
            int returnVal = chooser.showSaveDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                Baza.get().saveAs(chooser.getSelectedFile().getPath());
                this.setTitle(Baza.get().getFilename());
            }
        });
        plik.add(zapiszjako);
        plik.addSeparator();
        JMenuItem zamknij = new JMenuItem("Zamknij symulację");
        zamknij.addActionListener(e -> {
            setVisible(false);
            dispose();
            Baza.get().close();
            new OknoStartowe();
        });
        plik.add(zamknij);
        JMenuItem wyjdz = new JMenuItem("Wyjdź");
        wyjdz.addActionListener(e -> System.exit(0));
        plik.add(wyjdz);
        menuBar.add(plik);

        JMenu widok = new JMenu("Widok");
        ButtonGroup rgrupa = new ButtonGroup();
        JRadioButtonMenuItem raktywa = new JRadioButtonMenuItem("Aktywa");
        raktywa.setSelected(true);
        rgrupa.add(raktywa);
        raktywa.addActionListener(e -> showAktywa());
        widok.add(raktywa);
        JRadioButtonMenuItem rrynki = new JRadioButtonMenuItem("Rynki");
        rgrupa.add(rrynki);
        rrynki.addActionListener(e -> showRynki());
        widok.add(rrynki);
        JRadioButtonMenuItem rkraje = new JRadioButtonMenuItem("Kraje");
        rgrupa.add(rkraje);
        rkraje.addActionListener(e -> showKraje());
        widok.add(rkraje);
        JRadioButtonMenuItem rgracze = new JRadioButtonMenuItem("Gracze");
        rgrupa.add(rgracze);
        rgracze.addActionListener(e -> showGracze());
        widok.add(rgracze);
        JRadioButtonMenuItem rwaluty = new JRadioButtonMenuItem("Waluty");
        rgrupa.add(rwaluty);
        rwaluty.addActionListener(e -> showWaluty());
        widok.add(rwaluty);
        JRadioButtonMenuItem rwykres = new JRadioButtonMenuItem("Wykres");
        rgrupa.add(rwykres);
        rwykres.addActionListener(e-> showWykres());
        widok.add(rwykres);
        menuBar.add(widok);

        JMenu pomoc = new JMenu("Pomoc");
        JMenuItem about = new JMenuItem("O programie...");
        about.addActionListener(e -> {
            new OProgramie();
        });
        pomoc.add(about);
        menuBar.add(pomoc);
        setJMenuBar(menuBar);
    }

    public void showKraje() {
        JPanel aktywnyPanel = new PodgladKraje();
        setContentPane(aktywnyPanel);
        validate();
        repaint();
    }

    public void showAktywa(){
        JPanel aktywnyPanel = new PodgladAktywa();
        setContentPane(aktywnyPanel);
        validate();
        repaint();
    }

    public void showRynki(){
        JPanel aktywnyPanel = new PodgladRynki();
        setContentPane(aktywnyPanel);
        validate();
        repaint();
    }

    public void showGracze() {
        JPanel aktywnyPanel = new PodgladGracze();
        setContentPane(aktywnyPanel);
        validate();
        repaint();
    }

    public void showWaluty() {
        JPanel aktywnyPanel = new PodgladWaluty();
        setContentPane(aktywnyPanel);
        validate();
        repaint();
    }

    public void showWykres() {
        JPanel aktywnyPanel = new PodgladWykres();
        setContentPane(aktywnyPanel);
        validate();
        repaint();
    }
}
