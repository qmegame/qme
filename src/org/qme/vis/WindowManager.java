package org.qme.vis;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.qme.client.Request;

/**
 * Manages the sole window handle used with GLFW / OpenGL , and provides ways to
 * access GL code with a wrapper (because OpenGL is yucky)
 * @author adamhutchings
 * @since preA
 */
public final class WindowManager {
	
	/**
	 * No initialization, thank you very much.
	 */
	private WindowManager() {}
	
	static {
		
		// Initialize GLFW.
		glfwInit();
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		
		createWindow();
		
	}
	
	/**
	 * The window handle.
	 */
	private static long wn;
	
	/**
	 * The ratio between window height / monitor height
	 */
	private static final float SCREEN_SIZE = 0.75f;
	
	/**
	 * Get the preferred window size (3/4 screen height)
	 * @return the height/width of the window
	 */
	private static int windowSize() {
		
		GLFWVidMode screenSize = glfwGetVideoMode(glfwGetPrimaryMonitor());

		return (int) (screenSize.height() * SCREEN_SIZE);
		
	}
	
	/**
	 * Create the window - called as a static initializer.
	 */
	private static void createWindow() {
		
		// In case something weird happens with the primary monitor, get the
		// size of the monitor one time
		int size = windowSize();
		
		wn = glfwCreateWindow(size, size, "QME", NULL, NULL);
		
		// Boilerplate
		glfwMakeContextCurrent(wn);
		glfwShowWindow(wn);
		GL.createCapabilities();
		
	}
	
	/**
	 * If the window is not about to close
	 * @return whether the window is going to stay open
	 */
	private static boolean open() {
		return !glfwWindowShouldClose(wn);
	}
	
	/**
	 * Redraw the screen. If the window should close, send a request to the
	 * application so it can close.
	 */
	public static void repaint() {
		
		if (!open()) {
			Request.addRequest(new Request(Request.RequestType.EXIT));
			return;
		}
		
		glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
		glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		glfwSwapBuffers(wn);
		glfwPollEvents();
		
	}

}
