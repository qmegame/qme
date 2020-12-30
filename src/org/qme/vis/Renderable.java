package org.qme.vis;

/**
 * Represents an object that can be drawn onto the screen. Because OpenGL
 * effectively works with just ints and floats, no members other than the
 * parameterless render function are needed.
 * @author adamhutchings
 * @since preA
 */
public interface Renderable {
	
	/**
	 * Draw the item in question.
	 */
	public void draw();

}
