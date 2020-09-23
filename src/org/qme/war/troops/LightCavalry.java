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
}