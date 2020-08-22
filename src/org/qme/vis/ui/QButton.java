package org.qme.vis.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.qme.main.QApplication;
import org.qme.main.QObject;

/**
 * A basic part of the user interface system. It registers clicks in the area
 * outlined by its rectangle. Any created QButton objects must have inline
 * anonymous classes with an override for mouseClickOff.
 * @author adamhutchings
 * @since pre0
 * @see UIComponent
 */
public abstract class QButton extends QObject implements UIComponent {
	
	/**
	 * The background color of a button.
	 */
	public Color backgroundColor;
	
	/**
	 * The font color of all buttons. May change to be
	 * a member variable in the future.
	 */
	private static final Color TEXT_COLOR = new Color(0, 0, 0);
	
	/**
	 * Default width of a button.
	 */
	private static final int BUTTON_WIDTH = 150;
	
	/**
	 * Default height of a button.
	 */
	private static final int BUTTON_HEIGHT = 50;
	
	/**
	 * The central x coordinate of the button.
	 * @since pre0
	 */
	public int centerX;
	
	/**
	 * The center y coordinate of the button.
	 * @since pre0
	 */
	public int centerY;
	
	/**
	 * Internal use only (for now) - in case the width
	 * needs to be changed.
	 */
	private int width;
	
	/**
	 * Internal use only (for now) - in case the height
	 * needs to be changed.
	 */
	private int height;
	
	/**
	 * The text that displays on the button.
	 * @since pre0
	 */
	public String text;
	
	/**
	 * A simple member-setting constructor, also makes the
	 * default size of the button.
	 * @param x the x-coordinate of the button
	 * @param y the y-coordinate of the button
	 * @param str the text displayed on the button
	 * @author adamhutchings
	 */
	public QButton(QApplication a, int x, int y, String str) {
		
		super(a);
		
		backgroundColor = new Color(225, 225, 225);
		
		// Member setting
		centerX = x;
		centerY = y;
		text = str;
		
		// Size
		width = BUTTON_WIDTH;
		height = BUTTON_HEIGHT;
		
	}

	@Override
	/**
	 * Renders the rectangle and then the text
	 * in the appropriate places.
	 * @since pre0
	 * @author adamhutchings
	 */
	public void render(Graphics g) {
		
		Rectangle backgroundRect = getRect();
		
		// Draw the rectangle
		g.setColor(backgroundColor);
		g.fillRect(backgroundRect.x, backgroundRect.y, backgroundRect.width, backgroundRect.height);
		
		// Draw the text
		g.setColor(TEXT_COLOR);
		UIUtils.drawCenteredString(g, text, backgroundRect, UIUtils.QME_FONT);
		
		// Draw highlight
		if (hoveredOver) {
			g.setColor(Color.BLACK);
			g.drawRect(backgroundRect.x, backgroundRect.y, backgroundRect.width, backgroundRect.height);
		}
		
	}
	
	@Override
	/**
	 * Does nothing for now
	 */
	public void update(QApplication app) {
		
	}
	
	/**
	 * Returns a rectangle representing the button.
	 * @since pre0
	 */
	public Rectangle getRect() {
		return new Rectangle(centerX - (width / 2), centerY - (height / 2), width, height);
	}

	@Override
	public void mouseClickOn() {
		// Not going to do anything for now (later expand the rectangle)
	}
	
	@Override
	public void mouseHoverOn() {
		
	}
	
	@Override
	public void mouseHoverOff() {
		
	}

	/**
	 * Finds if the point is inside the rectangle.
	 * @since pre0
	 * @author adamhutchings
	 */
	@Override
	public boolean clickIsIn(int x, int y) {
		return getRect().contains(x, y);
	}

}
