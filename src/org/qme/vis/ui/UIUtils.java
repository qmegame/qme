package org.qme.vis.ui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Static utilities for rendering user interface
 * components in other classes. Will probably be expanded
 * upon later.
 * @author adamhutchings
 * @since pre0
 */
public class UIUtils {
	
	/**
	 * This font should generally be used. Setting
	 * the size to something other than the default is acceptable.
	 */
	public static final Font QME_FONT = new Font("TimesRoman", Font.BOLD, 20);
	
	/**
	 * Renders a text inside a rectangle. Useful for
	 * buttons, for example.
	 * @author adamhutchings
	 * @param g the graphics object with which to draw
	 * @param text the text to render
	 * @param rect the bounding rectangle inside which to render
	 * @param font the font to render the text in
	 */
	public static void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
		
		// Size of the font, etc.
		FontMetrics metrics = g.getFontMetrics(font);
		
		// Find the sizes of the fonts
		int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
		int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
		
		g.setFont(font);
		g.drawString(text, x, y);
		
	}

}
