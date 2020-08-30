package org.qme.vis;

import java.awt.Graphics;

/**
 * This just represents an item that can be drawn.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.vis.QRenderScreen
 */
public interface QRenderable {
	
	abstract QLayer getLayer();
	
	/**
	 * Is called every reload cycle to display the object.
	 * @param g - the Graphics object for the QRenderScreen instance.
	 * @see org.qme.vis.QRenderScreen
	 */
	abstract void render(Graphics g);

}
