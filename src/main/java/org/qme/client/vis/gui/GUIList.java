package org.qme.client.vis.gui;

import java.awt.*;

/**
 * Contains all GUIs that are in the game.
 */
public class GUIList {

    public static GUI RESOURCE_GUI;

    /**
     * Load everything.
     */
    public static void loadGUIs() {
        RESOURCE_GUI = new GUI(new UIComponent[] {
                new QBox(new Rectangle(5,5, 100, 120))
        });
    }

}
