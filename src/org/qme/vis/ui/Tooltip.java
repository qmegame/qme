package org.qme.vis.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.qme.main.QApplication;
import org.qme.main.QObject;

public class Tooltip extends QObject implements UIComponent {
	
	public static final Font TOOLTIP_FONT = new Font("TimesRoman", Font.BOLD, 8);
	
	private static final int TOOLTIP_WIDTH_BUFFER = 10;
	private static final int TOOLTIP_HEIGHT_BUFFER = 40;
	
	private int xLocation;
	private int yLocation;
	
	protected ArrayList<String> texts;

	/**
	 * Super constructor.
	 * @param app the application instance
	 */
	public Tooltip(QApplication app, ArrayList<String> t) {
		
		super(app);
		
	}

	@Override
	public void render(Graphics g) {
		
		Rectangle rect = new Rectangle();
		
		// Find the scale of the rectangle
		FontMetrics metrics = g.getFontMetrics(TOOLTIP_FONT);
		
		// The height we need is the height of all the fonts
		// The width we need is the widest one.
		
		int finalWidth = 0, finalHeight = 0;
		
		int tempW;
		
		for (String text : texts) {
			
			// Increase the height
			finalHeight += metrics.getHeight();
			
			// See if more width is needed
			if ((tempW = metrics.stringWidth(text)) > finalWidth) {
				finalWidth = tempW;
			}
			
			// Add a buffer on the sides
			finalHeight += TOOLTIP_HEIGHT_BUFFER;
			finalWidth  += TOOLTIP_WIDTH_BUFFER;
			
		}
		
		rect.x = xLocation - (finalWidth / 2);
		rect.width = finalWidth;
		
		rect.y = yLocation - (finalHeight / 2);
		rect.height = finalHeight;
		
		// Render the rectangle
		g.setColor(Color.WHITE);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		
		// The height to start at
		int height = rect.y +  TOOLTIP_HEIGHT_BUFFER / 2;
		
		// Render the text
		for (String text : texts) {
			UIUtils.drawCenteredString(
				g, text,
				new Rectangle(rect.x, height, rect.width, metrics.getHeight()),
				TOOLTIP_FONT
			);
			height += metrics.getHeight();
		}
		
	}

	@Override
	public void mouseClickOn() {
		// We might do something later	
	}

	@Override
	public void mouseClickOff() {
		// Nothing needed here
	}

	@Override
	public void mouseHoverOn() {
		// Also nothing needed	
	}

	@Override
	public void mouseHoverOff() {
		// Delete the object
		application.objects.remove(this);
	}

	@Override
	public boolean clickIsIn(int x, int y) {
		return false;
	}

	@Override
	public void update(QApplication app) {
		// I don't think we need anything here	
	}

}
