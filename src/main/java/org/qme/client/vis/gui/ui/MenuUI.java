package org.qme.client.vis.gui.ui;

import org.qme.client.Application;
import org.qme.client.vis.gui.GUI;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.gui.comp.QButton;
import org.qme.client.vis.wn.GLFWInteraction;

import java.awt.*;

public class MenuUI extends GUI {

    public MenuUI() {

        int win_size = GLFWInteraction.windowSize();
        int half_win_size = GLFWInteraction.windowSize() / 2;

        this.components = new UIComponent[] {

                // New game
                new QButton(
                        monospace, "New Game",
                        half_win_size,
                        win_size * 3 / 5,
                        200,
                        80,
                        Color.GRAY
                ) {
                    @Override
                    protected void action() {
                        Application.worldGen = true;
                        MenuUI.this.hide();
                    }
                },

                // Exit
                new QButton(
                        monospace, "Exit",
                        half_win_size,
                        win_size * 2 / 5,
                        200,
                        80,
                        Color.GRAY
                ) {
                    @Override
                    protected void action() {
                        Application.running = false;
                    }
                }

        };

    }

}
