package org.qme.vis.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.util.GlobalConstants;
import org.qme.vis.QLayer;

/**
 * The bottom view bar of the screen. Used to
 * replace the older tooltips (which honestly
 * looked like crap). This has one static member,
 * the bottom view bar.
 * @author adamhutchings
 * @since pre5
 */
public class BottomViewBar extends QObject implements UIComponent {
	
	public Color color;
	
	public static Color TILE_COLOR = new Color(0, 50, 0);
	public static Color UNIT_COLOR = Color.BLUE;
	public static Color TECH_COLOR = new Color(50, 0, 50);
	
	// The instance of the view bar.
	public static BottomViewBar viewBar;
	
	public static void registerViewBar(QApplication app) {
		viewBar = new BottomViewBar(app);
	}
	
	public String leftString;
	public String middleString;
	public String rightString;

	private BottomViewBar(QApplication app) {
		
		super(app);
		
		leftString = middleString = rightString = "";
		color = Color.RED; // Invalid
		
	}

	@Override
	public QLayer getLayer() {
		return QLayer.UI_LAYER;
	}

	@Override
	public void render(Graphics g) {
		
		Rectangle rect = new Rectangle(0, GlobalConstants.SCREEN_HEIGHT - 100, GlobalConstants.SCREEN_WIDTH, 100);
		
		g.setColor(color);
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		
		g.setColor(Color.WHITE);
		
		UIUtils.drawCenteredString(g, leftString, new Rectangle(
			rect.x, rect.y, GlobalConstants.SCREEN_WIDTH / 3, 100), UIUtils.QME_FONT
		);
		
		UIUtils.drawCenteredString(g, middleString, new Rectangle(
				GlobalConstants.SCREEN_WIDTH / 3, rect.y, GlobalConstants.SCREEN_WIDTH / 3, 100), UIUtils.QME_FONT
		);
		
		UIUtils.drawCenteredString(g, rightString, new Rectangle(
			GlobalConstants.SCREEN_WIDTH * 2 / 3, rect.y, GlobalConstants.SCREEN_WIDTH / 3, 100), UIUtils.QME_FONT
		);
			
	}
	public GlobalState getActiveState() {
		return GlobalState.MAIN_GAME;
	}

	@Override
	public void mouseClickOn() {}
	public void mouseClickOff() {}
	public void mouseHoverOn() {}
	public void mouseHoverOff() {}

	@Override
	public boolean clickIsIn(int x, int y) {
		// We don't need clicks here for now.
		return false;
	}

	@Override
	public void update(QApplication app) {
		// Nothing needed here.
	}

}
