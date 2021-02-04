package org.qme.client.vis.wn;

import org.qme.client.Application;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.Renderable;
import org.qme.client.vis.tex.TextureManager;
import org.qme.io.AudioPlayer;
import org.qme.io.AudioPlayerState;
import org.qme.utils.Direction;
import org.qme.world.World;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Manages the sole window handle used with GLFW / OpenGL , and provides ways to
 * access GL code with a wrapper (because OpenGL is yucky)
 * @author adamhutchings, jakeroggenbuck
 * @since 0.1.0
 */
public final class WindowManager {

	private static final float ZOOM_IN = 1.1F;
	private static final float ZOOM_OUT = 0.9F;

	private static final float ZOOM_MIN = 0.5F;
	private static final float ZOOM_MAX = 10;

	protected static final List<Renderable> renderables = new ArrayList<>();
	
	/**
	 * No initialization, thank you very much.
	 */
	private WindowManager() {}
	
	static {

		GLFWInteraction.glfwSetup();
		
		GLFWInteraction.createWindow();

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
		if ((keyAction != GLFW_RELEASE) && (keyAction != GLFW_PRESS)) {
			return;
		}

		boolean press = keyAction == GLFW_PRESS;

		switch (glfwKeyCode) {
			// Scroll
			case GLFW_KEY_A:
				Scrolling.doScroll(Direction.LEFT, press);
				break;
			case GLFW_KEY_S:
				Scrolling.doScroll(Direction.DOWN, press);
				break;
			case GLFW_KEY_D:
				Scrolling.doScroll(Direction.RIGHT, press);
				break;
			case GLFW_KEY_W:
				Scrolling.doScroll(Direction.UP, press);
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
			case GLFW_KEY_M:
				if (keyAction != GLFW_RELEASE) break;
				// Stop audio player
				if (Application.audioPlayer.audioPlayerState == AudioPlayerState.PAUSED) {
					Application.audioPlayer.play();
				} else {
					Application.audioPlayer.pause();
				}
				break;
			case GLFW_KEY_TAB:
				Application.box.setVisible(!Application.box.isVisible());
				break;
			case GLFW_KEY_F1:
				if (keyAction == GLFW_PRESS) {
					Application.debugLabel.setVisible(!Application.debugLabel.isVisible());
				}
				break;
			case GLFW_KEY_F2:
				if (keyAction == GLFW_PRESS) {
					Application.profilerLabel.setVisible(!Application.profilerLabel.isVisible());
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

		double focusX = (((double)GLFWInteraction.getSize() / 2) + Scrolling.getXOffset())/oldWorldSize;
		double focusY = (((double)GLFWInteraction.getSize() / 2) + Scrolling.getYOffset())/oldWorldSize;

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
		return (RenderMaster.TILE_GAP + RenderMaster.TILE_SIZE) * zoom * World.WORLD_SIZE;
	}

	/**
	 * Gets the height of the world in pixels
	 * @param zoom the current zoom factor of the world
	 * @return the actual height of the world
	 */
	public static float getWorldHeight(float zoom) {
		return (World.WORLD_SIZE * RenderMaster.TILE_Y_OFFSET * zoom) + (World.WORLD_SIZE * RenderMaster.TILE_Y_OFFSET * zoom);
	}

	/**
	 * Gets the width of the world in pixels
	 * @param zoom the current zoom factor of the world
	 * @return the actual width of the world
	 */
	public static float getWorldWidth(float zoom) {
		return (World.WORLD_SIZE * RenderMaster.TILE_X_OFFSET * zoom) + (World.WORLD_SIZE * RenderMaster.TILE_X_OFFSET * zoom);
	}
	
	/**
	 * Register object
	 * @param e the object to add to the render list
	 */
	public static void addObject(Renderable e) {
		renderables.add(e);
	}

}
