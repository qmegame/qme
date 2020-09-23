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
        super(app, tile, 3, 1, 15, 3, 2);
        type = UnitType.HORSE_ARCHER;
    }
    
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