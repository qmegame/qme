package org.qme.client.vis.gui;

import org.qme.client.Application;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.WindowManager;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class QBox extends UIComponent {

    public static final int BOX_CORNER_HEIGHT = 5;
    public static final int BOX_CORNER_WIDTH = 5;
    public static final int BOX_EDGE_HEIGHT = 2;
    public static final int BOX_EDGE_WIDTH = 2;
    public static final int BOX_HEIGHT = 10;

    public int x;
    public int y;
    public int x2;
    public int y2;

    /**
     * Creates a new QBox object
     * @param x the bottom left x coordinate
     * @param y the bottom left y coordinate
     * @param x2 the top right x coordinate
     * @param y2 the top right y coordinate
     */
    public QBox(int x, int y, int x2, int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;

        WindowManager.addObject(this);
    }

    /**
     * Gets the width of this box
     * @return the width of the box
     */
    public int getWidth() {
        return x2 - x;
    }

    /**
     * Gets the height of this box
     * @return the height of the box
     */
    public int getHeight() {
        return y2 - y;
    }

    @Override
    public void draw() {

        // TODO: Create texture atlas class for doing all this stuff automatically

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBindTexture(GL_TEXTURE_2D, TextureManager.getTexture("misc/box.png"));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        int actualCornerHeight = BOX_CORNER_HEIGHT * Application.RENDER_SCALE;
        int actualCornerWidth = BOX_CORNER_WIDTH * Application.RENDER_SCALE;

        int width = getWidth() - BOX_CORNER_WIDTH * 2;
        int height = getHeight() - BOX_CORNER_HEIGHT * 2;

        int actualWidth = width * Application.RENDER_SCALE;
        int actualHeight = height * Application.RENDER_SCALE;

        int actualEdgeHeight = BOX_EDGE_HEIGHT * Application.RENDER_SCALE;
        int actualEdgeWidth = BOX_EDGE_WIDTH * Application.RENDER_SCALE;

        Dimension texture = new Dimension(12, 10);

        // base for the background of the box
        RenderMaster.drawRegion(
                new Rectangle(x + Application.RENDER_SCALE, y + Application.RENDER_SCALE, actualWidth + actualCornerWidth + Application.RENDER_SCALE + actualEdgeWidth, actualHeight + actualCornerHeight + Application.RENDER_SCALE + actualEdgeHeight),
                new Rectangle(BOX_CORNER_WIDTH * 2, BOX_HEIGHT - 1, 1, 1), texture);

        // top right corner
        RenderMaster.drawRegion(
                new Rectangle(x + actualCornerWidth + actualWidth, y + actualCornerHeight + actualHeight, actualCornerWidth, actualCornerHeight),
                new Rectangle(BOX_CORNER_WIDTH, 0, BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT), texture);
        // bottom right corner
        RenderMaster.drawRegion(
                new Rectangle(x + actualCornerWidth + actualWidth, y, actualCornerWidth, actualCornerHeight),
                new Rectangle(BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT, BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT), texture);
        // bottom left corner
        RenderMaster.drawRegion(
                new Rectangle(x, y + actualCornerHeight + actualHeight, actualCornerWidth, actualCornerHeight),
                new Rectangle(0, 0, BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT), texture);
        // top left corner
        RenderMaster.drawRegion(
                new Rectangle(x, y, actualCornerWidth, actualCornerHeight),
                new Rectangle(0, BOX_CORNER_HEIGHT, BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT), texture);

        for (int i = 0; i < width; i++) {

            // top edge
            RenderMaster.drawRegion(
                    new Rectangle(x + (i * Application.RENDER_SCALE) + actualCornerWidth, y + (actualCornerHeight * 2) - actualEdgeHeight - Application.RENDER_SCALE + actualHeight, Application.RENDER_SCALE, actualEdgeHeight),
                    new Rectangle(BOX_CORNER_WIDTH * 2, BOX_HEIGHT, 1, 2), texture);

            // bottom edge
            RenderMaster.drawRegion(
                    new Rectangle(x + (i * Application.RENDER_SCALE) + actualCornerWidth, y + Application.RENDER_SCALE, Application.RENDER_SCALE, actualEdgeHeight),
                    new Rectangle(BOX_CORNER_WIDTH * 2 + 1, BOX_HEIGHT, 1, 2), texture);
        }

        for (int i = 0; i < height; i++) {

            // left edge
            RenderMaster.drawRegion(
                    new Rectangle(x + Application.RENDER_SCALE, y + actualCornerHeight + (Application.RENDER_SCALE * i), actualEdgeWidth, Application.RENDER_SCALE),
                    new Rectangle(BOX_CORNER_WIDTH * 2, BOX_HEIGHT + 3, 2, 1), texture);

            // right edge
            RenderMaster.drawRegion(
                    new Rectangle(x + (actualCornerWidth * 2) - actualEdgeWidth - Application.RENDER_SCALE + actualWidth, y + actualCornerHeight + (Application.RENDER_SCALE * i), actualEdgeWidth, Application.RENDER_SCALE),
                    new Rectangle(BOX_CORNER_WIDTH * 2, BOX_HEIGHT + 2, 2, 1), texture);

        }

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }
}
