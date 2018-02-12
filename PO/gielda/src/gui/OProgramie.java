package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Bo czemu nie?
 */

public class OProgramie extends JFrame {
    public OProgramie() {
        super("Symulator giełdy");
        JPanel panel = new JPanel(new GridLayout(2,1));
        JLabel label = new JLabel("Symulator Giełdy", JLabel.CENTER);
        panel.add(label);
        JLabel label2 = new JLabel("Copyright Michał Bień 2017", JLabel.CENTER);
        panel.add(label2);

        add(panel);

        setSize(200, 100);
        setLocation(250, 250);
        setVisible(true);
    }
}
