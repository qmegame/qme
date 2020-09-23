package org.qme.war.troops;

import java.awt.Graphics;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.vis.QLayer;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class ShieldBearer extends Unit {

    public ShieldBearer(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    
    public UnitType type = UnitType.SHIELD_BEARER;

    private double attack = 0;
    private double defense = 2;
    private double health = 15;
    private double movement = 2;
    private int attacks = 0;
    
    @Override
    public double currentDefense() {
    	int bonus = 0;
    	if(this.tileOn.x != 0) {
    		if(this.application.world.tiles[this.tileOn.x - 1][this.tileOn.y].occupied) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.x != (this.application.world.xDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x + 1][this.tileOn.y].occupied) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.y != 0) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y - 1].occupied) {
    			bonus++;
    		}
    	}
    	if(this.tileOn.y != (this.application.world.yDimension - 1)) {
    		if(this.application.world.tiles[this.tileOn.x][this.tileOn.y + 1].occupied) {
    			bonus++;
    		}
    	}
    	
    	return this.currentDefense + bonus;
    }
}