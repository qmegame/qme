package org.qme.client.vis.gui.ui;

import org.qme.client.vis.gui.GUI;
import org.qme.client.vis.gui.comp.QBox;
import org.qme.client.vis.gui.comp.QButton;
import org.qme.client.vis.gui.comp.QLabel;
import org.qme.io.Logger;
import org.qme.io.Severity;
import org.qme.utils.Language;
import org.qme.world.Tile;
import org.qme.world.res.Resource;

import java.awt.*;

/**
 * This UI shows information about a tile
 * @since 0.3.0
 * @author cameron
 */
public class ResourcesUI extends GUI {

    private QBox box;
    private QButton harvest;
    private QLabel label;

    /**
     * Creates a new instance of ResourceUI
     * Should only be called once unless you want to create multiple UIs for some reason.
     */
    public ResourcesUI() {

        box = new QBox(new Rectangle(5, 5, 250, 300));
        harvest = new QButton(monospace, Language.getTranslation("HARVEST"), new Rectangle(20, 20, 90, 30)) {
            @Override
            protected void action() {
                Logger.log("Harvest button pushed", Severity.NORMAL);
            }
        };
        label = new QLabel(monospace, "", 20, 278);

    }

    /**
     * Changes the information in this UI to show for a specified tile
     * @param tile the tile to show for
     */
    public void showFor(Tile tile) {

        String resourceList = "";

        if (tile.resources.size() > 0) {
            for (Resource resource : tile.resources) {
                resourceList += "\n- " + Language.getTranslation(resource.getType().toString());
            }

            resourceList = Language.getTranslation("TILE_CONTAINS") + resourceList;
            this.harvest.setClickable(true);
        } else {
            resourceList = Language.getTranslation("NO_RESOURCES");
            this.harvest.setClickable(false);
        }

        this.label.text = Language.getTranslation(tile.type.toString()) + "\n" + Language.getTranslation(tile.type.toString() + "_DESCRIPTION") + "\n\n" + resourceList;
    }

}
