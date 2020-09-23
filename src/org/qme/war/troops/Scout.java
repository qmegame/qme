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
public class Scout extends Unit {

    public Scout(QApplication app, Tile tile) {
        super(app, tile);
    }

    public Tile tileOn;
    public UnitType type = UnitType.SCOUT;

    private double attack = 1;
    private double defense = 1;
    private double health = 10;
    private double movement = 3;
    private int attacks = 2;

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

    @Override
    public QLayer getLayer() {
        // TODO Auto-generated method stub
        return QLayer.TROOP_LAYER;
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public GlobalState getActiveState() {
        // TODO Auto-generated method stub
    	return GlobalState.MAIN_GAME;
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