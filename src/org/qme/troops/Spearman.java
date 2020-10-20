package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class Spearman extends Unit {

    public Spearman(QApplication app, Tile tile) {
        super(app, tile, 1, 2, 15, 2, 2);
        type = UnitType.SPEARMAN;
    }
    
    @Override
    public double currentAttack() {
    	int bonus = 0;
    	if(this.tileOn.x != 0) {
    		if(this.application.world.tiles[this.tileOn.x - 1][this.tileOn.y].occupier.type == UnitType.SPEARMAN) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.x != (this.application.world.xDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x + 1][this.tileOn.y].occupier.type == UnitType.SPEARMAN) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.y != 0) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y - 1].occupier.type == UnitType.SPEARMAN) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.y != (this.application.world.yDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y + 1].occupier.type == UnitType.SPEARMAN) {
    			bonus++;
    		}
    	}
    	
    	if(bonus > 2) {
    		bonus = 2;
    	}
    	return this.currentAttack + bonus;
    }
    
    @Override
    public String getTexturePath() {
    	return "res/troops/spearman.png";
    }
    
}