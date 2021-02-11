package org.qme.client.vis.gui.ui;

import org.qme.client.Application;
import org.qme.client.vis.gui.GUI;
import org.qme.client.vis.gui.GUIManager;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.gui.comp.*;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.world.Tile;
import org.qme.world.res.Resource;
import org.qme.world.res.ResourceType;

import java.awt.*;

/**
 * This UI shows game options
 * @since 0.4.0
 * @author cameron
 */
public class OptionsUI extends GUI {

    public QLabel label;
    public QSlider music;
    public QBox box;
    public QButton back;
    public QButton seagull;

    /**
     * Creates a new PauseUI
     */
    public OptionsUI() {

        QFont main = new QFont(new Font(Font.MONOSPACED, Font.PLAIN, 16), true);
        QFont title = new QFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 32), true);

        box = new QBox(new Rectangle(GLFWInteraction.getSize()/2 - 150, GLFWInteraction.getSize()/2 - 150, 300, 250));
        label = new QLabel(title, "Options", GLFWInteraction.getSize()/2 - title.getWidth("Options")/2, GLFWInteraction.getSize()/2 + 40);
        seagull = new QButton(main, "Seagull", new Rectangle(GLFWInteraction.getSize()/2 - 100, GLFWInteraction.getSize()/2 - 20, 200, 40)) {
            @Override
            protected void action() {
                for (Tile[] tileList : Application.world.tiles) {
                    for (Tile tile : tileList) {
                        if (tile != null) {
                            tile.resources.add(new Resource(ResourceType.SEAGULL));
                        }
                    }
                }
                setText("SEAGULLL!!!!!!!");
            }
        };
        music = new QSlider(main, "Volume", new Rectangle(GLFWInteraction.getSize() / 2 - 100, GLFWInteraction.getSize() / 2 - 70, 200, 40)) {
            @Override
            protected void action(float fill) {
                Application.audioPlayer.setVolume(fill / 100);
            }
        };
        music.setFill(40F);
        back = new QButton(main, "Back", new Rectangle(GLFWInteraction.getSize()/2 - 100, GLFWInteraction.getSize()/2 - 120, 200, 40)) {
            @Override
            protected void action() {
                if (isVisible()) {
                    hide();
                    GUIManager.pauseUI.show();
                }
            }
        };

        components = new UIComponent[] {
                label,
                back,
                seagull,
                music,
                box
        };

        hide();

    }

}
