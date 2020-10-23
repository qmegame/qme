package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * @author santiago
 *@since pre3
 */
public class Chariots extends Unit {

    public Chariots(QApplication app, Tile tile) {
        super(app, tile, 4, 3, 20, 1, 2);
        type = UnitType.CHARIOTS;
    }
    
    @Override
    public double currentMovement() {
    	double m = 1;
    	if(this.tileOn.getType() == TileType.MOUNTAIN || this.tileOn.getType() == TileType.HIGH_MOUNTAIN) {
    		m = 0.75;
    	}
    	return currentMovement * m;
    }
    
    @Override
    public double attack(Unit defender) {
    	double m = 1;
    	if(defender.type == UnitType.SPEARMAN) {
    		m = 1.25;
    	}
    	if(this.actionable()) { 
  			double returN = ((2.5 * this.currentAttack() * (this.currentHealth() / this.getHealth())) / (defender.currentDefense() * m));
  			return returN;
  		}
  		return 0;
    }
    
}