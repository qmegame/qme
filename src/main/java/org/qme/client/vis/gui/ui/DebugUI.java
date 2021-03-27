package org.qme.client.vis.gui.ui;

import org.qme.client.vis.gui.GUI;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.gui.comp.QLabel;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.utils.Performance;

import java.awt.*;

public class DebugUI extends GUI {

    public QLabel label;
    public QLabel shadow;

    public DebugUI() {

        int win_size =GLFWInteraction.windowSize();
        int monospace_height = monospace.getHeight();

        this.shadow = new QLabel(monospace, "?!?", 6, win_size - (monospace_height + 3), Color.BLACK);
        this.label = new QLabel(monospace, "?!?", 5, win_size - (monospace_height + 2), Color.WHITE);

        components = new UIComponent[] {
                shadow,
                label
        };

        hide();
    }

    public void update(int fps, int frameCount) {
        String text = "Running game version v" + Performance.GAME_VERSION + " (id:" + Performance.GAME_VERSION_ID + ")" +
                "\nJVM: " + Performance.JAVA_VERSION + " (Vendor: " + Performance.JAVA_VENDOR + ")" +
                "\nOperating System: " + Performance.OPERATING_SYSTEM + " (Arch: " + Performance.ARCH_TYPE + ") (Version: " + Performance.OPERATING_SYSTEM_VERSION + ")" +
                "\nGraphics: " + Performance.GPU_NAME + " " + Performance.GPU_VENDOR +
                "\nMemory: (Max: " + Runtime.getRuntime().totalMemory() / 1000000 + "mb) (Used: " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1000000 + "mb)" +
                "\nProcessor: " + Performance.CPU +
                "\nFPS: " + fps + " (On: " + frameCount + ")";
        label.text = text;
        shadow.text = text;
    }

}
