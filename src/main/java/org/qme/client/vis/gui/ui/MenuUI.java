package org.qme.client.vis.gui.ui;

import org.qme.client.Application;
import org.qme.client.vis.gui.GUI;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.gui.comp.QButton;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.world.World;

import java.awt.*;

public class MenuUI extends GUI {

    public MenuUI() {

        this.components = new UIComponent[] {

                // New game
                new QButton(
                        monospace, "New Game",
                        GLFWInteraction.windowSize() / 2,
                        GLFWInteraction.windowSize() * 3 / 5,
                        100,
                        50,
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
                        GLFWInteraction.windowSize() / 2,
                        GLFWInteraction.windowSize() * 2 / 5,
                        100,
                        50,
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
