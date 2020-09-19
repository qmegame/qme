package org.qme.menus;

import org.qme.main.GameState;
import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.vis.QInputScreen;
import org.qme.vis.ui.QButton;

public class StartMenu {
	
	public static void makeMenu(QApplication app) {
	
		// New game time
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, QInputScreen.SCREEN_HEIGHT / 2, "New Game") {
	
			@Override
			public void mouseClickOff() {
				app.setState(GlobalState.GAME_SELECTION);
				app.game = new GameState(app);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.MAIN_MENU;
			}
			
		};
		
	}

}
