package org.qme.vis.ui;

import org.qme.vis.QRenderable;

/**
 * This class just represents a component of the user interface
 * system. It has methods for responding to clicks that are handled in
 * QInputScreen's interface methods. The two types of events (for now)
 * are click on and click off.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.vis.QInputScreen
 */
public interface UIComponent extends QRenderable {
	/**
	 * This method will be invoked if the mouse is pressed down
	 * over this component.
	 * @see clickIsIn
	 */
	public abstract void mouseClickOn();
	/**
	 * This method will be invoked if the mouse is released
	 * over this component.
	 * @see clickIsIn
	 */
	public abstract void mouseClickOff();
	
	/**
	 * The mouse handling methods will only be invoked if clickIsIn
	 * for the mouse location returns true.
	 * @param x - x coordinate of the click.
	 * @param y - y coordinate of the click.
	 * @return whether a click should be counted for event handling.
	 */
	public abstract boolean clickIsIn(int x, int y);
	
}
