package gui;

import aktywa.Spolka;
import aktywa.Waluta;
import aktywnosc.Inwestycja;
import aktywnosc.Operacja;
import aktywnosc.TypOperacji;
import helpery.Baza;
import rynek.Gielda;

import javax.swing.*;
import java.awt.*;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Panel okna podglądy aktywów pozwalający na wyświetlenie Spolki, samoodświeżający się
 */
public class PodgladSpolka extends JPanel implements AktywaPanelInterface {
    private JLabel przychod;
    private JLabel zysk;
    private JLabel nAkcji;
    private JLabel aktualnyKurs;
    private JLabel minKurs;
    private JLabel maxKurs;
    private JLabel kapitalWlasny;
    private JLabel wolumen;
    private JLabel obroty;
    private Spolka spolka;

    public PodgladSpolka(Spolka s) {
        super(new GridLayout(2,1));
        spolka = s;

        JPanel glowny = new JPanel(new GridLayout(12,2));
        add(glowny);
        JLabel label1 = new JLabel("Przychód");
        glowny.add(label1);
        przychod = new JLabel(String.format("%.2f", s.getPrzychod()));
        glowny.add(przychod);
        JLabel label2 = new JLabel("Zysk");
        glowny.add(label2);
        zysk = new JLabel(String.format("%.2f", s.getZysk()));
        glowny.add(zysk);
        JLabel label3 = new JLabel("Ilość akcji");
        glowny.add(label3);
        nAkcji = new JLabel(Integer.toString(s.getnAkcji()));
        glowny.add(nAkcji);
        JLabel label4 = new JLabel("Kurs otwarcia");
        glowny.add(label4);
        JLabel kursOtwarcia = new JLabel(String.format("%.2f", s.getKursOtwarcia()));
        glowny.add(kursOtwarcia);
        JLabel label5 = new JLabel("Aktualny kurs: ");
        glowny.add(label5);
        aktualnyKurs = new JLabel(String.format("%.2f", s.getCena()));
        glowny.add(aktualnyKurs);
        JLabel label6 = new JLabel("Kurs minimalny");
        glowny.add(label6);
        minKurs = new JLabel(String.format("%.2f", s.getKursMin()));
        glowny.add(minKurs);
        JLabel label7 = new JLabel("Kurs maksymalny");
        glowny.add(label7);
        maxKurs = new JLabel(String.format("%.2f", s.getKursMax()));
        glowny.add(maxKurs);
        JLabel label8 = new JLabel("Kapitał własny");
        glowny.add(label8);
        kapitalWlasny = new JLabel(String.format("%.2f", s.getKapitalWlasny()));
        glowny.add(kapitalWlasny);
        JLabel label9 = new JLabel("Kapitał zakładowy");
        glowny.add(label9);
        JLabel kapitalZakladowy = new JLabel(String.format("%.2f", s.getKapitalZaklad()));
        glowny.add(kapitalZakladowy);
        JLabel label10 = new JLabel("Pierwsza wycena");
        glowny.add(label10);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                        .withLocale(Locale.GERMANY )
                        .withZone( ZoneId.systemDefault() );
        JLabel pierwszaWycena = new JLabel(formatter.format(s.getPierwszaWycena()));
        glowny.add(pierwszaWycena);
        JLabel label11 = new JLabel("Wolumen");
        glowny.add(label11);
        wolumen = new JLabel(Integer.toString(s.getWolumen()));
        glowny.add(wolumen);
        JLabel label12 = new JLabel("Obroty");
        glowny.add(label12);
        obroty = new JLabel(String.format("%.2f", s.getObroty()));
        glowny.add(obroty);
        JPanel akcje = new JPanel(new GridLayout(2,3));
        add(akcje);
        JLabel label13 = new JLabel("Ilość");
        akcje.add(label13);
        JLabel label14 = new JLabel("Cena");
        akcje.add(label14);
        JLabel label15 = new JLabel("Wykup akcje");
        akcje.add(label15);
        JTextField iloscWykup = new JTextField();
        iloscWykup.setText(Integer.toString((int)(spolka.getnAkcji()*0.1)));
        akcje.add(iloscWykup);
        JTextField cenaWykupu = new JTextField();
        cenaWykupu.setText(String.format("%.2f", spolka.getCena()));
        akcje.add(cenaWykupu);
        JButton wykup = new JButton("Wykup");
        akcje.add(wykup);
        wykup.addActionListener(e -> {
            Operacja operacjaWykupu = new Operacja(Double.parseDouble(iloscWykup.getText()), null,
                    Double.parseDouble(cenaWykupu.getText()), spolka, TypOperacji.KUP);
            Baza.get().addOperacje(operacjaWykupu);
        });
    }

    public void refresh() {
        przychod.setText(String.format("%.2f", spolka.getPrzychod()));
        zysk.setText(String.format("%.2f", spolka.getZysk()));
        nAkcji.setText(Integer.toString(spolka.getnAkcji()));
        aktualnyKurs.setText(String.format("%.2f", spolka.getCena()));
        minKurs.setText(String.format("%.2f", spolka.getKursMin()));
        maxKurs.setText(String.format("%.2f", spolka.getKursMax()));
        kapitalWlasny.setText(String.format("%.2f", spolka.getKapitalWlasny()));
        wolumen.setText(Integer.toString(spolka.getWolumen()));
        obroty.setText(String.format("%.2f", spolka.getObroty()));
        validate();
        repaint();
    }
}
