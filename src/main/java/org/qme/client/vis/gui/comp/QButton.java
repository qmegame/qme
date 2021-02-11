package org.qme.client.vis.gui.comp;

import org.qme.client.Application;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.GLFWInteraction;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;
import java.util.HashMap;

/**
 * A drawable button on-screen. Responds to clicking.
 * @author adamhutchings
 * @since 0.3.0
 */
public abstract class QButton extends UIComponent {

    /**
     * Texture atlas that represents all buttons
     */
    private final HashMap<String, Rectangle> atlas = TextureManager.loadAtlas("/textures/misc/button.json");

    private QFont font;
    private Dimension textOffset;
    private String text;
    private Rectangle rect;
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
                (rect.height- font.getHeight()) / 2
        );
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
     * Changes the button label
     * @param string the new button label
     */
    public void setText(String string) {
        this.text = string;
        textOffset = new Dimension(
                (rect.width - font.getWidth(text)) / 2,
                (rect.height - font.getHeight()) / 2
        );
    }

    /**
     * Called when the button is clicked
     */
    protected abstract void action();

    @Override
    public void mouseClickOff() {
        if (this.isVisible()) {
            this.action();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return rect.contains(x, GLFWInteraction.windowSize() - y);
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

        String state = getDrawState().name().toLowerCase() + "-";

        RenderMaster.drawBounds(atlas, new Rectangle(rect.x, rect.y, rect.width - rect.width % Application.RENDER_SCALE - ((atlas.get(state + "bottom-left").width * 2)) * Application.RENDER_SCALE, rect.height - rect.height % Application.RENDER_SCALE - ((atlas.get(state + "bottom-left").height * 2)) * Application.RENDER_SCALE), state);

        font.drawText(text, rect.x + textOffset.width, rect.y + textOffset.height);

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }

    /**
     * Gets the current state of this button used for rendering
     * @return the state of the button
     */
    public ButtonDrawState getDrawState() {
        if (!isClickable()) {
            return ButtonDrawState.DISABLED;
        }
        if (isClicked()) {
            return ButtonDrawState.PRESSED;
        }
        return ButtonDrawState.UNPRESSED;
    }

    /**
     * Represents the drawable states of a button
     */
    public enum ButtonDrawState {
        PRESSED,
        UNPRESSED,
        DISABLED
    }
}
