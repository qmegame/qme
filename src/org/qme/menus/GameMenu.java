package org.qme.menus;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.vis.QInputScreen;
import org.qme.vis.ui.QButton;

public class GameMenu {
	
	public static void makeMenu(QApplication app) {
		
		// Next turn time
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT) - 100, "Next turn") {

			@Override
			public void mouseClickOff() {
				app.game.turnEnded();
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.MAIN_GAME;
			}
			
		};
		
	}

}
