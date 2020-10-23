package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class Legionnaire extends Unit {

    public Legionnaire(QApplication app, Tile tile) {
        super(app, tile, 0, 1, 5, 2, 2, UnitType.LEGIONNAIRE);
    }
    
    @Override
    public double currentAttack() {
    	int ABonus = 0;
    	if(this.tileOn.x != 0) {
    		if(this.application.world.tiles[this.tileOn.x - 1][this.tileOn.y].occupier.type == UnitType.LEGIONNAIRE) {
    			ABonus++;
    		}
    	}
    	if(this.tileOn.x != (this.application.world.xDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x + 1][this.tileOn.y].occupier.type == UnitType.LEGIONNAIRE) {
    			ABonus++;
    		}
    	}
    	if(this.tileOn.y != 0) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y - 1].occupier.type == UnitType.LEGIONNAIRE) {
    			ABonus++;
    		}
    	}
    	if(this.tileOn.y != (this.application.world.yDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y + 1].occupier.type == UnitType.LEGIONNAIRE) {
    			ABonus++;
    		}
    	}
    	
    	
    	return this.currentAttack + ABonus;
    }
    
    @Override
    public double currentDefense() {
    	int DBonus = 0;
    	if(this.tileOn.x != 0) {
    		if(this.application.world.tiles[this.tileOn.x - 1][this.tileOn.y].occupier.type == UnitType.LEGIONNAIRE) {
    			DBonus++;
    		}
    	}
    	if(this.tileOn.x != (this.application.world.xDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x + 1][this.tileOn.y].occupier.type == UnitType.LEGIONNAIRE) {
    			DBonus++;
    		}
    	}
    	if(this.tileOn.y != 0) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y - 1].occupier.type == UnitType.LEGIONNAIRE) {
    			DBonus++;
    		}
    	}
    	if(this.tileOn.y != (this.application.world.yDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y + 1].occupier.type == UnitType.LEGIONNAIRE) {
    			DBonus++;
    		}
    	}
    	
    	
    	return this.currentDefense + DBonus;
    }
    
    @Override
    public double currentHealth() {
    	int HBonus = 0;
    	if(this.tileOn.x != 0) {
    		if(this.application.world.tiles[this.tileOn.x - 1][this.tileOn.y].occupier.type == UnitType.LEGIONNAIRE) {
    			HBonus += 5;
    		}
    	}
    	if(this.tileOn.x != (this.application.world.xDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x + 1][this.tileOn.y].occupier.type == UnitType.LEGIONNAIRE) {
    			HBonus += 5;
    		}
    	}
    	if(this.tileOn.y != 0) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y - 1].occupier.type == UnitType.LEGIONNAIRE) {
    			HBonus += 5;
    		}
    	}
    	if(this.tileOn.y != (this.application.world.yDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y + 1].occupier.type == UnitType.LEGIONNAIRE) {
    			HBonus += 5;
    		}
    	}
    	
    	
    	return this.currentHealth + HBonus;
    }
    
}