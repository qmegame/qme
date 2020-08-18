package org.qme.main;

import java.util.ArrayList;

import org.qme.player.Player;
import org.qme.world.World;

/**
 * This is the class that will be written to saves eventually
 * It'll ideally be updated every frame
 * @author S-Mackenzie1678
 * @since pre1
 */
public final class GameState {
	World world;
	ArrayList<Player> civilizations = new ArrayList<Player>();
	int playerTurn;
	int turn;
	
	GameState(World world, ArrayList<Player> civilizations, int playerTurn, int turn) {
		this.world = world;
		this.civilizations = civilizations;
		this.playerTurn = playerTurn;
		this.turn = turn;
	}
}