package org.qme.client.vis.wn;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.Renderable;
import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.Scrolling;
import org.qme.client.vis.wn.WindowContextManager;
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

	private static final float ZOOM_IN = 1.1F;
	private static final float ZOOM_OUT = 0.9F;

	private static final int ZOOM_MIN = 2;
	private static final int ZOOM_MAX = 10;

	public static final ArrayList<Renderable> renderables = new ArrayList<>();
	
	/**
	 * No initialization, thank you very much.
	 */
	private WindowManager() {}
	
	static {

		WindowContextManager.glfwSetup();
		
		WindowContextManager.createWindow();

		// Load textures
		new TextureManager();

	}
	
	/**
	 * The function that gets called when a key is pressed.
	 * @param window the window handle (should be redundant)
	 * @param glfwKeyCode which key was pressed
	 * @param systemScancode system-specific scancode (unused)
	 * @param keyAction pressed down, released, etc.
	 * @param modifierKeys which keys were held (shift, ctrl, alt, caps lock)
	 */
	public static void onKeyPress(long window, int glfwKeyCode, int systemScancode, int keyAction, int modifierKeys) {
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

		double focusX = ((WindowContextManager.getSize() / 2) + Scrolling.getXOffset())/oldWorldSize;
		double focusY = ((WindowContextManager.getSize() / 2) + Scrolling.getYOffset())/oldWorldSize;

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
	 * Register object
	 * @param e the object to add to the render list
	 */
	public static void addObject(Renderable e) {
		renderables.add(e);
	}

}
