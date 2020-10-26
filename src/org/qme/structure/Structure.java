package org.qme.structure;

import org.qme.main.QApplication;
import org.qme.main.QObject;

/**
 * This is the superclass for all buildings
 * and cities - anything that can be placed
 * on a tile (although depending on what design
 * does, it may not be so simple)
 * @author adamhutchings
 * @since pre4
 */
public abstract class Structure extends QObject {

	public Structure(QApplication app) {
		super(app);
	}

	/**
	 * The city that owns this. For cities, it's
	 * the capital city. For capital cities, it's
	 * themselves.
	 */
	public Settlement owner;
	
	/**
	 * What happens when the next turn button is clicked
	 * to land on the owner's turn.
	 */
	public abstract void action();
	
}
