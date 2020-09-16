package org.qme.war.troops;

import org.qme.world.Tile;

public class Scout extends Unit {

	public Tile tileOn;
	
	private double attack = 1;
	private double defense = 1;
	private double health = 10;
	private double movement = 3;
	private int attacks = 2;
	
	@Override
	double movementCalculate(Tile target) {
		int xDistance = Math.abs(this.tileOn.x - target.x);
		int yDistance = Math.abs(this.tileOn.x - target.x);
		
		if(xDistance >= yDistance) {
			return xDistance;
		} else {
			return yDistance;
		}
	}
	
}