package org.qme.menus;

import java.awt.event.WindowEvent;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.vis.QInputScreen;
import org.qme.vis.ui.QButton;

public class EscapeMenu {
	
	public static void makeMenu(QApplication app) {
		
		// Quit time
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT / 2) + 100, "Quit") {

			@Override
			public void mouseClickOff() {
				app.qiscreen.dispatchEvent(new WindowEvent(app.qiscreen, WindowEvent.WINDOW_CLOSING));
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.ESCAPE_MENU;				
			}
			
		};
		
		// Return to game
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT / 2) - 100, "Return to game") {

			@Override
			public void mouseClickOff() {
				app.setState(GlobalState.MAIN_GAME);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.ESCAPE_MENU;				
			}
			
		};
		
		// Get to settings
		new QButton(app, QInputScreen.SCREEN_WIDTH - 75, 25, "Settings") {

			@Override
			public void mouseClickOff() {
				app.setState(GlobalState.SETTINGS_MENU);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.ESCAPE_MENU;				
			}
			
		};
			
	}

}
