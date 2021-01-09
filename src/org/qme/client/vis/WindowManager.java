package org.qme.client.vis;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.*;

import java.awt.Dimension;
import java.nio.DoubleBuffer;
import java.util.ArrayList;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

/**
 * Manages the sole window handle used with GLFW / OpenGL , and provides ways to
 * access GL code with a wrapper (because OpenGL is yucky)
 * @author adamhutchings
 * @since preA
 */
public final class WindowManager {
	
	/**
	 * Objects to render
	 */
	private static final ArrayList<Renderable> renderables
		= new ArrayList<>();
	
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
	 * The size of the window.
	 */
	private static int size;
	
	/**
	 * The x-offset from normal
	 */
	private static int xOffset = 0;
	
	/**
	 * The y-offset from normal
	 */
	private static int yOffset = 0;
	
	/**
	 * How fast this works
	 */
	private static final int scrollSpeed = 20;
	
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
				onKeyPress(
					window, glfwKeyCode, systemScancode, keyAction, modifierKeys
				);
			}
			
		});
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glLoadIdentity();
	    GL11.glOrtho(0, size, 0, size, 1, -1);
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}
	
	/**
	 * If the window is not about to close
	 * @return whether the window is going to stay open
	 */
	public static boolean shouldBeOpen() {
		return !glfwWindowShouldClose(wn);
	}
	
	/**
	 * Redraw the screen. If the window should close, send a request to the
	 * application so it can close.
	 */
	public static void repaint() {
		
		glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
		glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		for (Renderable e : renderables) {
			e.draw();
		}
		
		glfwSwapBuffers(wn);
		glfwPollEvents();
		
	}
	
	/**
	 * The function that gets called when a key is pressed.
	 * @param window the window handle (should be redundant)
	 * @param glfwKeyCode which key was pressed
	 * @param systemScancode system-specific scancode (unused)
	 * @param keyAction pressed down, released, etc.
	 * @param modifierKeys which keys were held (shift, ctrl, alt, caps lock)
	 */
	private static void onKeyPress(
			long window,
			int glfwKeyCode,
			int systemScancode,
			int keyAction,
			int modifierKeys)
	{
		switch (glfwKeyCode) {
		
		// Scroll
		case GLFW_KEY_A:
		case GLFW_KEY_S:
		case GLFW_KEY_D:
		case GLFW_KEY_W:
			doScroll(glfwKeyCode);
			break;
		
		}
	}
	
	/**
	 * Scroll based on a key code
	 * @param keycode the code (WASD)
	 */
	private static void doScroll(int keycode) {
		
		switch (keycode) {
		
		case GLFW_KEY_A:
			xOffset -= scrollSpeed;
			break;
		case GLFW_KEY_S:
			yOffset -= scrollSpeed;
			break;
		case GLFW_KEY_D:
			xOffset += scrollSpeed;
			break;
		case GLFW_KEY_W:
			yOffset += scrollSpeed;
			break;
		default:;
		
		}
		
	}
	
	/**
	 * Get the size of the window
	 * @return the size
	 */
	public static int size() {
		return size;
	}
	
	/**
	 * Get the offset, for use in calculating rendering routines
	 * @return the offset in a Java AWT dimension
	 */
	public static Dimension getOffsets() {
		return new Dimension(xOffset, yOffset);
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
	 * Get the window x offset
	 * @return the window x offset
	 */
	public static int getWindowX() {
		return xOffset;
	}
	
	/**
	 * Get the window y offset
	 * @return the window y offset
	 */
	public static int getWindowY() {
		return yOffset;
	}
	
	/**
	 * Register object
	 * @param e the object to add to the render list
	 */
	public static void addObject(Renderable e) {
		renderables.add(e);
	}

}
