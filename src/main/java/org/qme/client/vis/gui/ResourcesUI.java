package org.qme.client.vis.gui;

import org.qme.client.vis.gui.ui.QBox;
import org.qme.client.vis.gui.ui.QButton;
import org.qme.client.vis.gui.ui.QLabel;
import org.qme.utils.Language;
import org.qme.world.Tile;
import org.qme.world.TileType;
import org.qme.world.res.Resource;

import java.awt.*;
import java.util.ArrayList;

public class ResourcesUI extends GUI {

    private QBox box;
    private QButton harvest;
    private QLabel label;

    public ResourcesUI() {

        box = new QBox(new Rectangle(5, 5, 250, 300));
        harvest = new QButton(monospace, Language.getTranslation("HARVEST"), new Rectangle(20, 20, 90, 30)) {
            @Override
            protected void action() {
                System.out.println("BOI");
            }
        };
        label = new QLabel(monospace, "", 20, 278);

    }

    public void showFor(Tile tile) {

        String resourceList = "";

        for (Resource resource : tile.resources) {
            resourceList += "\n- " + Language.getTranslation(resource.getType().toString());
        }

        this.label.text = Language.getTranslation(tile.type.toString()) + "\n\n" + Language.getTranslation("TILE_CONTAINS") + resourceList;
    }

}
