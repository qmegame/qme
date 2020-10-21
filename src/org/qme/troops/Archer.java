package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * @author santiago
 *@since pre3
 */
public class Archer extends Unit {

    public Archer(QApplication app, Tile tile) {
        super(app, tile, 1, 2, 15, 2, 2);
        type = UnitType.ARCHER;
    }
    
    @Override
    public double currentAttack() {
    	double multiplier = 1;
    	if(this.tileOn.getType() == TileType.FOREST) {
    		multiplier = 1.35;
    	}
    	return currentAttack * multiplier;
    }
    
    @Override
    public String getTexturePath() {
    	return "res/troops/archer.png";
    }
    
}