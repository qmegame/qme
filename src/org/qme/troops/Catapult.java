package org.qme.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class Catapult extends Unit {

    public Catapult(QApplication app, Tile tile) {
        super(app, tile, 5, 0, 5, 1, 2, UnitType.CATAPULT);
    }

}