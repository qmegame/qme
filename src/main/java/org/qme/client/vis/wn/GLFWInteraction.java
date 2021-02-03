package org.qme.client.vis.wn;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.qme.client.vis.Renderable;
import org.qme.client.vis.gui.UIComponent;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Contains the functionality for interfacing with GLFW code. Used to be part of
 * the massive WindowManager file.
 * @author adamhutchings
 * @since 0.3
 */
public class GLFWInteraction {

    private static long wn;

    private static int size;

    private static final float SCREEN_SIZE = 0.75f;

    /**
     * Not allowed!
     */
    private GLFWInteraction() {
        throw new IllegalStateException("No initializing GLFWInteraction!");
    }

    /**
     * Set up some GLFW things.
     */
    public static void glfwSetup() {
        System.setProperty("java.awt.headless", "true");

        // Initialize GLFW.
        glfwInit();

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
    }

    /**
     * Create the window - called as a static initializer.
     */
    public static void createWindow() {

        // In case something weird happens with the primary monitor, get the
        // size of the monitor one time
        size = windowSize();

        wn = glfwCreateWindow(size, size, "QME", NULL, NULL);

        // Boilerplate
        glfwMakeContextCurrent(wn);
        glfwShowWindow(wn);
        GL.createCapabilities();

        glfwSetKeyCallback(wn, new GLFWKeyCallback() {

            @Override
            public void invoke(
                    long window,
                    int glfwKeyCode,
                    int systemScancode,
                    int keyAction,
                    int modifierKeys)
            // Sorry for having the opening bracket on its own line here.
            {
                // Simple wrapper.
                WindowManager.onKeyPress(
                        window, glfwKeyCode, systemScancode, keyAction, modifierKeys
                );
            }

        });

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, size, 0, size, 1, -1);
        glMatrixMode(GL_MODELVIEW);

    }

    /**
     * Redraw the screen. If the window should close, send a request to the
     * application so it can close.
     */
    public static void repaint() {

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

        glfwSwapBuffers(wn);
        glfwPollEvents();

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
