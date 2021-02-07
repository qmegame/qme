package org.qme.client.vis;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoadingBar extends JFrame {

    // create a frame
    static JFrame f;

    static JProgressBar b;

    public static void newWindow()
    {

        // create a frame
        f = new JFrame("Generating World...");

        // create a panel
        JPanel p = new JPanel();

        // create a progressbar
        b = new JProgressBar();

        // set initial value
        b.setValue(0);

        b.setStringPainted(true);

        // add progressbar
        p.add(b);

        // add panel
        f.add(p);

        // set the size of the frame
        f.setSize(150, 100);
        f.setVisible(true);

        fill();
    }

    // function to increase progress
    public static void fill()
    {
        int i = 0;
        try {
            while (i <= 100) {
                // fill the menu bar
                b.setValue(i + 10);

                // delay the thread
                Thread.sleep(1000);
                i += 20;
            }
        }
        catch (Exception e) {
            // Exception
        }
    }
}
