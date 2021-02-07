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

    private static HashMap<String, Rectangle> atlas = TextureManager.loadAtlas("/textures/misc/box.json");

    private int actualCornerHeight;
    private int actualCornerWidth;
    private int bodyWidth;
    private int bodyHeight;
    private int actualBodyWidth;
    private int actualBodyHeight;
    private int actualEdgeWidth;
    private int actualEdgeHeight;
    private Dimension texture;

    public Rectangle rect;

    /**
     * Creates a new QBox object
     * @param rect the box location
     */
    public QBox(Rectangle rect) {
        super();
        this.rect = rect;
        this.calculateDimensions();
    }

    /**
     * Calculates values used for rendering.
     * If the dimensions of the QBox has changed they will need to be recalculated.
     */
    public void calculateDimensions() {
        this.actualCornerHeight = atlas.get("bottom-left").height * Application.RENDER_SCALE;
        this.actualCornerWidth = atlas.get("bottom-left").width * Application.RENDER_SCALE;
        this.bodyWidth = (rect.width / Application.RENDER_SCALE) - atlas.get("bottom-left").width * 2;
        this.bodyHeight = (rect.height / Application.RENDER_SCALE) - atlas.get("bottom-left").height * 2;
        this.actualBodyWidth = bodyWidth * Application.RENDER_SCALE;
        this.actualBodyHeight = bodyHeight * Application.RENDER_SCALE;
        this.actualEdgeHeight = atlas.get("edge-left").height * Application.RENDER_SCALE;
        this.actualEdgeWidth = atlas.get("edge-left").width * Application.RENDER_SCALE;
        this.texture = new Dimension(atlas.get("total").width, atlas.get("total").height);
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

        // base for the background of the box
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x + actualEdgeWidth,
                        rect.y + actualEdgeWidth,
                        actualBodyWidth + actualEdgeWidth + actualCornerWidth,
                        actualBodyHeight + actualEdgeWidth + actualCornerHeight
                ),
                atlas.get("fill"), texture);

        // top right corner
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x + actualCornerWidth + actualBodyWidth,
                        rect.y + actualCornerHeight + actualBodyHeight,
                        actualCornerWidth,
                        actualCornerHeight
                ),
                atlas.get("bottom-right"), texture);

        // bottom right corner
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x + actualCornerWidth + actualBodyWidth,
                        rect.y,
                        actualCornerWidth,
                        actualCornerHeight
                ),
                atlas.get("top-right"), texture);

        // top left corner
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x,
                        rect.y + actualCornerHeight + actualBodyHeight,
                        actualCornerWidth,
                        actualCornerHeight
                ),
                atlas.get("bottom-left"), texture);

        // bottom left corner
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x,
                        rect.y,
                        actualCornerWidth,
                        actualCornerHeight
                ),
                atlas.get("top-left"), texture);

        for (int i = 0; i < bodyWidth; i++) {

            // top edge
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x + (i * actualEdgeHeight) + actualCornerWidth,
                            rect.y + (actualCornerHeight * 2) - actualEdgeWidth - actualEdgeHeight + actualBodyHeight,
                            Application.RENDER_SCALE,
                            actualEdgeWidth
                    ),
                    atlas.get("edge-top"), texture);

            // bottom edge
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x + (i * actualEdgeHeight) + actualCornerWidth,
                            rect.y + actualEdgeHeight,
                            actualEdgeHeight,
                            actualEdgeWidth
                    ),
                    atlas.get("edge-bottom"), texture);
        }

        for (int i = 0; i < bodyHeight; i++) {

            // left edge
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x + Application.RENDER_SCALE,
                            rect.y + actualCornerHeight + (actualEdgeHeight * i),
                            actualEdgeWidth,
                            actualEdgeHeight
                    ),
                    atlas.get("edge-left"), texture);

            // right edge
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x + (actualCornerWidth * 2) - actualEdgeWidth - Application.RENDER_SCALE + actualBodyWidth,
                            rect.y + actualCornerHeight + (actualEdgeHeight * i),
                            actualEdgeWidth,
                            actualEdgeHeight
                    ),
                    atlas.get("edge-right"), texture);

        }

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }
}
