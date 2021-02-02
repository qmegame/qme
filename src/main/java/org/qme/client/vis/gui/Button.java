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
    private static final int BUTTON_WIDTH = 400, BUTTON_HEIGHT = 200;

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
     * label handle
     */
    private final QLabel label;

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
        this.label = new QLabel(Application.mono, text, xLoc, yLoc);
        WindowManager.addObject(this);
    }

    @Override
    public void draw() {
        glColor3f(0.5f, 0.5f, 0.5f);
        glVertex2f(rect.x, rect.y);
        glVertex2f(rect.x + rect.width, rect.y);
        glVertex2f(rect.x + rect.width, rect.y + rect.height);
        glVertex2f(rect.x, rect.y + rect.height);
        glEnd();
        Application.mono.drawText(text, rect.x, rect.y);
    }
}
