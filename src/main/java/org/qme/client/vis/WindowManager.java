package org.qme.client.vis;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.Scrolling;
import org.qme.utils.Direction;
import org.qme.world.World;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Manages the sole window handle used with GLFW / OpenGL , and provides ways to
 * access GL code with a wrapper (because OpenGL is yucky)
 * @author adamhutchings, jakeroggenbuck
 * @since 0.1.0
 */
public final class WindowManager {

	private static long wn;
	private static int size;

	private static final float SCREEN_SIZE = 0.75f;

	private static final float ZOOM_IN = 1.1F;
	private static final float ZOOM_OUT = 0.9F;

	private static final int ZOOM_MIN = 2;
	private static final int ZOOM_MAX = 10;

	private static final ArrayList<Renderable> renderables = new ArrayList<>();
	
	/**
	 * No initialization, thank you very much.
	 */
	private WindowManager() {}
	
	static {

		System.setProperty("java.awt.headless", "true");
		
		// Initialize GLFW.
		glfwInit();
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		createWindow();

		// Load textures
		new TextureManager();

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
	private static void onKeyPress(long window, int glfwKeyCode, int systemScancode, int keyAction, int modifierKeys) {
		if (keyAction == GLFW_RELEASE) {
			return;
		}

		switch (glfwKeyCode) {
			// Scroll
			case GLFW_KEY_A:
				Scrolling.doScroll(Direction.LEFT);
				break;
			case GLFW_KEY_S:
				Scrolling.doScroll(Direction.DOWN);
				break;
			case GLFW_KEY_D:
				Scrolling.doScroll(Direction.RIGHT);
				break;
			case GLFW_KEY_W:
				Scrolling.doScroll(Direction.UP);
				break;
			case GLFW_KEY_I:
				// Zoom in until limit is reached
				if (RenderMaster.zoom <= ZOOM_MAX) {
					applyZoom(ZOOM_IN);
				}
				break;
			case GLFW_KEY_O:
				// Zoom out until limit is reached
				if (RenderMaster.zoom >= ZOOM_MIN) {
					applyZoom(ZOOM_OUT);
				}
				break;
			default:
				break;
		}
	}

	/**
	 * Zooms in or out and applies an offset to zoom evenly
	 * @param zoomFactor the scale factor to be applied to the zoom
	 */
	private static void applyZoom(float zoomFactor) {
		// Works by calculating how much offset must be applied to counteract the objects increasing in size
		double newWorldSize = getWorldSize(RenderMaster.zoom * zoomFactor);
		double oldWorldSize = getWorldSize(RenderMaster.zoom);

		double focusX = ((size/2) + Scrolling.getXOffset())/oldWorldSize;
		double focusY = ((size/2) + Scrolling.getYOffset())/oldWorldSize;

		double worldSizeChange = oldWorldSize - newWorldSize;

		Scrolling.setXOffset(Scrolling.getXOffset() - (worldSizeChange) * focusX);
		Scrolling.setYOffset(Scrolling.getYOffset() - (worldSizeChange) * focusY);

		RenderMaster.zoom *= zoomFactor;
	}

	/**
	 * Gets the size of the world in pixels
	 * @param zoom the current zoom factor of the world
	 * @return the size of the world in pixels
	 */
	public static float getWorldSize(float zoom) {
		return (RenderMaster.TILE_SPACING + RenderMaster.TILE_SIZE) * zoom * World.WORLD_SIZE;
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
	 * Register object
	 * @param e the object to add to the render list
	 */
	public static void addObject(Renderable e) {
		renderables.add(e);
	}

}
