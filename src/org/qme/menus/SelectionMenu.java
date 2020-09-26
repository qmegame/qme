package org.qme.menus;

import org.qme.main.GlobalState;
import static org.qme.main.Main.displayError;
import org.qme.main.QApplication;
import org.qme.player.PoliticalEntity;

import static org.qme.util.GlobalConstants.SCREEN_HEIGHT;
import static org.qme.util.GlobalConstants.SCREEN_WIDTH;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.qme.vis.ui.QButton;
import org.qme.vis.ui.QOptionButton;
import org.qme.world.World;

public class SelectionMenu {
	
	public static String SMALL_WORLD_MESSAGE = "Small (15x15)";
	public static String MEDIUM_WORLD_MESSAGE = "Medium (25x25)";
	public static String LARGE_WORLD_MESSAGE = "Large (40x40)";
	
	public static int SMALL_WORLD_PLAYER_COUNT = 10;
	public static int MEDIUM_WORLD_PLAYER_COUNT = 25;
	public static int LARGE_WORLD_PLAYER_COUNT = 50;
	
	public static void makeMenu(QApplication app) {
		
		// For the below button.
		String[] worldSizes = {SMALL_WORLD_MESSAGE, MEDIUM_WORLD_MESSAGE, LARGE_WORLD_MESSAGE};
		
		// Size of the world
		QOptionButton sizePicker = new QOptionButton(app, SCREEN_WIDTH / 2, 100, worldSizes) {

			@Override
			public GlobalState getActiveState() {
				return GlobalState.GAME_SELECTION;
			}
			
		};
		
		ArrayList<PoliticalEntity> players = new ArrayList<>();
		
		new QButton(app, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, "New Player") {

			@Override
			public GlobalState getActiveState() {
				return GlobalState.GAME_SELECTION;
			}

			@Override
			public void mouseClickOff() {
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
					for (PoliticalEntity player : app.game.civilizations) {
						if (player.name.equals(playerName)) {
							duplicate = true;
						}
					}
					if (duplicate) {
						displayError("Please choose a non-duplicate name.", false);
					} else {
						players.add(new PoliticalEntity(playerName));
					}
				} else {
					displayError("Oops! No name was selected. Try again, you failure.", false);
				}
				
			}
			
		};
		
		// Start game time
		new QButton(app, SCREEN_WIDTH / 2, SCREEN_HEIGHT - 100, "Start Game") {

			@Override
			public void mouseClickOff() {
				
				int size;
				
				int goalPlayers;
				
				if (sizePicker.getOption() == SMALL_WORLD_MESSAGE) {
					size = 15;
					goalPlayers = SMALL_WORLD_PLAYER_COUNT;
				} else if (sizePicker.getOption() == MEDIUM_WORLD_MESSAGE) {
					size = 25;
					goalPlayers = MEDIUM_WORLD_PLAYER_COUNT;
				} else {
					size = 40;
					goalPlayers = LARGE_WORLD_PLAYER_COUNT;
				}
				
				if (players.size() == 0) {
					displayError("You're such a failure. Actually add a human player before starting the game.", false);
					return;
				}
				
				app.game.civilizations = players;
				
				// Add AIs until the count has been reached.
				for (int i = players.size(); i < goalPlayers; i++) {
					app.game.civilizations.add(new PoliticalEntity());
				}
				
				System.out.println(app.game.civilizations.size());

				app.world = new World(app, size, size);
				
				app.setState(GlobalState.MAIN_GAME);
				
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.GAME_SELECTION;
			}
			
		};
		
	}

}
