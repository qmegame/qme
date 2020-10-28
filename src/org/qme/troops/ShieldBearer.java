package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class ShieldBearer extends Unit {

    public ShieldBearer(QApplication app, Tile tile) {
        super(app, tile, 0, 2, 15, 2, 0, UnitType.SHIELD_BEARER, 1);
    }
    
    @Override
    public double currentDefense() {
    	int bonus = 0;
    	if(this.tileOn.x != 0) {
    		if(this.application.world.tiles[this.tileOn.x - 1][this.tileOn.y].occupier != null) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.x != (this.application.world.xDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x + 1][this.tileOn.y].occupier != null) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.y != 0) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y - 1].occupier != null) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.y != (this.application.world.yDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y + 1].occupier != null) {
    			bonus++;
    		}
    	}
    	
    	return this.currentDefense + bonus;
    }
    
}