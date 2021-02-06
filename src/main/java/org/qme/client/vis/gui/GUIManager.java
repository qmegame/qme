package org.qme.client.vis.gui;

import org.qme.client.vis.gui.ui.QFont;

import java.awt.*;

/**
 * Contains all GUIs that are in the game.
 */
public class GUIManager {

    public static ResourcesUI resourcesUI;

    /**
     * Load everything.
     */
    public static void loadGUIs() {
        QFont font = new QFont(new Font(Font.MONOSPACED, Font.PLAIN, 12), true);
        resourcesUI = new ResourcesUI();
        /*RESOURCE_GUI = new GUI(new UIComponent[] {
                    new QBox(new Rectangle(5,5, 250, 300)),
                    new QButton(font, "Harvest", new Rectangle(20, 20, 90, 30)) {
                        @Override
                        protected void action() {
                            System.out.println("BOI");
                        }
                    },
                    new QLabel(font, "Resource", 20, 278)
        });*/
    }

}
