package org.qme.player;

import java.util.ArrayList;

import org.qme.structure.Settlement;
import org.qme.util.NameGen;

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
	
	public PoliticalEntity superior = null;
	
	public Settlement home;
	
	public String name;
	
	@SuppressWarnings("serial")
	ArrayList<Settlement> ownedCities = new ArrayList<>() {{add(home);}};
	
	public boolean ai;
	
	public PoliticalEntity(String n) {
		
		name = n;
		
		// Settlement stuff here later
		
	}
	
	// Without a name
	public PoliticalEntity() {
		this(NameGen.namer());
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

}
