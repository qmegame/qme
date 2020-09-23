package org.qme.war.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * @author santiago
 *@since pre3
 */
public class Archer extends Unit {

    public Archer(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    
    public UnitType type = UnitType.ARCHER;

    private double attack = 1;
    private double defense = 2;
    private double health = 15;
    private double movement = 2;
    private int attacks = 2;
    
    @Override
    public double currentAttack() {
    	double multiplier = 1;
    	if(this.tileOn.getType() == TileType.FOREST) {
    		multiplier = 1.35;
    	}
    	return currentAttack * multiplier;
    }
}