package org.qme.client.vis.gui;

import org.qme.client.Application;
import org.qme.client.vis.wn.WindowManager;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Rectangle;

/**
 * A drawable button on-screen. Responds to clicking.
 * @author adamhutchings
 * @since 0.3
 */
public class Button extends UIComponent {

    /**
     * How big buttons are.
     */
    private static final int BUTTON_WIDTH = 80, BUTTON_HEIGHT = 20;

    /**
     * The text displayed.
     */
    private final String text;

    /**
     * Where the button is located
     */
    private final int xLoc, yLoc;

    /**
     * The source rectangle to draw.
     */
    private Rectangle rect;

    /**
     * Default constructor.
     * @param text the text to display
     * @param xLoc x location
     * @param yLoc y location
     */
    public Button(String text, int xLoc, int yLoc) {
        this.text = text;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.rect = new Rectangle(
                xLoc - (BUTTON_WIDTH / 2),
                yLoc - (BUTTON_HEIGHT / 2),
                BUTTON_WIDTH,
                BUTTON_HEIGHT
        );
        WindowManager.addObject(this);
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
        glEnd();
        glColor3f(0.5f, 0.5f, 0.5f);
        Application.mono.drawText(text, rect.x, rect.y);
    }
}
