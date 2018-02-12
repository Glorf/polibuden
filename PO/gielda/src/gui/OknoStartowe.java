package gui;

import helpery.Baza;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Okno startowe - pozwala stworzyć nową symulację lub wznowić istniejącą
 */
public class OknoStartowe extends JFrame {

    public OknoStartowe() {
        super("Symulator Giełdy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,1));
        setContentPane(panel);
        JLabel label = new JLabel("Symulator Giełdy", JLabel.CENTER);
        panel.add(label);
        JButton nowy = new JButton("Nowa symulacja");
        nowy.addActionListener(e-> {
            new OknoGlowne();
            setVisible(false);
            dispose();
        });
        panel.add(nowy);
        JButton otworz = new JButton("Wznów symulację");
        otworz.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setApproveButtonText("Otwórz");
            chooser.setDialogTitle("Wybierz plik");
            chooser.setFileFilter(new FileFilter() {

                public String getDescription() {
                    return "Pliki Symulacji Giełdy (.psg)";
                }

                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        String filename = f.getName().toLowerCase();
                        return filename.endsWith(".psg");
                    }
                }
            });
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                Baza.get().close();
                Baza.get().open(chooser.getSelectedFile().getPath());
                new OknoGlowne().setTitle(Baza.get().getFilename());

                setVisible(false);
                dispose();
            }
        });
        panel.add(otworz);


        pack();
        setLocation(100, 100);
        setVisible(true);
    }
}
