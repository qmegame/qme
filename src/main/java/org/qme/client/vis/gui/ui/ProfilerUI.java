package org.qme.client.vis.gui.ui;

import org.qme.client.vis.gui.GUI;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.gui.comp.QLabel;
import org.qme.utils.Performance;

import java.awt.*;
import java.util.HashMap;

public class ProfilerUI extends GUI {

    public QLabel label;
    public QLabel shadow;

    public ProfilerUI() {
        this.shadow = new QLabel(monospace, "?!?", 6, 4, Color.BLACK, false);
        this.label = new QLabel(monospace, "?!?", 5, 5, Color.WHITE, false);

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
