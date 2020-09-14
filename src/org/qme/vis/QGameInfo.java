package org.qme.vis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.vis.QInputScreen;
import org.qme.vis.ui.UIUtils;

/**
 * An object of this class displays
 * the current player and turn
 * @author adamhutchings
 * @since pre2
 */
public class QGameInfo extends QObject implements QRenderable {
	
	private static final Rectangle GAME_INFO_RENDER_RECT = new Rectangle(
			QInputScreen.SCREEN_WIDTH / 2, QInputScreen.SCREEN_HEIGHT - 200, 0, 0
	);

	public QGameInfo(QApplication app) {
		// Auto-generated constructor
		super(app);
	}

	@Override
	public QLayer getLayer() {
		return QLayer.EFFECT_LAYER;
	}

	/**
	 * Current player and turn
	 * @author adamhutchings
	 * @since pre2
	 */
	@Override
	public void render(Graphics g) {
		if (application.getState() == GlobalState.MAIN_GAME) {
			g.setColor(Color.BLACK);
			String text = "Player: " + application.game.civilizations.get(application.game.playerTurn).name
			       + "     Turn:"    + application.game.turn;
			UIUtils.drawCenteredString(g, text, GAME_INFO_RENDER_RECT, UIUtils.QME_FONT);
		}
	}

	@Override
	public void update(QApplication app) {
		// Updating doesn't do anything
	}
	
	

}
