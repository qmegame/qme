package org.qme.init;

import org.qme.client.Application;
import org.qme.client.vis.gui.GUIManager;
import org.qme.client.vis.gui.comp.QFont;
import org.qme.client.vis.gui.comp.QLabel;
import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.io.Logger;
import org.qme.io.Severity;
import org.qme.utils.Language;
import org.qme.utils.Performance;
import org.qme.world.World;

import java.awt.*;
import java.util.Locale;

public class AssetInit {

    public static void init() {

        Logger.log("Starting GUI init ...", Severity.DEBUG);

        // Load textures
        new TextureManager();

        Logger.log("Successfully initialized textures", Severity.DEBUG);

        Application.world = new World();

        Language.switchLanguage(new Locale("en", "US"));

        Logger.log("Finished initializing languages", Severity.DEBUG);

        GUIManager.loadGUIs();

        Logger.log("Finished loading main GUIs", Severity.DEBUG);

        QFont labelMono = new QFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 12), true);
        QFont buttonFont = new QFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 18), true);

        // Debug Label
        Application.debugLabel = new QLabel(labelMono, "...", 5, GLFWInteraction.windowSize() - (labelMono.getHeight() + 2), Color.WHITE, true);
        Application.debugLabel.setVisible(false);

        // Profiler Label
        Application.profilerLabel = new QLabel(labelMono, "...", 5, 5, Color.WHITE, false);
        Application.profilerLabel.setVisible(false);

        Logger.log("Finished loading miscellaneous GUIs", Severity.DEBUG);

        // Update debug information
        Performance.updateValues();

        Logger.log("Finished initialization, entering main loop", Severity.NORMAL);

    }

}
