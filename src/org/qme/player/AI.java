package org.qme.player;

import org.qme.util.NameGen;

/**
 * This class is an AI
 * @author S-Mackenzie1678
 * @since pre1
 */
public class AI extends Player {
	public AI() {
		super(false, "");	// Why u no let me refer to method
		this.name = NameGen.namer();
	}
}