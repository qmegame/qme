package org.qme.client.vis.gui;

import org.qme.client.Application;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.tex.TextureManager;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.util.HashMap;

/**
 * A drawable button on-screen. Responds to clicking.
 * @author adamhutchings
 * @since 0.3.0
 */
public abstract class QButton extends UIComponent {

    private static final HashMap<String, Rectangle> atlas = TextureManager.loadAtlas("/textures/misc/button.json");

    private int actualCornerHeight;
    private int actualCornerWidth;
    private int bodyWidth;
    private int bodyHeight;
    private int actualBodyWidth;
    private int actualBodyHeight;
    private int actualEdgeWidth;
    private int actualEdgeHeight;

    private String text;
    private QFont font;
    private Dimension textOffset;
    private Rectangle rect;
    private Dimension texture;
    private boolean clickable = true;

    /**
     * Creates a new button
     * @param font the font to use
     * @param text the text to display
     * @param x x location
     * @param y y location
     * @param width the width of the button
     * @param height the height of the button
     */
    public QButton(QFont font, String text, int x, int y, int width, int height) {
        this(font, text, new Rectangle(x - (width / 2), y - (height / 2), width, height));
    }

    /**
     * Creates a new button
     * @param font the font to use
     * @param text the text to display
     * @param rect the button size and location
     */
    public QButton(QFont font, String text, Rectangle rect) {
        this.font = font;
        this.text = text;
        this.rect = rect;
        textOffset = new Dimension(
                (rect.width - font.getWidth(text)) / 2,
                (rect.height - font.getHeight()) / 2
        );
        this.calculateDimensions();
    }

    /**
     * Calculates values used for rendering.
     * If the dimensions of the QButton has changed they will need to be recalculated.
     */
    public void calculateDimensions() {
        String state = getState().name().toLowerCase() + "-";
        this.actualCornerHeight = atlas.get(state + "bottom-left").height * Application.RENDER_SCALE;
        this.actualCornerWidth = atlas.get(state + "bottom-left").width * Application.RENDER_SCALE;
        this.bodyWidth = (rect.width / Application.RENDER_SCALE) - atlas.get(state + "bottom-left").width * 2;
        this.bodyHeight = (rect.height / Application.RENDER_SCALE) - atlas.get(state + "bottom-left").height * 2;
        this.actualBodyWidth = bodyWidth * Application.RENDER_SCALE;
        this.actualBodyHeight = bodyHeight * Application.RENDER_SCALE;
        this.actualEdgeHeight = atlas.get(state + "left").height * Application.RENDER_SCALE;
        this.actualEdgeWidth = atlas.get(state + "left").width * Application.RENDER_SCALE;
        this.texture = new Dimension(atlas.get("total").width, atlas.get("total").height);
    }

    /**
     * Gets if the button is clickable
     * If the button is not clickable it will still call its action
     * @return if the button is clickable
     */
    public boolean isClickable() {
        return clickable;
    }

    /**
     * Sets if the button is clickable
     * If the button is not clickable it will still call its action
     * @param b if the button should be clickable
     */
    public void setClickable(boolean b) {
        this.clickable = b;
    }

    /**
     * Called when the button is clicked
     */
    protected abstract void action();

    @Override
    public void mouseClickOff() {
        this.action();
    }

    @Override
    public boolean contains(int x, int y) {
        return rect.contains(x, y);
    }

    @Override
    public void draw() {
        // If anyone has a better way to do this please feel free.

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBindTexture(GL_TEXTURE_2D, TextureManager.getTexture("misc/button.png"));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        String state = getState().name().toLowerCase() + "-";

        // base for the background of the box
        RenderMaster.drawRegion(
                new Rectangle(
                        rect.x + actualEdgeWidth,
                        rect.y + actualEdgeWidth,
                        actualBodyWidth + actualCornerWidth,
                        actualBodyHeight + actualCornerHeight
                ),
                atlas.get(state + "fill"), texture);

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

        font.drawText(text, rect.x + textOffset.width, rect.y + textOffset.height);

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }

    /**
     * Gets the current state of this button used for rendering
     * @return the state of the button
     */
    public ButtonState getState() {
        if (!isClickable()) {
            return ButtonState.DISABLED;
        }
        if (isClicked()) {
            return ButtonState.PRESSED;
        }
        return ButtonState.UNPRESSED;
    }

    /**
     * Represents the drawable states of a button
     */
    public enum ButtonState {
        PRESSED,
        UNPRESSED,
        DISABLED
    }
}
