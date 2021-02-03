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

    public Rectangle rect;

    /**
     * Creates a new QBox object
     * @param rect the box location
    */
    public QBox(Rectangle rect) {
        this.rect = rect;

        WindowManager.addObject(this);
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

        int width = rect.width - BOX_CORNER_WIDTH * 2;
        int height = rect.height - BOX_CORNER_HEIGHT * 2;

        int actualWidth = width * Application.RENDER_SCALE;
        int actualHeight = height * Application.RENDER_SCALE;

        int actualEdgeHeight = BOX_EDGE_HEIGHT * Application.RENDER_SCALE;
        int actualEdgeWidth = BOX_EDGE_WIDTH * Application.RENDER_SCALE;

        Dimension texture = new Dimension(12, 10);

        // base for the background of the box
        RenderMaster.drawRegion(
                new Rectangle(rect.x + Application.RENDER_SCALE, rect.y + Application.RENDER_SCALE, actualWidth + actualCornerWidth + Application.RENDER_SCALE + actualEdgeWidth, actualHeight + actualCornerHeight + Application.RENDER_SCALE + actualEdgeHeight),
                new Rectangle(BOX_CORNER_WIDTH * 2, BOX_HEIGHT - 1, 1, 1), texture);

        // top right corner
        RenderMaster.drawRegion(
                new Rectangle(rect.x + actualCornerWidth + actualWidth, rect.y + actualCornerHeight + actualHeight, actualCornerWidth, actualCornerHeight),
                new Rectangle(BOX_CORNER_WIDTH, 0, BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT), texture);
        // bottom right corner
        RenderMaster.drawRegion(
                new Rectangle(rect.x + actualCornerWidth + actualWidth, rect.y, actualCornerWidth, actualCornerHeight),
                new Rectangle(BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT, BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT), texture);
        // bottom left corner
        RenderMaster.drawRegion(
                new Rectangle(rect.x, rect.y + actualCornerHeight + actualHeight, actualCornerWidth, actualCornerHeight),
                new Rectangle(0, 0, BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT), texture);
        // top left corner
        RenderMaster.drawRegion(
                new Rectangle(rect.x, rect.y, actualCornerWidth, actualCornerHeight),
                new Rectangle(0, BOX_CORNER_HEIGHT, BOX_CORNER_WIDTH, BOX_CORNER_HEIGHT), texture);

        for (int i = 0; i < width; i++) {

            // top edge
            RenderMaster.drawRegion(
                    new Rectangle(rect.x + (i * Application.RENDER_SCALE) + actualCornerWidth, rect.y + (actualCornerHeight * 2) - actualEdgeHeight - Application.RENDER_SCALE + actualHeight, Application.RENDER_SCALE, actualEdgeHeight),
                    new Rectangle(BOX_CORNER_WIDTH * 2, BOX_HEIGHT, 1, 2), texture);

            // bottom edge
            RenderMaster.drawRegion(
                    new Rectangle(rect.x + (i * Application.RENDER_SCALE) + actualCornerWidth, rect.y + Application.RENDER_SCALE, Application.RENDER_SCALE, actualEdgeHeight),
                    new Rectangle(BOX_CORNER_WIDTH * 2 + 1, BOX_HEIGHT, 1, 2), texture);
        }

        for (int i = 0; i < height; i++) {

            // left edge
            RenderMaster.drawRegion(
                    new Rectangle(rect.x + Application.RENDER_SCALE, rect.y + actualCornerHeight + (Application.RENDER_SCALE * i), actualEdgeWidth, Application.RENDER_SCALE),
                    new Rectangle(BOX_CORNER_WIDTH * 2, BOX_HEIGHT + 3, 2, 1), texture);

            // right edge
            RenderMaster.drawRegion(
                    new Rectangle(rect.x + (actualCornerWidth * 2) - actualEdgeWidth - Application.RENDER_SCALE + actualWidth, rect.y + actualCornerHeight + (Application.RENDER_SCALE * i), actualEdgeWidth, Application.RENDER_SCALE),
                    new Rectangle(BOX_CORNER_WIDTH * 2, BOX_HEIGHT + 2, 2, 1), texture);

        }

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }
}
