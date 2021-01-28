package org.qme.client.vis;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.qme.client.vis.tex.TextureManager;
import org.qme.utils.Direction;
import org.qme.world.World;

import java.awt.*;
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
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
		
		createWindow();

		// Load textures
		new TextureManager();
		
	}
	
	/**
	 * The window handle.
	 */
	private static long wn;
	
	/**
	 * The size of the window.
	 */
	public static int size;
	
	/**
	 * The x-offset from normal
	 */
	private static double xOffset = 0D;
	
	/**
	 * The y-offset from normal
	 */
	private static double yOffset = 0D;
	
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
	public static int windowSize() {
		
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
	private static void onKeyPress(long window, int glfwKeyCode, int systemScancode, int keyAction, int modifierKeys) {
		if (keyAction == GLFW_RELEASE) {
			return;
		}

		switch (glfwKeyCode) {
			// Scroll
			case GLFW_KEY_A:
				doScroll(Direction.LEFT);
				break;
			case GLFW_KEY_S:
				doScroll(Direction.DOWN);
				break;
			case GLFW_KEY_D:
				doScroll(Direction.RIGHT);
				break;
			case GLFW_KEY_W:
				doScroll(Direction.UP);
				break;
			case GLFW_KEY_I:
				// Zoom in until limit is reached
				if (RenderMaster.zoom <= RenderMaster.HIGHEST) {
					applyZoom(RenderMaster.ZOOM_IN);
				}
				break;
			case GLFW_KEY_O:
				// Zoom out until limit is reached
				if (RenderMaster.zoom >= RenderMaster.LOWEST) {
					applyZoom(RenderMaster.ZOOM_OUT);
				}
				break;
		}
	}

	/**
	 * Zooms in or out and applies an offset to zoom evenly
	 */
	private static void applyZoom(float zoomFactor) {
		// Works by calculating how much offset must be applied to counteract the objects increasing in size
		double newWorldSize = getWorldSize(RenderMaster.zoom * zoomFactor);
		double oldWorldSize = getWorldSize(RenderMaster.zoom);

		double focusX = ((size/2) + xOffset)/oldWorldSize;
		double focusY = ((size/2) + yOffset)/oldWorldSize;

		double worldSizeChange = oldWorldSize - newWorldSize;

		xOffset -= (worldSizeChange) * focusX;
		yOffset -= (worldSizeChange) * focusY;

		RenderMaster.zoom *= zoomFactor;
	}

	/**
	 * Gets the size of the world in pixels
	 * @param zoom the current zoom factor of the world
	 * @return the size of the world in pixels
	 */
	private static float getWorldSize(float zoom) {
		return RenderMaster.TILE_SPACING * zoom * World.WORLD_SIZE;
	}

	/**
	 * Calculates if the camera can move any more in any direction given that the limit is the center of the screen
	 * @param direction the direction that is being moved to
	 * @param zoom the current zoom to calculate for
	 * @return if the camera can move
	 */
	private static boolean canMove(Direction direction, float zoom) {
		switch (direction) {
			case UP:
				return ((size/2) + yOffset)/getWorldSize(zoom) < 1;
			case DOWN:
				return ((size/2) + yOffset)/getWorldSize(zoom) > 0;
			case LEFT:
				return ((size/2) + xOffset)/getWorldSize(zoom) > 0;
			case RIGHT:
				return ((size/2) + xOffset)/getWorldSize(zoom) < 1;
			default:
				return true;
		}
	}
	
	/**
	 * Scroll based on a key code
	 * @param direction the direction of motion
	 */
	private static void doScroll(Direction direction) {

		if (!canMove(direction, RenderMaster.zoom)) {
			return;
		}

		switch (direction) {
			case UP:
				yOffset += scrollSpeed;
				break;
			case DOWN:
				yOffset -= scrollSpeed;
				break;
			case RIGHT:
				xOffset += scrollSpeed;
				break;
			case LEFT:
				xOffset -= scrollSpeed;
				break;
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
	 * @deprecated Provides imprecise rounded offsets use getWindowY() and getWindowX() instead
	 */
	@Deprecated
	public static Dimension getOffsets() {
		return new Dimension((int) Math.round(xOffset), (int) Math.round(yOffset));
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
	public static double getWindowX() {
		return xOffset;
	}
	
	/**
	 * Get the window y offset
	 * @return the window y offset
	 */
	public static double getWindowY() {
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
