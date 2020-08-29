package org.qme.util;

import java.util.Random;

public class NameGen {

/**
 * @author S-Mackenzie1678
 * @since pre2
 * @return a random name
 */
	public static String namer() {
		String name = "";
		Random rand = new Random();
		// First syllable
		// Syllable onset
		int onset = rand.nextInt(21);
		if(onset == 0) { name += "P"; }
		if(onset == 1) { name += "B"; }
		if(onset == 2) { name += "By"; }
		if(onset == 3) { name += "M"; }
		if(onset == 4) { name += "My"; }
		if(onset == 5) { name += "T"; }
		if(onset == 6) { name += "D"; }
		if(onset == 7) { name += "N"; }
		if(onset == 8) { name += "L"; }
		if(onset == 9) { name += "Dy"; }
		if(onset == 10) { name += "S"; }
		if(onset == 11) { name += "Z"; }
		if(onset == 12) { name += "Zy"; }
		if(onset == 13) { name += "K"; }
		if(onset == 14) { name += "G"; }
		if(onset == 15) { name += "Gy"; }
		if(onset == 16) { name += "Ng"; }
		if(onset == 17) { name += "Ngy"; }
		if(onset == 18) { name += "Kh"; }
		if(onset == 19) { name += "Khy"; }
		if(onset == 20) { name += "Y"; }
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
		// Additional Syllables
		for(int i = 0; i < rand.nextInt(4); i++) {
		// Syllable onset
			onset = rand.nextInt(21);
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