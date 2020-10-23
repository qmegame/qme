package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.vis.QLayer;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class Scout extends Unit {

    public Scout(QApplication app, Tile tile) {
        super(app, tile, 1, 1, 10, 3, 2, UnitType.SCOUT);
    }
    
    @Override
    protected double movementCalculate(Tile target) {
        int xDistance = Math.abs(this.tileOn.x - target.x);
        int yDistance = Math.abs(this.tileOn.x - target.x);

        if(xDistance >= yDistance) {
            return xDistance;
        } else {
            return yDistance;
        }
    }
    
}