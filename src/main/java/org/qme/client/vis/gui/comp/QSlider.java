package org.qme.client.vis.gui.comp;

import org.qme.client.Application;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.GLFWInteraction;

import java.awt.*;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

/**
 * Creates a new drawable slider object
 * @author cameron
 * @since 0.4.0
 */
public abstract class QSlider extends UIComponent {

    private static final HashMap<String, Rectangle> atlas = TextureManager.loadAtlas("/textures/misc/button.json");

    private int actualCornerHeight;
    private int actualCornerWidth;
    private int actualEdgeWidth;
    private int actualEdgeHeight;

    private String text;
    private QFont font;
    private Dimension textOffset;
    private Rectangle rect;
    private Dimension texture;

    private boolean locked = false;
    private int fill = 100;

    /**
     * Creates a new slider
     * @param font the font to use
     * @param text the text to display
     * @param x x location
     * @param y y location
     * @param width the width of the button
     * @param height the height of the button
     */
    public QSlider(QFont font, String text, int x, int y, int width, int height) {
        this(font, text, new Rectangle(x - (width / 2), y - (height / 2), width, height));
    }

    /**
     * Creates a new slider
     * @param font the font to use
     * @param text the text to display
     * @param rect the button size and location
     */
    public QSlider(QFont font, String text, Rectangle rect) {
        this.font = font;
        this.text = text;
        this.rect = rect;
        textOffset = new Dimension(
                (rect.width - font.getWidth(text)) / 2,
                (rect.height- font.getHeight()) / 2
        );
        this.calculateDimensions();
    }

    /**
     * Gets if the sliders is modifiable by the user
     * @return if the slider is modifiable
     */
    public boolean isLocked() {
        return locked;
    };

    /**
     * Sets if this slider should be modifiable by the user
     * @param b
     */
    public void setLocked(boolean b) {
        locked = b;
    }

    /**
     * Sets the fill percentage
     * @param fill the amount to fill
     */
    public void setFill(float fill) {
        this.fill = (int) ((fill / 100) * rect.width);
    }

    /**
     * Calculates values used for rendering.
     * If the dimensions of the QButton has changed they will need to be recalculated.
     */
    public void calculateDimensions() {
        String state = "pressed-";
        this.actualCornerHeight = atlas.get(state + "bottom-left").height * Application.RENDER_SCALE;
        this.actualCornerWidth = atlas.get(state + "bottom-left").width * Application.RENDER_SCALE;
        this.actualEdgeHeight = atlas.get(state + "left").height * Application.RENDER_SCALE;
        this.actualEdgeWidth = atlas.get(state + "left").width * Application.RENDER_SCALE;
        this.texture = new Dimension(atlas.get("total").width, atlas.get("total").height);
    }

    /**
     * Called when the button is clicked
     */
    protected abstract void action(float fill);

    @Override
    public boolean contains(int x, int y) {

        // TODO: This is ugly asf needs an api method instead of putting it into contains
        if (rect.contains(x, GLFWInteraction.windowSize() - y)) {

            if (isClicked() && !isLocked() && isVisible()) {
                float fill = ((float)(x - (rect.x - 10)) / rect.width) * 100 - 5;
                setFill(fill);
                action(fill);
            }
            return true;
        }

        return false;
    }

    @Override
    public void draw() {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBindTexture(GL_TEXTURE_2D, TextureManager.getTexture("misc/button.png"));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        int bodyWidth = (rect.width / Application.RENDER_SCALE) - atlas.get("disabled-" + "bottom-left").width * 2;
        int bodyHeight = (rect.height / Application.RENDER_SCALE) - atlas.get("disabled-" + "bottom-left").height * 2;

        drawBounds(bodyWidth, bodyHeight, "disabled-", true);

        bodyWidth = (Math.max(15, fill) / Application.RENDER_SCALE) - atlas.get((isClicked() ? "pressed" : "unpressed") + "-" + "bottom-left").width * 2;

        drawBounds(bodyWidth, bodyHeight, (isClicked() ? "pressed" : "unpressed") + "-", true);

        font.drawText(text, rect.x + textOffset.width, rect.y + textOffset.height);

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }

    /**
     * Draws the features of the slider
     * @param bodyWidth the width of the slider in pixels relative to the textures
     * @param bodyHeight the height of the slider in pixels relative to the texture
     * @param state the state to draw
     * @param drawEnd if the end of the slider should draw
     */
    private void drawBounds(int bodyWidth, int bodyHeight, String state, boolean drawEnd) {
        int actualBodyWidth = bodyWidth * Application.RENDER_SCALE;
        int actualBodyHeight = bodyHeight * Application.RENDER_SCALE;
        // base for the background of the box
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x + actualEdgeWidth,
                        rect.y + actualEdgeWidth,
                        actualBodyWidth + actualEdgeWidth,
                        actualBodyHeight + actualCornerHeight
                ),
                atlas.get(state + "fill"), texture);

        if (drawEnd) {
            // top right corner
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x + actualCornerWidth + actualBodyWidth,
                            rect.y + actualCornerHeight + actualBodyHeight,
                            actualCornerWidth,
                            actualCornerHeight
                    ),
                    atlas.get(state + "top-right"), texture);

            // bottom right corner
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x + actualCornerWidth + actualBodyWidth,
                            rect.y,
                            actualCornerWidth,
                            actualCornerHeight
                    ),
                    atlas.get(state + "bottom-right"), texture);
        }

        // top left corner
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x,
                        rect.y + actualCornerHeight + actualBodyHeight,
                        actualCornerWidth,
                        actualCornerHeight
                ),
                atlas.get(state + "top-left"), texture);

        // bottom left corner
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x,
                        rect.y,
                        actualCornerWidth,
                        actualCornerHeight
                ),
                atlas.get(state + "bottom-left"), texture);

        for (int i = 0; i < bodyWidth; i++) {

            // top edge
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x + (i * actualEdgeHeight) + actualCornerWidth,
                            rect.y + (actualCornerHeight * 2) - actualEdgeWidth + actualBodyHeight,
                            Application.RENDER_SCALE,
                            actualEdgeWidth
                    ),
                    atlas.get(state + "top"), texture);

            // bottom edge
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x + (i * actualEdgeHeight) + actualCornerWidth,
                            rect.y,
                            actualEdgeHeight,
                            actualEdgeWidth
                    ),
                    atlas.get(state + "bottom"), texture);
        }

        for (int i = 0; i < bodyHeight; i++) {

            // left edge
            RenderMaster.drawRegion(
                    new Rectangle(
                            rect.x,
                            rect.y + actualCornerHeight + (actualEdgeHeight * i),
                            actualEdgeWidth,
                            actualEdgeHeight
                    ),
                    atlas.get(state + "left"), texture);

            if (drawEnd) {
                // right edge
                RenderMaster.drawRegion(
                        new Rectangle(
                                rect.x + (actualCornerWidth * 2) - actualEdgeWidth + actualBodyWidth,
                                rect.y + actualCornerHeight + (actualEdgeHeight * i),
                                actualEdgeWidth,
                                actualEdgeHeight
                        ),
                        atlas.get(state + "right"), texture);
            }

        }
    }

}
