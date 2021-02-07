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
    static JFrame frame;
    static JProgressBar b;
    private static boolean done;

    public static void newWindow()
    {

        // create a frame
        frame = new JFrame("Generating World...");

        // create a panel
        JPanel p = new JPanel();
        Logger.log("LoadingBar Panel Created", Severity.DEBUG);

        // create a progressbar
        b = new JProgressBar();
        Logger.log("LoadingBar Created", Severity.NORMAL);

        // set initial value
        b.setValue(0);
        b.setStringPainted(true);

        // add progressbar
        p.add(b);
        Logger.log("LoadingBar Added", Severity.DEBUG);

        // add panel
        frame.add(p);
        Logger.log("LoadingBar Panel Added", Severity.DEBUG);

        // set the size of the frame
        frame.setSize(200, 80);
        frame.setVisible(true);
    }

    // function to increase progress
    public static void fill(int progress) {
        try {
            if (!done) {
                b.setValue(progress);
            }
        } catch (Exception e) {
            // exception
            Logger.log("Error: LoadingBar Cannot Fill", Severity.FATAL);
        }
    }
    public static void done() {
        // tell progress listener to stop updating progress bar.
        done = true;
        Toolkit.getDefaultToolkit().beep();
        Logger.log("LoadingBar Done", Severity.NORMAL);

        // close window
        frame.setVisible(false);
        frame.dispose();
        Logger.log("LoadingBar Windows Closed", Severity.DEBUG);
    }
}
