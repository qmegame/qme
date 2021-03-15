package org.qme.client.vis.gui;

import org.qme.client.vis.gui.ui.DebugUI;
import org.qme.client.vis.gui.ui.MenuUI;
import org.qme.client.vis.gui.ui.ProfilerUI;
import org.qme.client.vis.gui.ui.ResourcesUI;

/**
 * Contains all GUIs that are in the game.
 */
public class GUIManager {

    public static ResourcesUI resourcesUI;
    public static DebugUI debugUI;
    public static ProfilerUI profilerUI;
    public static MenuUI menuUI;

    /**
     * Load everything.
     */
    public static void loadGUIs() {

        resourcesUI = new ResourcesUI();
        debugUI = new DebugUI();
        profilerUI = new ProfilerUI();
        menuUI = new MenuUI();

        // make main menu visible
        menuUI.show();

    }

}
