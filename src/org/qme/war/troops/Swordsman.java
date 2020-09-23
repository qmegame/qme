package org.qme.war.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class Swordsman extends Unit {

    public Swordsman(QApplication app, Tile tile) {
        super(app, tile, 2, 3, 15, 2, 2);
        type = UnitType.SWORDSMAN;
    }
    
}