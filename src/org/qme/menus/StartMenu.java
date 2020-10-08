package org.qme.menus;

import java.io.File;
import org.qme.audio.AudioWrapper;
import org.qme.main.GameState;
import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import static org.qme.util.GlobalConstants.SCREEN_HEIGHT;
import static org.qme.util.GlobalConstants.SCREEN_WIDTH;
import org.qme.vis.ui.QButton;

public class StartMenu {
	
	public static void makeMenu(QApplication app) {
		
		AudioWrapper music = new AudioWrapper(new File("res/QME5_Menu.wav"));
		music.loopForever();
		
		// New game time
		new QButton(app, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, "New Game") {
			@Override
			public void mouseClickOff() {
				app.setState(GlobalState.GAME_SELECTION);
				music.stopLooping();
				app.game = new GameState(app);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.MAIN_MENU;
			}
			
		};
		
	}

}
