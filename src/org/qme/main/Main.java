package org.qme.main;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.qme.logging.FileWatcher;
import static org.qme.logging.FileWatcher.ERROR_LOG;
import org.qme.player.AI;
import org.qme.player.Human;
import org.qme.vis.QDebug;
import org.qme.vis.QGameInfo;
import org.qme.player.Player;
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
	 * We'll put this somewhere else later. Hopefully. Maybe.
	 * @author adamhutchings
	 * @since pre3
	 */
	public final static void displayError(String status) {
		JOptionPane.showMessageDialog(null, status);
	}
	
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
		/**
		 * @author S-Mackenzie1678
		 * @since pre2
		 * @see org.qme.main.ErrorFileWatcher
		 */
		try {
			FileOutputStream errors = new FileOutputStream(ERROR_LOG);
			System.setErr(new PrintStream(errors));
		} catch(IOException e) {
			displayError("The file " + System.getProperty("user.dir") + System.getProperty("file.separator") + ERROR_LOG + " does not exist. Please create it.");
			System.exit(0);
		}
		
		try {
			
			FileWatcher alert = new FileWatcher(new File(ERROR_LOG));
			
			QApplication app = new QApplication();
			
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
			
			// New player time
			new QButton(app, QInputScreen.SCREEN_WIDTH / 2, (QInputScreen.SCREEN_HEIGHT / 2) - 100, "New Player") {
				
				@Override
				public void mouseClickOff() {
					
					Object[] playerTypes = {"Human", "AI"};
					int response = JOptionPane.showOptionDialog(
						app.qiscreen,
						"Choose the type of player:",
						"Player selection (TM)",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						playerTypes,
						1
					);
					if (response == 0) {
						
						String playerName = (String) JOptionPane.showInputDialog(
								
							app.qiscreen,
							"Please choose a name:",
							"Player Selection (TM)",
							JOptionPane.PLAIN_MESSAGE,
							null,
							null,
							""
								
						);
						
						if ((playerName != null) && (playerName.length() > 0)) {
							boolean duplicate = false;
							for (Player player : app.game.civilizations) {
								if (player.name.equals(playerName)) {
									duplicate = true;
								}
							}
							if (duplicate) {
								displayError("Please choose a non-duplicate name.");
							} else {
								app.game.civilizations.add(new Human(playerName));
							}
						} else {
							displayError("Oops! No name was selected. Try again, failure.");
						}
						
					} else {
						app.game.civilizations.add(new AI());
					}
					
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
					
					if (app.game.civilizations.size() > 0) {
					
						app.world = new World(app, 25, 25);
					
						app.setState(GlobalState.MAIN_GAME);
					
					} else {
						
						displayError("You need to have at least some players. Seriously.");
						
					}
				}
				
				@Override
				public GlobalState getActiveState() {
					return GlobalState.GAME_SELECTION;
				}
				
			};
			
			new QDebug(app, QInputScreen.SCREEN_WIDTH / 2, QInputScreen.SCREEN_HEIGHT / 15);
			
			new QGameInfo(app);
		
			// Main loop time
			while (true) {
				
				// Wait for some time
				TimeUnit.MILLISECONDS.sleep(1000 / FRAMERATE);
				
				app.reload();
				
				alert.check();
			}

			
			
		} catch(Exception e) {}
  
  }

}
