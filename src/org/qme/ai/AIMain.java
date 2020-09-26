package org.qme.ai;

import org.qme.main.GameState;
import org.qme.player.PoliticalEntity;

/**
 * Stuff for AI move.
 * @author adamhutchings
 *
 */
public final class AIMain {
	
	/**
	 * It's final, so no one can inherit, but if
	 * we make the constructor private, this class
	 * can never be instantiated.
	 */
	private AIMain() {}
	
	/**
	 * This code will eventually call other class methods,
	 * to make a move for the AI.
	 * @param entity the AI to make a move for.
	 * @param state the current state of the game
	 */
	public static void makeMove(PoliticalEntity entity, GameState state) {
		// Yippee
	}

}
