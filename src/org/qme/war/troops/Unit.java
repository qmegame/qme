package org.qme.war.troops;

import java.lang.Math;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.vis.QRenderable;
import org.qme.vis.ui.UIComponent;
import org.qme.world.Tile;

/**
 * The Unit class is troops and stuff
 * @author santiago
 * @since pre2
 */
public abstract class Unit extends QObject implements QRenderable, UIComponent {
	public Unit(QApplication app) {	// I don't know and this makes an error go away.
		super(app);
	}
	
	private boolean actionable = true;	// Whether or not a unit can do stuff (aka it's dead)
	
	public Tile tileOn;
	
	private double attack;
	private double defense;
	private double health;
	private double movement;
	
	public double getAttack() { return this.attack; }
	public double getDefense() { return this.defense; }
	public double getHealth() { return this.health; }
	public double getMovement() { return this.movement; }
	
	public double currentAttack;
	public double currentDefense;
	public double currentHealth;
	public double currentMovement;
	
	public double currentAttack() { return this.currentAttack; }
	public double currentDefense() { return this.currentDefense; }
	public double currentHealth() { return this.currentHealth; }
	public double currentMovement() { return this.currentMovement; }
	
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
	
	private double movementCalculate(Tile target) {
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
}