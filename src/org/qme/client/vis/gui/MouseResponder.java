package org.qme.client.vis.gui;

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

}
