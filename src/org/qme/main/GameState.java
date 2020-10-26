package org.qme.main;

import java.util.ArrayList;

import org.qme.ai.AIMain;
import org.qme.player.PoliticalEntity;
import org.qme.structure.Structure;

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
		
		// Update the structures.
		for (QObject qo : owner.objects) {
			if (qo instanceof Structure) {
				// Move if the turn is boutta happen
				if ( ((Structure) qo).owner.directOwner == civilizations.get(playerTurn)) {
					((Structure) qo).action();
				}
			}
		}
		
		// If it's an AI move, use the ai move thingy
		if (civilizations.get(playerTurn).ai) {
			AIMain.makeMove(civilizations.get(playerTurn), this);
			// Risk of recursion here, so at least one human player must exist.
			turnEnded();
		}
		
	}
}