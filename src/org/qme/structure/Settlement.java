package org.qme.structure;

import org.qme.main.QApplication;
import org.qme.player.PoliticalEntity;
import org.qme.world.Tile;

/**
 * Just the basics of what I assume a settlement might need.
 * @author adamhutchings
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

	@Override
	public void action() {
		// Money. We'll see. Who knows?
	}

	@Override
	public void update(QApplication app) {}
	
	/**
	 * Is this the capital city? Checks if the owner is itself.
	 */
	public boolean isCapital() {
		return (owner == this);
	}
	
}
