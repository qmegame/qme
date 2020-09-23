package org.qme.war.troops;

import java.awt.Graphics;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.vis.QLayer;
import org.qme.world.Tile;

/**
 * @author santiago
 *@since pre3
 */
public class Swordsman extends Unit {

    public Swordsman(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    public UnitType type = UnitType.SWORDSMAN;

    private double attack = 2;
    private double defense = 3;
    private double health = 15;
    private double movement = 2;
    private int attacks = 2;
    
}