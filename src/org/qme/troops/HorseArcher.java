package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * @author santiago
 *@since pre3
 */
public class HorseArcher extends Unit {

    public HorseArcher(QApplication app, Tile tile) {
        super(app, tile, 3, 1, 15, 3, 2);
        type = UnitType.HORSE_ARCHER;
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
    public double currentAttack() {
    	double m = 1;
    	if(this.tileOn.getType() == TileType.MOUNTAIN || this.tileOn.getType() == TileType.HIGH_MOUNTAIN) {
    		m = 0.5;
    	} else if(this.tileOn.getType() == TileType.FOREST) {
    		m = 1.25;
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