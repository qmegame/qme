package org.qme.main;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import org.qme.player.Human;
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
	 * @author S-Mackenzie1678
	 * @since pre0
	 * @see org.qme.main.QApplication
	 * @param args - command line arguments: unused for now
	 * @throws InterruptedException - from the sleep call
	 */
	public static void main(String[] args) throws InterruptedException {
		try {
			FileOutputStream errors = new FileOutputStream("qdata/error_logs.txt");
			System.setErr(new PrintStream(errors));
		} catch(IOException e) {
			System.exit(0);
		}
		
		try {
			ErrorFileWatcher alert = new ErrorFileWatcher(new File("qdata/error_logs.txt"));
			
			QApplication app = new QApplication();
			
			// New game time
			new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT / 2) - 100, "New Game") {
	
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
					app.game.civilizations.add(new Human("Human Player " + app.game.civilizations.size()));
				}
				
				@Override
				public GlobalState getActiveState() {
					return GlobalState.GAME_SELECTION;
				}
				
			};
			
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
				
				alert.check();
			}

			
			
		} catch(Exception e) {
			System.exit(0);
		}
  
  }

}
