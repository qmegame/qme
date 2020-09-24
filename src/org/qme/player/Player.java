package org.qme.player;

import java.util.ArrayList;

import org.qme.tech.Tech;
import org.qme.world.Tile;

/**
 * This class is a civilization, whether a human or an AI.
 * @author S-Mackenzie1678
 * @since pre1
 */
abstract public class Player {
	boolean currentTurn = false;
	boolean human;
	public String name;
	ArrayList<Tile> territory = new ArrayList<Tile>();
	// A list of variables like money and resources to come
	
	protected Player(boolean human, String name) {
		this.human = human;
		this.name = name;
		this.techs.add(Tech.NULL_TECH);
	}
	
	/**
	 * All the techs, y/n
	 * @see org.qme.tech
	 * @since pre3
	 */
	ArrayList<Tech> techs = new ArrayList<Tech>();
	
	public boolean hasTech(Tech tech) {
		for(int i = 0; i < this.techs.size(); i++) {
			if(this.techs.get(i) == tech) {
				return true;
			}
		}
		return false;
	}
	
	public boolean canGetTech(Tech tech) {
		for(int i = 0; i < this.techs.size(); i++) {
			if(this.techs.get(i) == tech.parent) {
				return true;
			}
		}
		return false;
	}
}