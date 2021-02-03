package org.qme.client.vis;

/**
 * Used directly only by client code - represents something that can be drawn.
 * The client has an array of these objects that gets updated whenever something
 * gets changed.
 * @author adamhutchings
 * @since 0.1.0
 */
public abstract class Renderable {

	private boolean isVisible = true;

	/**
	 * Checks if the object should be rendered
	 * @return if the object is visible
	 */
	public boolean isVisible() { return isVisible; }

	/**
	 * Changes the visibility of the object
	 * @param b the new visibility of the object
	 */
	public void setVisible(boolean b) { isVisible = b; }

	/**
	 * How the draw this object.
	 */
	public void draw() {

	}

}
