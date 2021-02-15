package org.qme.client.vis.gui;

import org.qme.client.vis.gui.ui.OptionsUI;
import org.qme.client.vis.gui.ui.PauseUI;
import org.qme.client.vis.gui.ui.ResourcesUI;

/**
 * Contains all GUIs that are in the game.
 */
public class GUIManager {

    public static ResourcesUI resourcesUI;
    public static PauseUI pauseUI;
    public static OptionsUI optionsUI;

    /**
     * Load everything.
     */
    public static void loadGUIs() {
        resourcesUI = new ResourcesUI();
        pauseUI = new PauseUI();
        optionsUI = new OptionsUI();
    }
}
