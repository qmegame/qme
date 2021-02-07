package org.qme.client.vis;

import org.qme.io.Logger;
import org.qme.io.Severity;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The class that handles the LoadingBar for the world generation
 * @author Tom
 * @since 0.3.2
 */

public class LoadingBar extends JFrame {

    // create a frame
    static JFrame f;
    static JProgressBar b;
    private static boolean done;

    public static void newWindow()
    {

        // create a frame
        f = new JFrame("Generating World...");

        // create a panel
        JPanel p = new JPanel();
        Logger.log("LoadingBar Panel Created", Severity.NORMAL);

        // create a progressbar
        b = new JProgressBar();
        Logger.log("LoadingBar Created", Severity.NORMAL);

        // set initial value
        b.setValue(0);
        b.setStringPainted(true);

        // add progressbar
        p.add(b);
        Logger.log("LoadingBar  Added", Severity.NORMAL);

        // add panel
        f.add(p);
        Logger.log("LoadingBar Panel Added", Severity.NORMAL);

        // set the size of the frame
        f.setSize(150, 100);
        f.setVisible(true);
    }

    // function to increase progress
    public static void fill() {
        try {
            if (!done) {
                int progress = task.getProgress();
                b.setValue(progress);
            }
        } catch (Exception e) {
            // Exception
            Logger.log("Error: LoadingBar Cannot Fill", Severity.FATAL);
        }
    }
    public void done() {
        //Tell progress listener to stop updating progress bar.
        done = true;
        Toolkit.getDefaultToolkit().beep();
        setCursor(null); //turn off the wait cursor
        b.setValue(b.getMinimum());
        Logger.log("LoadingBar Done", Severity.FATAL);
    }
}
