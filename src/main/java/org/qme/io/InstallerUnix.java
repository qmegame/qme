package org.qme.io;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.lang.Runtime;

public class InstallerUnix {

    /**
     * The all important function that installs the game
     * (It's frontend's problem now)
     * @author santiago
     * @since preB
     * @param location Where the game will be put
     */
    public void Install(File location) {
        if(InstallerUnix.filesDownloaded()) {
            try {
                Runtime.getRuntime().exec("mv resources/ " + location.getPath());
                //qme5.jar is a placeholder name
                Runtime.getRuntime().exec("mv qme5.jar " + location.getPath());
            } catch(IOException e) {
                JOptionPane.showMessageDialog(null,
                        "Issue installing files to selected location. Grant necessary permissions.",
                        null, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Files to install missing. Try the download again.", null,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Checks to make sure everything has been downloaded
     * TODO: update with stuff when more resources area added
     * @since preB
     * @author santiago
     * @return Whether or not all the files have been downloaded
     */
    private static boolean filesDownloaded() {
        // This is gonna get cursed
        final File resources = new File("resources/");
        final File textures = new File("resources/textures/");
        final File desertPng = new File("resources/textures/desert.png");
        final File fertilePlainsPng = new File("resources/textures/fertile-plains.png");
        final File forestPng = new File("resources/textures/forest.png");
        final File highMountainPng = new File("resources/textures/high-mountain.png");
        final File missingPng = new File("resources/textures/missing.png");
        final File mountainPng = new File("resources/textures/mountain.png");
        final File oceanPng = new File("resources/textures/ocean.png");
        final File plainsPng = new File("resources/textures/plains.png");
        final File seaPng = new File("resources/textures/sea.png");

        final File qme5Jar = new File("qme5.jar");

        return resources.exists() && textures.exists() && desertPng.exists() &&
                fertilePlainsPng.exists() && forestPng.exists() &&
                highMountainPng.exists() && missingPng.exists() &&
                mountainPng.exists() && oceanPng.exists() && plainsPng.exists() &&
                seaPng.exists() && qme5Jar.exists();
    }
}
