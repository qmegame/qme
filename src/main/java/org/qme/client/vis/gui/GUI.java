package org.qme.client.vis.gui;

/**
 * Represents a list of UIComponents with neater ways to show / hide all of the
 * components. An instance should represent one set of related buttons, etc.,
 * like a menu.
 */
public class GUI {

    /**
     * All of the components.
     */
    private final UIComponent[] components;

    /**
     * Default constructor
     */
    public GUI(UIComponent[] components) {
        this.components = components;
        // We're going to load all menus at once, so hidden by default.
        this.hide();
    }

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
