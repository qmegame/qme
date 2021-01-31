package org.qme.client.vis.wn;

import org.qme.client.vis.RenderMaster;
import org.qme.utils.Direction;

import static org.qme.client.vis.wn.WindowManager.*;

/**
 * Contains all code for controlling scrolling - moved out of WindowManager.
 * @author adamhutchings
 * @since 0.3.0
 */
public class Scrolling {

    private static double xOffset = 0D;

    private static double yOffset = 0D;

    private static final int SCROLL_SPEED = 20;

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

    public static void setYOffset(double yOffset) {
        Scrolling.yOffset = yOffset;
    }

    public static void setXOffset(double xOffset) {
        Scrolling.xOffset = xOffset;
    }

    /**
     * Scroll based on a key code
     * @param direction the direction of motion
     */
    public static void doScroll(Direction direction) {

        if (!canMove(direction, RenderMaster.zoom)) {
            return;
        }

        switch (direction) {
            case UP:
                yOffset += SCROLL_SPEED;
                break;
            case DOWN:
                yOffset -= SCROLL_SPEED;
                break;
            case RIGHT:
                xOffset += SCROLL_SPEED;
                break;
            case LEFT:
                xOffset -= SCROLL_SPEED;
                break;
        }
    }

    /**
     * Calculates if the camera can move any more in any direction given that the limit is the center of the screen
     * @param direction the direction that is being moved to
     * @param zoom the current zoom to calculate for
     * @return if the camera can move
     */
    private static boolean canMove(Direction direction, float zoom) {
        int size = GLFWInteraction.getSize();
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
