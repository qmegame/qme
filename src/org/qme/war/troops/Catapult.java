package org.qme.war.troops;

import java.awt.Graphics;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.vis.QLayer;
import org.qme.world.Tile;
import org.qme.world.TileType;

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

    @Override
    public QLayer getLayer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public GlobalState getActiveState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void mouseClickOn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClickOff() {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseHoverOn() {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseHoverOff() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean clickIsIn(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void update(QApplication app) {
        // TODO Auto-generated method stub

    }
}