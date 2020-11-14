package org.qme.vis.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.player.PoliticalEntity;
import org.qme.tech.Tech;
import org.qme.util.GlobalConstants;
import org.qme.vis.QLayer;
import org.qme.vis.QRenderable;

/**
 * A single tech in the view
 * @author adamhutchings
 * @since pre3
 */
public class TechViewer extends QObject implements QRenderable, UIComponent {
	
	public static int TECH_VIEW_WIDTH = 150;
	public static int TECH_VIEW_HEIGHT = 25;
	
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
			xPos - TECH_VIEW_WIDTH / 2, yPos - TECH_VIEW_HEIGHT / 2,
			TECH_VIEW_WIDTH, TECH_VIEW_HEIGHT
		);
	}

	@Override
	public GlobalState getActiveState() {
		return GlobalState.TECH_TREE_VIEW;
	}

	@Override
	public void mouseClickOn() {
		// NONFUNCTIONAL
		if (GlobalConstants.TOOLTIPS) {
			BottomViewBar.viewBar.color = BottomViewBar.TECH_COLOR;
			BottomViewBar.viewBar.leftString = "Tech: " + tech.getName();
			BottomViewBar.viewBar.middleString = "Parent: " + tech.parent.getName();
			BottomViewBar.viewBar.rightString = "Cost: 0"; // TODO TODO TODO
		}
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
		
		// Render the rectangle base
		PoliticalEntity player = application.game.civilizations.get(application.game.playerTurn);
		if (player.hasTech(tech)) {
			g.setColor(Color.BLUE);
		} else if (player.canGetTech(tech)) {
			g.setColor(new Color(0, 100, 0));
		} else {
			g.setColor(Color.BLACK);
		}
		
		g.drawRect(backgroundRect.x, backgroundRect.y, backgroundRect.width, backgroundRect.height);
		
		// Draw the outline (THICC)
		if (this.hoveredOver) {
			g.setColor(Color.BLACK);
			Graphics2D gCopy = (Graphics2D) g.create();
			gCopy.setStroke(new BasicStroke(5));
			gCopy.drawRect(backgroundRect.x, backgroundRect.y, backgroundRect.width, backgroundRect.height);
			gCopy.dispose();
		}
		
		// Write the name of the tech in small font
		Font copyFont = UIUtils.QME_FONT.deriveFont(15f);
		UIUtils.drawCenteredString(g, tech.getName(), backgroundRect, copyFont);
		
	}

	@Override
	public void update(QApplication app) {
		// Nothing
	}

}
