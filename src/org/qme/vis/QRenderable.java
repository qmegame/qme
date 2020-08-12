package org.qme.vis;

import java.awt.Graphics;

/**
 * This just represents an item that can be drawn.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.vis.QRenderScreen
 */
public interface QRenderable {
	
	abstract void render(Graphics g);

}
