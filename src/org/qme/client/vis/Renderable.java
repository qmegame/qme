package org.qme.client.vis;

/**
 * Used directly only by client code - represents something that can be drawn.
 * The client has an array of these objects that gets updated whenever something
 * gets changed.
 * @author adamhutchings
 *
 */
public interface Renderable {
	
	/**
	 * How the draw this object.
	 */
	public void draw();

}
