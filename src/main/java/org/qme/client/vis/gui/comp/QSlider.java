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

    /**
     * Texture atlas that represents all sliders
     */
    private final HashMap<String, Rectangle> atlas = TextureManager.loadAtlas("/textures/misc/button.json");

    private String text;
    private QFont font;
    private Dimension textOffset;
    private Rectangle rect;

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
     * Called when the button is clicked
     */
    protected abstract void action(float fill);

    @Override
    public boolean contains(int x, int y) {

        // TODO: This is ugly asf needs an api method instead of putting it into contains
        if (rect.contains(x, GLFWInteraction.windowSize() - y)) {

            if (isClicked() && !isLocked() && isVisible()) {
                float fill = ((float)(x - (rect.x)) / rect.width) * 100;
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

        int bodyWidth = rect.width - rect.width % Application.RENDER_SCALE - ((atlas.get("disabled-" + "bottom-left").width * 2)) * Application.RENDER_SCALE;
        int bodyHeight = rect.height - rect.height % Application.RENDER_SCALE - ((atlas.get("disabled-" + "bottom-left").height * 2)) * Application.RENDER_SCALE;

        RenderMaster.drawBounds(atlas, new Rectangle(rect.x, rect.y, bodyWidth, bodyHeight), "disabled-");

        bodyWidth = Math.max(15, fill) - Math.max(15, fill) % Application.RENDER_SCALE - (atlas.get((isClicked() ? "pressed" : "unpressed") + "-" + "bottom-left").width * 2) * Application.RENDER_SCALE;
        RenderMaster.drawBounds(atlas, new Rectangle(rect.x, rect.y, bodyWidth, bodyHeight), (isClicked() ? "pressed" : "unpressed") + "-");

        font.drawText(text, rect.x + textOffset.width, rect.y + textOffset.height);

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }

}
