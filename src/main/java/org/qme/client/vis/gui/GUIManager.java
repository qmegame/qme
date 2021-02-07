package org.qme.client.vis.gui;

import org.qme.client.vis.gui.ui.ResourcesUI;

/**
 * Contains all GUIs that are in the game.
 */
public class GUIManager {

    public static ResourcesUI resourcesUI;

    /**
     * Load everything.
     */
    public static void loadGUIs() {
        resourcesUI = new ResourcesUI();
    }

}
