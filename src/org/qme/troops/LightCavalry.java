package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * @author santiago
 *@since pre3
 */
public class LightCavalry extends Unit {

    public LightCavalry(QApplication app, Tile tile) {
        super(app, tile, 2, 1, 10, 4, 2);
        type = UnitType.LIGHT_CAVALRY;
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
    

    
    @Override
    public String getTexturePath() {
    	return "res/troops/light_cavalry.png";
    }
    
}