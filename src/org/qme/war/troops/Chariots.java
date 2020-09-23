package org.qme.war.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * @author santiago
 *@since pre3
 */
public class Chariots extends Unit {

    public Chariots(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    
    public UnitType type = UnitType.CHARIOTS;

    private double attack = 4;
    private double defense = 3;
    private double health = 20;
    private double movement = 1;
    private int attacks = 2;
    
    @Override
    public double currentMovement() {
    	double m = 1;
    	if(this.tileOn.getType() == TileType.MOUNTAIN || this.tileOn.getType() == TileType.HIGH_MOUNTAIN) {
    		m = 0.75;
    	}
    	return currentMovement * m;
    }
}