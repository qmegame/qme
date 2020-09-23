package org.qme.war.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * @author santiago
 *@since pre3
 */
public class HorseArcher extends Unit {

    public HorseArcher(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    
    public UnitType type = UnitType.HORSE_ARCHER;

    private double attack = 3;
    private double defense = 1;
    private double health = 15;
    private double movement = 3;
    private int attacks = 2;
    
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
}