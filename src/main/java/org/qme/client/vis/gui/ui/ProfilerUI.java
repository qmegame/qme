package org.qme.client.vis.gui.ui;

import org.qme.client.vis.gui.GUI;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.gui.comp.QFont;
import org.qme.client.vis.gui.comp.QLabel;
import org.qme.utils.Performance;

import java.awt.*;
import java.util.HashMap;

public class ProfilerUI extends GUI {

    public QLabel label;
    public QLabel shadow;

    public ProfilerUI() {
        QFont font = new QFont(new Font(Font.MONOSPACED, Font.PLAIN, 12), true);
        this.shadow = new QLabel(font, "?!?", 6, 4, Color.BLACK, false);
        this.label = new QLabel(font, "?!?", 5, 5, Color.WHITE, false);

        components = new UIComponent[] {
                shadow,
                label
        };

        hide();
    }

    public void update(HashMap<String, Float> timings) {
        String text = "Profiler [Render] [Tick] [Other]" +
                "\nRender: " + timings.getOrDefault("render", 0F) + "ms" +
                "\nTick: " + timings.getOrDefault("tick", 0F) + "ms" +
                "\nTotal: " + Performance.getTotal() + "ms";
        label.text = text;
        shadow.text = text;
    }

}
