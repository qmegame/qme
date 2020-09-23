package org.qme.vis.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.tech.Tech;
import org.qme.vis.QLayer;
import org.qme.vis.QRenderable;

/**
 * A single tech in the view
 * @author adamhutchings
 * @since pre3
 */
public class TechViewer extends QObject implements QRenderable, UIComponent {
	
	private static int TECH_VIEW_WIDTH = 200;
	private static int TECH_VIEW_HEIGHT = 50;
	
	/**
	 * A rectangular background for the tech
	 */
	private Rectangle backgroundRect;
	
	// Which tech it is
	public Tech tech;

	public TechViewer(QApplication app, Tech t, int xPos, int yPos) {
		super(app);
		tech = t;
		backgroundRect = new Rectangle(
			xPos - TECH_VIEW_WIDTH / 2, yPos = TECH_VIEW_HEIGHT / 2,
			TECH_VIEW_WIDTH, TECH_VIEW_HEIGHT
		);
	}

	@Override
	public GlobalState getActiveState() {
		return GlobalState.TECH_TREE_VIEW;
	}

	@Override
	public void mouseClickOn() {
		// Nothing
	}

	@Override
	public void mouseClickOff() {
		// Nothing
	}

	@Override
	public void mouseHoverOn() {
		// Nothing
	}

	@Override
	public void mouseHoverOff() {
		// Nothing
	}

	@Override
	public boolean clickIsIn(int x, int y) {
		return backgroundRect.contains(x, y);
	}

	@Override
	public QLayer getLayer() {
		return QLayer.UI_LAYER;
	}

	@Override
	public void render(Graphics g) {
		
		// Render the rectangle base (will be different colors later)
		g.setColor(Color.GRAY);
		g.drawRect(backgroundRect.x, backgroundRect.y, backgroundRect.width, backgroundRect.height);
		
		// Draw the outline (THICC)
		if (this.hoveredOver) {
			g.setColor(Color.BLACK);
			Graphics2D gCopy = (Graphics2D) g.create();
			gCopy.setStroke(new BasicStroke(5));
			gCopy.drawRect(backgroundRect.x, backgroundRect.y, backgroundRect.width, backgroundRect.height);
			gCopy.dispose();
		}
		
		// Write the name of the tech
		UIUtils.drawCenteredString(g, tech.getName(), backgroundRect, UIUtils.QME_FONT);
		
	}

	@Override
	public void update(QApplication app) {
		// Nothing
	}

}
