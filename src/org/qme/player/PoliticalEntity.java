package org.qme.player;

import java.util.ArrayList;

import org.qme.structure.Settlement;
import org.qme.tech.Tech;
import org.qme.util.NameGen;
import org.qme.world.TileType;

/**
 * For the new political system!
 * Every political entity will have an owner (may be null)
 * and a list of subordinates, as well as some things that
 * may be owned.
 * @author adamhutchings
 * @since pre3
 */
public class PoliticalEntity {
	
	ArrayList<PoliticalEntity> subordinates = new ArrayList<>();
	
	public PoliticalEntity superior;
	
	public Settlement capital;
	
	public String name;
	
	ArrayList<Settlement> ownedCities = new ArrayList<>();
	
	public boolean ai;
	
	public PoliticalEntity(String n) {
		
		name = n;
		ai = false;
		
		// We're going to have to set the capital here later.
		ownedCities.add(capital);
		
		techs.add(Tech.NULL_TECH);
		
	}
	
	// Without a name
	public PoliticalEntity() {
		this(NameGen.namerTibet());
		/*if(this.capital.tile.getType() == TileType.DESERT) {
			this.name = NameGen.namerSahara();
		} else if(this.capital.tile.getType() == TileType.FERTILE_PLAINS || this.capital.tile.getType() == TileType.PLAINS || this.capital.tile.getType() == TileType.FOREST) {
			this.name = NameGen.namerIroquois();
		} else {
			this.name = NameGen.namerTibet();
		}*/	// Uncomment when capital gets actually set and this won't give big nullptrexceptions.
		ai = true;
	}
	
	/**
	 * Get the name that this will be referred to by.
	 */
	public String getName() {
		if (getLevels() == 0) {
			// Check how many cities are owned.
			if (ownedCities.size() == 1) {
				return "City-state";
			} else if (ownedCities.size() < 4) {
				return "Confederation";
			} else if (ownedCities.size() < 8) {
				return "Nation";
			} else {
				return "Empire";
			}
		} else {
			return "Territory of " + superior.name;
		}
	}
	
	/**
	 * If nothing owns this, the level is 0.
	 * If a level-0 entity owns this, the level is 1.
	 * @return the level
	 * @author adamhutchings
	 * @since pre3
	 */
	public int getLevels() {
		
		if (superior == null) {
			return 0;
		} else {
			return superior.getLevels() + 1;
		}
		
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
