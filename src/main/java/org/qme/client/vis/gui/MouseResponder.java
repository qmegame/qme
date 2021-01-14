package org.qme.client.vis.gui;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.qme.client.vis.WindowManager;

/**
 * Represents an interface for UI component interaction with mouse events. Most
 * classes should inherit from UIComponent, which represents a renderable AND
 * mouse-responding object.
 * @author adamhutchings
 * @since preA
 */
public interface MouseResponder {

	/**
	 * Whether or not this item is clicked.
	 * @return clicked status
	 */
	public boolean clicked();
	
	/**
	 * Set the clicked status.
	 * @param b whether the item is clicked
	 */
	public void setClicked(boolean b);
	
	/**
	 * Whether or not this is hovered over.
	 * @return hovered status
	 */
	public boolean hovered();
	
	/**
	 * Set the hovered status.
	 * @param b whether the item is hovered
	 */
	public void setHovered(boolean b);
	
	/**
	 * Whether this object contains a given position.
	 * @param xLoc the x-position of the location
	 * @param yLoc the y-position of the location
	 * @return whether this object contains that position
	 */
	public boolean contains(int xLoc, int yLoc);
	
	/**
	 * What happens when a mouse hovers over this.
	 */
	public void mouseHoverOn();
	
	/**
	 * What happens when a mouse hovers off this.
	 */
	public void mouseHoverOff();
	
	/**
	 * What happens when a mouse clicks on this.
	 */
	public void mouseClickOn();
	
	/**
	 * What happens when a mouse clicks off this.
	 */
	public void mouseClickOff();
	
	/**
	 * Gets called, and dispatches all appropriate events
	 * @param objects the list of object to act on
	 * @param event the constructed event - null if this is just hovers
	 */
	public static void callMouseResponders(
			ArrayList<MouseResponder> objects,
			MouseEvent event) {
		
		// Mouse location
		WindowManager.getMouseLocation(MouseData.mouseX, MouseData.mouseY);
		
		int x = (int) MouseData.mouseX.array()[0];
		int y = (int) MouseData.mouseY.array()[0];
		
		for (MouseResponder responder : objects) {
			
			if (event == null) {
				
				// Only hovers
			
				// If the mouse is in now but wasn't last time, hover on
				if (responder.contains(x, y) && !responder.hovered()) {
					responder.mouseHoverOn();
					responder.setHovered(true);
				}
				
				// If it isn't it now but was last time, hover off!
				if (!responder.contains(x, y) && responder.hovered()) {
					responder.mouseHoverOff();
					responder.setHovered(false);
				}
				
			} else {
				
				if (responder.contains(x, y)
						&& event.action == GLFW_PRESS
						&& !responder.clicked()) {
					responder.mouseClickOn();
					responder.setClicked(true);
				}
				
				// Release happens if a component was clicked, no matter if the
				// mouse is still over it.
				if (event.action == GLFW_RELEASE && responder.clicked()) {
					responder.mouseClickOff();
					responder.setClicked(false);
				}
				
			}
			
		}
		
	}
	
	/**
	 * Simple wrapper for mouse data.
	 * @author adamhutchings
	 * @since preA
	 */
	public static final class MouseEvent {
		
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
	 * @since preA
	 */
	public static final class MouseData {
		
		/**
		 * Stuffed with the mouse's x location
		 */
		private static DoubleBuffer mouseX = BufferUtils.createDoubleBuffer(1);
		
		/**
		 * Stuffed with the mouse's y location
		 */
		private static DoubleBuffer mouseY = BufferUtils.createDoubleBuffer(1);
		
	}

}
