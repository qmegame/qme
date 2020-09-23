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
public class Legionnaire extends Unit {

    public Legionnaire(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    
    public UnitType type = UnitType.SPEARMAN;

    private double attack = 0;
    private double defense = 1;
    private double health = 5;
    private double movement = 2;
    private int attacks = 2;
    
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