package org.qme.war.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * @author santiago
 *@since pre3
 */
public class LightCavalry extends Unit {

    public LightCavalry(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    
    public UnitType type = UnitType.LIGHT_CAVALRY;

    private double attack = 2;
    private double defense = 1;
    private double health = 10;
    private double movement = 4;
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