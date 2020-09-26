package org.qme.main;

import java.util.ArrayList;

import org.qme.player.PoliticalEntity;

/**
 * This is the class that will be written to saves eventually
 * It'll ideally be updated every frame
 * @author S-Mackenzie1678
 * @since pre1
 */
public final class GameState {
	public QApplication owner;
	public ArrayList<PoliticalEntity> civilizations;
	public int playerTurn;
	public int turn;
	
	public GameState(QApplication app) {
		this.owner = app;
		this.civilizations = new ArrayList<PoliticalEntity>();
		this.playerTurn = 0;
		this.turn = 0;
	}
	
	public void turnEnded() {
		this.playerTurn++;
		this.playerTurn %= this.civilizations.size();
		if (this.playerTurn == 0) {
			this.turn++;
		}
	}
}