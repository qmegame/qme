package org.qme.structure;

import java.util.ArrayList;
import org.qme.main.QApplication;
import org.qme.player.PoliticalEntity;
import org.qme.world.Tile;

/**
 * Just the basics of what I assume a settlement might need.
 * @author adamhutchings
 * @author S-Mackenzie1678
 * @since pre3
 */
public class Settlement extends Structure {

	public Settlement(QApplication app, Tile t, PoliticalEntity s) {
		super(app);
		tile = t;
		directOwner = s;
	}

	public Tile tile;
	
	public PoliticalEntity directOwner;
	
	public int population;
	public int maxPopulation;
	
	public double happiness;
	public double crime;
	
	// Will eventually become an array of the child class of Structure for processing buildings
	private ArrayList<Structure> processingBuildings = new ArrayList();
	// Ditto, but social buildings
	private ArrayList<Structure> socialBuildings = new ArrayList();
	// Ditto, but defense buildings
	private ArrayList<Structure> defenseBuildings = new ArrayList();

	@Override
	public void action() {
		// Money. We'll see. Who knows?
	}

	@Override
	public void update(QApplication app) {
		if(this.population > this.maxPopulation) {
			this.population = this.maxPopulation;
		}
	}
	
	/**
	 * Is this the capital city? Checks if the owner is itself.
	 */
	public boolean isCapital() {
		return (owner == this);
	}
	
}
