package org.qme.util;

import java.util.Random;

public class NameGen {

/**
 * @author S-Mackenzie1678
 * @since pre2
 * @return a random name
 */
	public static String namer() {
		String[] onsets = {"p", "b", "by", "m", "my", "t", "d", "dy", "n", "l", "s", "z", "zy", "k", "g", "gy", "ng", "ngy", "kh", "khy", "y"};
		String[] nuclei = {"a", "i", "u"};
		String[] codas = {"", "m", "n", "ng"};
		String name = "";
		Random rand = new Random();
		for (int i = 0; (i < rand.nextInt(3) + 1); i++) {
			// Onsets (capitalizing is hard)
			if(i == 0) {
				int onset = rand.nextInt(onsets.length);
				name += Character.toUpperCase(onsets[onset].charAt(0));
				if(onsets[onset].length() > 1) {
					name += onsets[onset].substring(1);
				}
			} else {
				name += onsets[rand.nextInt(onsets.length)];
			}
			// Nuclei
			name += nuclei[rand.nextInt(nuclei.length)];
			// Codas
			name += codas[rand.nextInt(codas.length)];
		}
		return name;
	}
}