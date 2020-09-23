package org.qme.war.troops;

import org.qme.main.QApplication;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class Catapult extends Unit {

    public Catapult(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    
    public UnitType type = UnitType.CATAPULT;

    private double attack = 5;
    private double defense = 0;
    private double health = 5;
    private double movement = 1;
    private int attacks = 2;
}