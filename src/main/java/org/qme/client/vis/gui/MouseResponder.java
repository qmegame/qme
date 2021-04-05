package org.qme.client.vis.gui;

import org.lwjgl.BufferUtils;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.client.vis.wn.Scrolling;
import org.qme.world.Tile;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Represents an interface for UI component interaction with mouse events. Most
 * classes should inherit from UIComponent, which represents a renderable AND
 * mouse-responding object.
 * @author adamhutchings
 * @since 0.1.0
 */
public interface MouseResponder {

	/**
	 * Whether or not this item is clicked.
	 * @return clicked status
	 */
	boolean isClicked();
	
	/**
	 * Set the clicked status.
	 * @param b whether the item is clicked
	 */
	void setClicked(boolean b);
	
	/**
	 * Whether or not this is hovered over.
	 * @return hovered status
	 */
	boolean isHovered();
	
	/**
	 * Set the hovered status.
	 * @param b whether the item is hovered
	 */
	void setHovered(boolean b);
	
	/**
	 * Whether this object contains a given position.
	 * @param xLoc the x-position of the location
	 * @param yLoc the y-position of the location
	 * @return whether this object contains that position
	 */
	boolean contains(int xLoc, int yLoc);
	
	/**
	 * What happens when a mouse hovers over this.
	 */
	void mouseHoverOn();
	
	/**
	 * What happens when a mouse hovers off this.
	 */
	void mouseHoverOff();
	
	/**
	 * What happens when a mouse clicks on this.
	 */
	void mouseClickOn();
	
	/**
	 * What happens when a mouse clicks off this.
	 */
	void mouseClickOff();
	
	/**
	 * Gets called, and dispatches all appropriate events
	 * @param objects the list of object to act on
	 * @param event the constructed event - null if this is just hovers
	 */
	static void callMouseResponders(
			ArrayList<MouseResponder> objects,
			MouseEvent event) {
		
		// Mouse location
		GLFWInteraction.getMouseLocation(MouseData.mouseX, MouseData.mouseY);
		
		int x = (int) MouseData.mouseX.get(0);
		int y = (int) MouseData.mouseY.get(0);
		
		for (MouseResponder responder : objects) {

			if (responder instanceof Tile) {
				Tile tile = (Tile) responder;
				int tileX = (int) ((tile.x * RenderMaster.TILE_X_OFFSET * RenderMaster.zoom) - (tile.y * RenderMaster.TILE_X_OFFSET * RenderMaster.zoom) - Scrolling.getXOffset());
				int tileY = (int) ((tile.y * RenderMaster.TILE_Y_OFFSET * RenderMaster.zoom) + (tile.x * RenderMaster.TILE_Y_OFFSET * RenderMaster.zoom) - Scrolling.getYOffset());
				if (!RenderMaster.isInFrame(tileX, tileY)) {
					continue;
				}
			}
			
			if (event == null) {
				
				// Only hovers
			
				// If the mouse is in now but wasn't last time, hover on
				if (responder.contains(x, y) && !responder.isHovered()) {
					responder.mouseHoverOn();
					responder.setHovered(true);
				}
				
				// If it isn't it now but was last time, hover off!
				if (!responder.contains(x, y) && responder.isHovered()) {
					responder.mouseHoverOff();
					responder.setHovered(false);
				}
				
			} else {
				
				if (responder.contains(x, y)
						&& event.action == GLFW_PRESS
						&& !responder.isClicked()) {
					responder.mouseClickOn();
					responder.setClicked(true);
				}
				
				// Release happens if a component was clicked, no matter if the
				// mouse is still over it.
				if (event.action == GLFW_RELEASE && responder.isClicked()) {
					responder.mouseClickOff();
					responder.setClicked(false);
				}
				
			}
			
		}
		
	}
	
	/**
	 * Simple wrapper for mouse data.
	 * @author adamhutchings
	 * @since 0.1.0
	 */
	final class MouseEvent {
		
		/**
		 * Which button was pressed.
		 */
		public final int button;
		
		/**
		 * Which action it was - CLICK or RELEASE
		 */
		public final int action;
		
		/**
		 * Initialize the variables
		 * @param button which button
		 * @param action which action
		 */
		public MouseEvent(int button, int action) {
			this.button = button;
			this.action = action;
		}
		
	}
	
	/**
	 * Stores internal mouse data.
	 * @author adamhutchings
	 * @since 0.1.0
	 */
	final class MouseData {
		
		/**
		 * Stuffed with the mouse's x location
		 */
		private static final DoubleBuffer mouseX = BufferUtils.createDoubleBuffer(1);
		
		/**
		 * Stuffed with the mouse's y location
		 */
		private static final DoubleBuffer mouseY = BufferUtils.createDoubleBuffer(1);
		
	}

}
