package org.qme.client.vis.gui;

import org.qme.client.vis.gui.comp.QFont;

import java.awt.*;

/**
 * Represents a list of UIComponents with neater ways to show / hide all of the
 * components. An instance should represent one set of related buttons, etc.,
 * like a menu.
 */
public abstract class GUI {

    protected final QFont monospace = new QFont(new Font(Font.DIALOG_INPUT, Font.PLAIN, 12), true);

    /**
     * All of the components.
     */
    protected UIComponent[] components;

    /**
     * Bring up this menu.
     */
    public void show() {
        for (UIComponent c : components) {
            c.setVisible(true);
        }
    }

    /**
     * Hide this menu.
     */
    public void hide() {
        for (UIComponent c : components) {
            c.setVisible(false);
        }
    }

    /**
     * Toggle visibility by checking the first component.
     */
    public void toggle() {
        if (components[0].isVisible()) {
            hide();
        } else {
            show();
        }
    }

}
