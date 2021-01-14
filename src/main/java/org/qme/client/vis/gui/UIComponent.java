package org.qme.client.vis.gui;

import org.qme.client.vis.Renderable;

/**
 * Simple class to extend both and offer default functionality.
 * @author adamhutchings
 * @since preA
 */
public abstract class UIComponent implements MouseResponder, Renderable {
	
	/**
	 * For logging purposes (maybe more later)
	 * @return the name of the component
	 */
	public String name() {
		return "component.unnamed";
	}

	/**
	 * Do nothing
	 */
	@Override
	public void draw() {
		
	}
	
	/**
	 * Whether this is clicked.
	 */
	private boolean clicked;
	
	/**
	 * Access the click variable.
	 */
	@Override
	public boolean clicked() {
		return clicked;
	}

	/**
	 * Set the click variable.
	 */
	@Override
	public void setClicked(boolean b) {
		clicked = b;
	}
	
	/**
	 * Whether this is hovered.
	 */
	private boolean hovered;

	/**
	 * Access the hover variable
	 */
	@Override
	public boolean hovered() {
		return hovered;
	}

	/**
	 * Set the hover variable
	 */
	@Override
	public void setHovered(boolean b) {
		hovered = b;
	}

	/**
	 * Default - never contains
	 */
	@Override
	public boolean contains(int xLoc, int yLoc) {
		return false;
	}

	/**
	 * Nothing
	 */
	@Override
	public void mouseHoverOn() {
		
	}

	/**
	 * Nothing
	 */
	@Override
	public void mouseHoverOff() {
		
	}

	/**
	 * Nothing
	 */
	@Override
	public void mouseClickOn() {
		
	}

	/**
	 * Nothing
	 */
	@Override
	public void mouseClickOff() {
		
	}

}
