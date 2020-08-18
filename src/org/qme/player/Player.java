package org.qme.player;

import java.util.ArrayList;

import org.qme.world.Tile;

/**
 * This class is a civilization, whether a human or an AI.
 * @author S-Mackenzie1678
 * @since pre1
 */
abstract public class Player {
	boolean currentTurn = false;
	boolean human;
	ArrayList<Tile> territory = new ArrayList<Tile>();
	// A list of variables like money and resources to come
	
	Player(boolean human) {
		this.human = human;
	}
}