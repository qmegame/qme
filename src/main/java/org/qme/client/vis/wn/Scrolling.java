package org.qme.client.vis.wn;

import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.gui.GUIManager;
import org.qme.utils.Direction;

import java.util.HashMap;

import static org.qme.client.vis.wn.WindowManager.*;

/**
 * Contains all code for controlling scrolling - moved out of WindowManager.
 * @author adamhutchings
 * @since 0.3.0
 */
public class Scrolling {

    private static double xOffset = 0D;
    private static double yOffset = 0D;

    private static final int SCROLL_SPEED = 4;

    /**
     * Whether each button is pressed
     */
    private static final HashMap<Direction, Boolean> pressed
            = new HashMap<>();

    static {
        // Initialize everything to false
        for (int i = 0; i < Direction.values().length; i++) {
            pressed.put(Direction.values()[i], false);
        }
    }

    /**
     * Not allowed!
     */
    private Scrolling() {
        throw new IllegalStateException("No initializing scrolling!");
    }

    /**
     * Get the window x offset
     * @return the window x offset
     */
    public static double getXOffset() {
        return xOffset;
    }

    /**
     * Get the window y offset
     * @return the window y offset
     */
    public static double getYOffset() {
        return yOffset;
    }

    /**
     * Sets the window y offset
     * @param yOffset the new y offset
     */
    public static void setYOffset(double yOffset) {
        Scrolling.yOffset = yOffset;
    }

    /**
     * Sets the window y offset
     * @param xOffset the new x offset
     */
    public static void setXOffset(double xOffset) {
        Scrolling.xOffset = xOffset;
    }

    /**
     * Scroll based on a key code
     * @param direction the direction of motion
     * @param press whether it's pressed (true) or released (false)
     */
    public static void doScroll(Direction direction, boolean press) {

        if (press && (GUIManager.optionsUI.isVisible() || GUIManager.pauseUI.isVisible())) {
            return;
        }

        if (press && !canMove(direction, RenderMaster.zoom)) {
            return;
        }

        pressed.put(direction, press);

    }

    /**
     * Actually move the world
     */
    public static void moveWorld() {
        if (GUIManager.optionsUI.isVisible() || GUIManager.pauseUI.isVisible()) {
            return;
        }

        if (pressed.get(Direction.UP) && canMove(Direction.UP, RenderMaster.zoom)) {
            yOffset += SCROLL_SPEED;
        }
        if (pressed.get(Direction.DOWN) && canMove(Direction.DOWN, RenderMaster.zoom)) {
            yOffset -= SCROLL_SPEED;
        }
        if (pressed.get(Direction.LEFT) && canMove(Direction.LEFT, RenderMaster.zoom)) {
            xOffset -= SCROLL_SPEED;
        }
        if (pressed.get(Direction.RIGHT) && canMove(Direction.RIGHT, RenderMaster.zoom)) {
            xOffset += SCROLL_SPEED;
        }
    }

    /**
     * Calculates if the camera can move any more in any direction given that the limit is the center of the screen
     * @param direction the direction that is being moved to
     * @param zoom the current zoom to calculate for
     * @return if the camera can move
     */
    private static boolean canMove(Direction direction, float zoom) {
        double size = GLFWInteraction.getSize();
        switch (direction) {
            case UP:
                return ((size / 2) + Scrolling.getYOffset())/getWorldHeight(zoom) < 1;
            case DOWN:
                return ((size / 2) + Scrolling.getYOffset())/getWorldHeight(zoom) > 0;
            case LEFT:
                return ((size / 2) + Scrolling.getXOffset())/getWorldWidth(zoom) > -0.5;
            case RIGHT:
                return ((size / 2) + Scrolling.getXOffset())/getWorldWidth(zoom) < 0.5;
            default:
                return true;
        }
    }

}
