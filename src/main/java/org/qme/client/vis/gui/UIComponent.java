package org.qme.client.vis.gui;

import org.qme.client.Application;
import org.qme.client.vis.Layer;
import org.qme.client.vis.Renderable;
import org.qme.client.vis.wn.WindowManager;

/**
 * Simple class to extend both and offer default functionality.
 * @author adamhutchings
 * @since 0.1.0
 */
public abstract class UIComponent implements MouseResponder, Renderable {

	private boolean clicked;
	private boolean hovered;
	private boolean visible = true;
	
	/**
	 * For logging purposes (maybe more later)
	 * @return the name of the component
	 */
	public static final String getName = "component.unnamed";

	/**
	 * Default constructor
	 */
	protected UIComponent() {
		WindowManager.addObject(this);
		Application.registerMouseResponder(this);
	}

	@Override
	public Layer layer() {
		return Layer.UI;
	}

	/**
	 * Checks if the object should be rendered
	 * @return if the object is visible
	 */
	public boolean isVisible() { return visible; }

	/**
	 * Changes the visibility of the object
	 * @param b the new visibility of the object
	 */
	public void setVisible(boolean b) { visible = b; }
	
	/**
	 * Gets if the UIComponent is clicked
	 * @return if the component is clicked
	 */
	@Override
	public boolean isClicked() {
		return clicked;
	}

	/**
	 * Sets the current clicked state of the UIComponent
	 * @param b if the component should be clicked
	 */
	@Override
	public void setClicked(boolean b) {
		clicked = b;
	}

	/**
	 * Gets if the UIComponent is hovered
	 * @return if the component is hovered
	 */
	@Override
	public boolean isHovered() {
		return hovered;
	}

	/**
	 * Sets the current hovered state of the UIComponent
	 * @param b if the component should be hovered
	 */
	@Override
	public void setHovered(boolean b) {
		hovered = b;
	}

	/**
	 * Always false. Does not contain.
	 */
	@Override
	public boolean contains(int xLoc, int yLoc) {
		return false;
	}

	@Override
	public void mouseHoverOn(int x, int y) {
		// Do nothing
	}

	@Override
	public void mouseHoverOff(int x, int y) {
		// Do nothing
	}

	@Override
	public void mouseClickOn(int x, int y) {
		// Do nothing
	}

	@Override
	public void mouseClickOff(int x, int y) {
		// Do nothing
	}

	@Override
	public void draw() {
		// Do nothing
	}

}
