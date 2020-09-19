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

    private double attack = 2;
    private double defense = 3;
    private double health = 15;
    private double movement = 2;
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