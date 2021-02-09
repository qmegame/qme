package org.qme.init;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.opengl.GL;
import org.qme.client.Application;
import org.qme.client.vis.gui.MouseResponder;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.client.vis.wn.WindowManager;
import org.qme.io.Logger;
import org.qme.io.Severity;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * This class contains a method for initializing all OpenGL-related code and
 * contexts. Used to live in WindowManager / GLFWInteraction.
 * @author adamhutchings
 * @since 0.4
 */
public class GLInit {

    /**
     * No!!!
     */
    private GLInit() { throw new IllegalStateException("GLInit"); }

    /**
     * Set up some GLFW things.
     */
    public static void glfwSetup() {

        System.setProperty("java.awt.headless", "true");

        Logger.log("Headlessness activated", Severity.DEBUG);

        // Initialize GLFW.
        glfwInit();

        Logger.log("glfwInit succeeded, setting GLFW prefs", Severity.DEBUG);

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        Logger.log("GLFW prefs succeeded", Severity.DEBUG);

    }

    /**
     * Create the window - called as a static initializer.
     */
    public static void createWindow() {

        // In case something weird happens with the primary monitor, get the
        // size of the monitor one time
        GLFWInteraction.size = GLFWInteraction.windowSize();

        Logger.log("Successfully got monitor size", Severity.DEBUG);

        GLFWInteraction.wn = glfwCreateWindow(GLFWInteraction.size, GLFWInteraction.size, "QME", NULL, NULL);

        Logger.log("Created raw window handle", Severity.DEBUG);

        // Boilerplate
        glfwMakeContextCurrent(GLFWInteraction.wn);
        glfwShowWindow(GLFWInteraction.wn);
        GL.createCapabilities();

        Logger.log("Window now visible, and contexts created", Severity.DEBUG);

        glfwSetKeyCallback(GLFWInteraction.wn, new GLFWKeyCallback() {

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

        glfwSetMouseButtonCallback(GLFWInteraction.wn, new GLFWMouseButtonCallback() {

            @Override
            public void invoke(
                    long window,
                    int button,
                    int keyAction,
                    int modifierKeys)
            // Sorry for having the opening bracket on its own line here.
            {
                MouseResponder.callMouseResponders(
                        Application.getResponders(),
                        new MouseResponder.MouseEvent(button, keyAction));
            }
        });

        Logger.log("Successfully added key / mouse callbacks", Severity.DEBUG);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, GLFWInteraction.size, 0, GLFWInteraction.size, 1, -1);
        glMatrixMode(GL_MODELVIEW);

        Logger.log("Successfully initialized GL matrices", Severity.DEBUG);

    }

    /**
     * Initialize all - master OpenGL thing
     */
    public static void init() {

        Logger.log("Initializing OpenGL ...", Severity.NORMAL);

        glfwSetup();

        Logger.log("Finished initializing GLFW", Severity.DEBUG);

        createWindow();

        Logger.log("Finished creating window contexts", Severity.DEBUG);

        Logger.log("Finished initializing OpenGL", Severity.NORMAL);

    }

}
