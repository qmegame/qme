package org.qme.client.vis.gui.comp;

import org.qme.client.Application;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.GLFWInteraction;

import java.awt.*;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

public class QBox extends UIComponent {

    /**
     * Texture atlas that represents all boxes
     */
    private final HashMap<String, Rectangle> atlas = TextureManager.loadAtlas("/textures/misc/box.json");

    public Rectangle rect;

    /**
     * Creates a new QBox object
     * @param rect the box location
     */
    public QBox(Rectangle rect) {
        super();
        this.rect = rect;
    }

    @Override
    public boolean contains(int x, int y) {
        return rect.contains(x, GLFWInteraction.getSize() - y);
    }

    @Override
    public void draw() {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBindTexture(GL_TEXTURE_2D, TextureManager.getTexture("misc/box.png"));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        int bodyWidth = rect.width - rect.width % Application.RENDER_SCALE - ((atlas.get("bottom-left").width * 2)) * Application.RENDER_SCALE;
        int bodyHeight = rect.height - rect.height % Application.RENDER_SCALE - ((atlas.get("bottom-left").height * 2)) * Application.RENDER_SCALE;

        RenderMaster.drawBounds(atlas, new Rectangle(rect.x, rect.y, bodyWidth, bodyHeight));

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }
}
