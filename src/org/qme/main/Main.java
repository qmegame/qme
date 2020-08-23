package org.qme.main;

import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

import org.qme.vis.QInputScreen;
import org.qme.vis.ui.QButton;
import org.qme.world.World;

/**
 * Holds the main method and the FRAMERATE constant.
 * FRAMERATE may be moved to a different place later.
 * @author adamhutchings
 * @since pre0
 */
public class Main {
	
	/**
	 * This represents how often the screen is reloaded.
	 * Only in this class temporarily - may be moved.
	 * @author adamhutchings
	 * @since pre0
	 */
	public static final int FRAMERATE = 30;
	
	/**
	 * Creates a new QApplication instance and reloads it.
	 * @author adamhutchings
	 * @since pre0
	 * @see org.qme.main.QApplication
	 * @param args - command line arguments: unused for now
	 * @throws InterruptedException - from the sleep call
	 */
	public static void main(String[] args) throws InterruptedException {
		
		QApplication app = new QApplication();
		
		// New game time
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT / 2) - 100, "New Game") {

			@Override
			public void mouseClickOff() {
				app.setState(GlobalState.GAME_SELECTION);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.MAIN_MENU;
			}
			
		};
		
		// Quit time
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT / 2) + 100, "Quit") {

			@Override
			public void mouseClickOff() {
				app.qiscreen.dispatchEvent(new WindowEvent(app.qiscreen, WindowEvent.WINDOW_CLOSING));
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.MAIN_MENU;				
			}
			
		};
		
		// New player time
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT / 2) - 100, "New Player") {
			
			@Override
			public void mouseClickOff() {
				
			}
			
			@Override
			public GlobalState getActiveState() {
				
				return GlobalState.GAME_SELECTION;
				
			}
			
		};
		
		// Start game time
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT / 2) + 100, "Start Game") {

			@Override
			public void mouseClickOff() {
				app.world = new World(app, 25, 25);
				app.setState(GlobalState.MAIN_GAME);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.GAME_SELECTION;
			}
			
		};
		
		// Main loop time
		while (true) {
			
			// Wait for some time
			TimeUnit.MILLISECONDS.sleep(1000 / FRAMERATE);
			
			app.reload();
			
		}
		
	}

}
