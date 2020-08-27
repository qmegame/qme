package org.qme.player;

import java.util.Random;

/**
 * This class is an AI
 * @author S-Mackenzie1678
 * @since pre1
 */
public class AI extends Player {
	public AI() {
		super(false, "");	// Why u no let me refer to method
		this.name = namer();
	}
	
	/**
	 * @author S-Mackenzie1678
	 * @since pre2
	 * @return a random name
	 */
	String namer() {
		String name = "";
		Random rand = new Random();
		for(int i = 0; i < 2; i++) {
		// Syllable onset
			int onset = rand.nextInt(21);
			if(onset == 0) { name += "p"; }
			if(onset == 1) { name += "b"; }
			if(onset == 2) { name += "by"; }
			if(onset == 3) { name += "m"; }
			if(onset == 4) { name += "my"; }
			if(onset == 5) { name += "t"; }
			if(onset == 6) { name += "d"; }
			if(onset == 7) { name += "n"; }
			if(onset == 8) { name += "l"; }
			if(onset == 9) { name += "dy"; }
			if(onset == 10) { name += "s"; }
			if(onset == 11) { name += "z"; }
			if(onset == 12) { name += "zy"; }
			if(onset == 13) { name += "k"; }
			if(onset == 14) { name += "g"; }
			if(onset == 15) { name += "gy"; }
			if(onset == 16) { name += "ng"; }
			if(onset == 17) { name += "ngy"; }
			if(onset == 18) { name += "kh"; }
			if(onset == 19) { name += "khy"; }
			if(onset == 20) { name += "y"; }
			// Syllable nucleus
			onset = rand.nextInt(3);
			if(onset == 0) { name += "a"; }
			if(onset == 1) { name += "i"; }
			if(onset == 2) { name += "u"; }
			// Syllable coda
			onset = rand.nextInt(4);
			if(onset == 0) { name += ""; }
			if(onset == 1) { name += "n"; }
			if(onset == 2) { name += "m"; }
			if(onset == 3) { name += "ng"; }
		}
		return name;
	}
}