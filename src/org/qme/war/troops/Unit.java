package org.qme.war.troops;

import java.lang.Math;
import org.qme.main.QApplication;
import org.qme.main.QObject;
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
	
	public Unit(QApplication app) {	// Again, idk and this kills errors
		super(app);
		
		this.currentAttack = this.attack;
		this.currentDefense = this.defense;
		this.currentHealth = this.health;
		this.currentMovement = this.movement;
		this.currentAttacks = this.attacks;
	}
	
	private boolean actionable = true;	// Whether or not a unit can do stuff (aka it's dead)
	
	public Tile tileOn;
	public int morale = 0;
	
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
	 * Call this when morale changes (only deals with movement by 1)
	 * @param up
	 */
	private void moraleEffects(boolean up) {
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
			this.tileOn = target;
		}
	}
	
	public void involuntaryMove(Tile target) {
		this.tileOn = target;
	}
}
