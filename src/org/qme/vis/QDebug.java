package org.qme.vis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.qme.main.GlobalState;
import org.qme.main.Main;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.vis.ui.UIComponent;
import org.qme.vis.ui.UIUtils;

public class QDebug extends QObject implements UIComponent {
	
	/* 
	 * A static boolean for whether the debug screen is on or off.
	 */
	static Boolean isDebugOn = false;
	
	/*
	 * A public static method for toggling the debug screen.
	 */
	public static void toggleDebug() {
		isDebugOn = !isDebugOn;
	}
	
	private static final Color TEXT_COLOR = new Color(0, 0, 0);
	
	public String text;
	
	@Override
	public QLayer getLayer() {
		return QLayer.UI_LAYER;
	}
	
	/*
	 * The x-coordinate of the center of the text.
	 */
	public int centerX;
	
	/*
	 * The y-coordinate of the center of the text.
	 */
	public int centerY;
	
	public QDebug(QApplication a, int x, int y) {
		
		super(a);
		
		// Member setting
		centerX = x;
		centerY = y;
		
	
	}
	
	/*
	 * The method that renders the debug screen.
	 */
	public void render(Graphics g) {
		
		// Make the strings for the different stuff in the debug screen
		String fpsText = "FPS: " + Main.FRAMERATE;
		String globalStateText = "GlobalState: " + this.application.getState();
		
		// Put all the strings together
		Rectangle debugRect = getRect();
		String debugText = fpsText + " " + globalStateText;
		
		// Draw the stuff
		g.setColor(TEXT_COLOR);
		if (isDebugOn) {
			UIUtils.drawCenteredString(g, debugText, debugRect, UIUtils.QME_FONT);
		}
	}
	
	/*
	 * A useless method that returns the rectangle that the debug text is centered at. It's useless because the rectangle is degenerate.
	 */
	public Rectangle getRect() {
		return new Rectangle(centerX, centerY, 0, 0);
	}
	
	@Override
	public boolean clickIsIn(int x, int y) {
		return false;
	}
	
	@Override
	public void mouseClickOn() {
		
	}
	
	@Override
	public void mouseClickOff() {
		
	}
	
	@Override
	public void mouseHoverOn() {
		
	}
	
	@Override
	public void mouseHoverOff() {
		
	}
	
	@Override
	public GlobalState getActiveState() {
		return GlobalState.MAIN_GAME;
	}
	
	@Override
	public void update(QApplication app) {
		
	}
}
