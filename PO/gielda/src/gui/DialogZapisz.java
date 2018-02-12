package gui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Customowe okno dialogowe (polski "zapisz" i inne wodotryski)
 */
public class DialogZapisz extends JFileChooser {
    DialogZapisz() {
        super();
        setApproveButtonText("Zapisz");
        setDialogTitle("Wybierz plik");
        setFileFilter(new FileFilter() {

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
    }
}
