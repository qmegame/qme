package org.qme.menus;

import javax.swing.JOptionPane;

import org.qme.main.GlobalState;
import static org.qme.main.Main.displayError;
import org.qme.main.QApplication;
import org.qme.player.AI;
import org.qme.player.Human;
import org.qme.player.Player;
import static org.qme.util.GlobalConstants.SCREEN_HEIGHT;
import static org.qme.util.GlobalConstants.SCREEN_WIDTH;
import org.qme.vis.ui.QButton;
import org.qme.world.World;

public class SelectionMenu {
	
	public static void makeMenu(QApplication app) {
		
		// New player time
		new QButton(app, SCREEN_WIDTH / 2, (SCREEN_HEIGHT / 2) - 100, "New Player") {
			
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
							displayError("Please choose a non-duplicate name.", false);
						} else {
							app.game.civilizations.add(new Human(playerName));
						}
					} else {
						displayError("Oops! No name was selected. Try again, you failure.", false);
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
		
		// Start game time
		new QButton(app, SCREEN_WIDTH / 2, (SCREEN_HEIGHT / 2) + 100, "Start Game") {

			@Override
			public void mouseClickOff() {
				
				if (app.game.civilizations.size() > 0) {
				
					app.world = new World(app, 25, 25);
				
					app.setState(GlobalState.MAIN_GAME);
				
				} else {
					
					displayError("You need to have at least some players. Seriously.", false);
					
				}
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.GAME_SELECTION;
			}
			
		};
		
	}

}
