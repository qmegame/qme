package org.qme.troops;

import java.awt.Graphics;
import java.lang.Math;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.player.PoliticalEntity;
import org.qme.vis.QLayer;
import org.qme.vis.QRenderable;
import org.qme.vis.ui.UIComponent;
import org.qme.world.Tile;

/**
 * The Unit class is for all units, land or sea.
 * @author santiago
 * @since pre2
 */
public abstract class Unit extends QObject implements QRenderable, UIComponent {
	private static final float ABOVE_MORALE = 1.1f;
	private static final float BELOW_MORALE = 1.15f;
	
	public Unit(QApplication app, Tile tile,
			int a, int d, int h, int m, int attacks) {	// Again, idk and this kills errors
		super(app);
		
		attack = a;
		defense = d;
		health = h;
		movement = m;
		this.attacks = attacks;
		
		this.currentAttack = this.attack;
		this.currentDefense = this.defense;
		this.currentHealth = this.health;
		this.currentMovement = this.movement;
		this.currentAttacks = this.attacks;
		
	}
	
	private boolean actionable = true;	// Whether or not a unit can do stuff (aka it's dead)
	
	public Tile tileOn;
	public int morale = 0;
	public UnitType type;
	public PoliticalEntity owner;
	
	private double attack;
	private double defense;
	private double health;
	private double movement;
	private int attacks;
	
	public double getAttack() { return this.attack; }
	public double getDefense() { return this.defense; }
	public double getHealth() { return this.health; }
	public double getMovement() { return this.movement; }
	public int getAttacks() { return this.attacks; }
	
	public double currentAttack;
	public double currentDefense;
	public double currentHealth;
	public double currentMovement;
	public int currentAttacks;
	
	public double currentAttack() { return this.currentAttack; }
	public double currentDefense() { return this.currentDefense; }
	public double currentHealth() { return this.currentHealth; }
	public double currentMovement() { return this.currentMovement; }
	public int currentAttacks() { return this.currentAttacks; }
	
	/**
	 * Call this to check if this can do things
	 * @return Whether or not the unit can act
	 */
	public boolean actionable() { return this.actionable; }
	
	/**
	 * Call this when morale changes (only deals with movement by 1)
	 * @param up
	 */
	protected void moraleEffects(boolean up) {
		if(up && this.morale >= 0) {
			this.health *= ABOVE_MORALE;
			this.defense *= ABOVE_MORALE;
			this.attack *= ABOVE_MORALE;
		} else if(up && this.morale < 0) {
			this.health *= BELOW_MORALE;
			this.defense *= BELOW_MORALE;
			this.attack *= BELOW_MORALE;
		} else if(!up && this.morale > 0) {
			this.health /= ABOVE_MORALE;
			this.defense /= ABOVE_MORALE;
			this.attack /= ABOVE_MORALE;
		} else {
			this.health /= BELOW_MORALE;
			this.defense /= BELOW_MORALE;
			this.attack /= BELOW_MORALE;
		}
		if(up) {
			this.morale++;
		} else {
			this.morale--;
		}
	}
	
	public double attack(Unit defender) {
		if(this.actionable) { 
			double returN = ((2.5 * this.currentAttack() * (this.currentHealth() / this.getHealth())) / defender.currentDefense());
			return returN;
		}
		return 0;
	}
	
	public void takeDamage(int damage) {
		this.currentHealth -= damage;
	}
	
	public void remove() {
		this.actionable = false;
	}
	
	protected double movementCalculate(Tile target) {
		int xDistance = Math.abs(this.tileOn.x - target.x);
		int yDistance = Math.abs(this.tileOn.x - target.x);
		
		if(xDistance == yDistance) {
			return 1.5 * xDistance;
		} else if(xDistance > yDistance) {
			return 1.5 * yDistance + xDistance - yDistance;
		} else {
			return 1.5 * xDistance + yDistance - xDistance;
		}
	}
	
	public void voluntaryMove(Tile target) {
		if(this.actionable && this.movementCalculate(target) <= this.currentMovement()) {
			this.tileOn.occupier = null;
			this.tileOn = target;
			this.tileOn.occupier = this;
		}
	}
	
	public void involuntaryMove(Tile target) {
		this.tileOn.occupier = null;
		this.tileOn = target;
		this.tileOn.occupier = this;
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
    public void render(Graphics g) {
        // TODO Auto-generated method stub

    }

    @Override
    public QLayer getLayer() {
        // TODO Auto-generated method stub
    	return QLayer.TROOP_LAYER;
    }


    @Override
    public GlobalState getActiveState() {
        // TODO Auto-generated method stub
    	return GlobalState.MAIN_GAME;
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
