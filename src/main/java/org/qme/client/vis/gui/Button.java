package org.qme.client.vis.gui;

import org.qme.client.Application;
import org.qme.client.vis.wn.WindowManager;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;

/**
 * A drawable button on-screen. Responds to clicking.
 * @author adamhutchings
 * @since 0.3
 */
public abstract class Button extends UIComponent {

    /**
     * How big buttons are.
     */
    private static final int BUTTON_WIDTH = 80, BUTTON_HEIGHT = 20;

    /**
     * The text displayed.
     */
    private final String text;

    /**
     * The source rectangle to draw.
     */
    private Rectangle rect;

    /**
     * Saved text size
     */
    private final Dimension textOffset;

    /**
     * Default constructor.
     * @param text the text to display
     * @param xLoc x location
     * @param yLoc y location
     */
    public Button(String text, int xLoc, int yLoc) {
        super();
        this.text = text;
        this.rect = new Rectangle(
                xLoc - (BUTTON_WIDTH / 2),
                yLoc - (BUTTON_HEIGHT / 2),
                BUTTON_WIDTH,
                BUTTON_HEIGHT
        );
        textOffset = new Dimension(
                (- Application.mono.getWidth(text) + BUTTON_WIDTH) / 2,
                (- Application.mono.getHeight() + BUTTON_HEIGHT) / 2
        );
    }

    /**
     * Duplicated code for buttons
     */
    public void buttonPerimeter() {
        glVertex2f(rect.x, rect.y);
        glVertex2f(rect.x + rect.width, rect.y);
        glVertex2f(rect.x + rect.width, rect.y + rect.height);
        glVertex2f(rect.x, rect.y + rect.height);
    }

    @Override
    public void draw() {
        glColor3f(0.4f, 0.4f, 0.4f);
        glBegin(GL_QUADS);
            buttonPerimeter();
        glEnd();
        glColor3f(0.0f, 0.0f, 0.0f);
        glBegin(GL_LINE_STRIP);
            buttonPerimeter();
            glVertex2f(rect.x, rect.y);
        glEnd();
        glColor3f(0.5f, 0.5f, 0.5f);
        Application.mono.drawText(text, rect.x + textOffset.width, rect.y + textOffset.height);
    }

    /**
     * Trigger user-specified event
     */
    @Override
    public void mouseClickOff() {
        this.action();
    }

    /**
     * What happens when the button is clicked
     */
    protected abstract void action();

    /**
     * Is the click in the bounds of the rectangle
     */
    @Override
    public boolean contains(int xLoc, int yLoc) {
        return this.rect.contains(xLoc, yLoc);
    }
}
