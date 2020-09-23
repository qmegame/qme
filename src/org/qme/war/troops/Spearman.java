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
public class Spearman extends Unit {

    public Spearman(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    
    public UnitType type = UnitType.SPEARMAN;

    private double attack = 1;
    private double defense = 2;
    private double health = 15;
    private double movement = 2;
    private int attacks = 2;
    
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
    public QLayer getLayer() {
        // TODO Auto-generated method stub
    	return QLayer.TROOP_LAYER;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public GlobalState getActiveState() {
        // TODO Auto-generated method stub
    	return GlobalState.MAIN_GAME;
    }

    @Override
    public boolean clickIsIn(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void update(QApplication app) {
        // TODO Auto-generated method stub

    }
}