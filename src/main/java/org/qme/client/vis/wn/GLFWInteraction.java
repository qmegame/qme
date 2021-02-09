package org.qme.client.vis.wn;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.qme.client.Application;
import org.qme.client.vis.Renderable;
import org.qme.client.vis.gui.MouseResponder;
import org.qme.client.vis.gui.UIComponent;
import org.qme.utils.Performance;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Contains the functionality for interfacing with GLFW code. Used to be part of
 * the massive WindowManager file.
 * @author adamhutchings
 * @since 0.3.0
 */
public class GLFWInteraction {

    public static long wn;

    public static int size;

    private static final float SCREEN_SIZE = 0.75f;

    /**
     * Not allowed!
     */
    private GLFWInteraction() {
        throw new IllegalStateException("No initializing GLFWInteraction!");
    }

    /**
     * Redraw the screen. If the window should close, send a request to the
     * application so it can close.
     */
    public static void repaint() {
        Performance.startTiming("render");

        glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        for (Renderable e : WindowManager.renderables) {
            if (e instanceof UIComponent) {
                if (!((UIComponent) e).isVisible()) {
                    continue;
                }
            }

            e.draw();
        }

        Performance.endTiming("render");
        Performance.startTiming("tick");

        MouseResponder.callMouseResponders(Application.getResponders(), null);

        glfwSwapBuffers(wn);
        glfwPollEvents();

        Performance.endTiming("tick");
    }

    /**
     * Get the preferred window size (3/4 screen height)
     * @return the height/width of the window
     */
    public static int windowSize() {

        GLFWVidMode screenSize = glfwGetVideoMode(glfwGetPrimaryMonitor());

        assert screenSize != null;
        return (int) (screenSize.height() * SCREEN_SIZE);

    }

    /**
     * Get the size of the window
     * @return the size
     */
    public static int getSize() {
        return size;
    }

    /**
     * Stuff a mouse location - GLFW wrapper
     * @param xWrap the x to stuff
     * @param yWrap the y to stuff
     */
    public static void getMouseLocation(
            DoubleBuffer xWrap, DoubleBuffer yWrap) {
        glfwGetCursorPos(wn, xWrap, yWrap);
    }

    /**
     * If the window is not about to close
     * @return whether the window is going to stay open
     */
    public static boolean shouldBeOpen() {
        return !glfwWindowShouldClose(wn);
    }

}
