package org.qme.client.vis;

/**
 * Used directly only by client code - represents something that can be drawn.
 * The client has an array of these objects that gets updated whenever something
 * gets changed.
 * @author adamhutchings
 * @since 0.1.0
 */
public interface Renderable {
	
	/**
	 * How the draw this object.
	 */
	void draw();

}
