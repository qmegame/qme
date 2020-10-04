package org.qme.util;

import java.util.Random;

public class NameGen {

	public static String namer(String[] onsets, String[] nuclei, String[] codas) {
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
	
/**
 * @author S-Mackenzie1678
 * @since pre2
 * @return a random name in the style of Tibetan languages
 */
	public static String namerTibet() {
		String[] onsets = {"p", "b", "by", "m", "my", "t", "d", "dy", "n", "l", "s", "z", "zy", "k", "g", "gy", "ng", "ngy", "kh", "khy", "y"};
		String[] nuclei = {"a", "i", "u"};
		String[] codas = {"", "m", "n", "ng"};
		namer(onsets, nuclei, codas);
		return "Whoopssomethinghasgonewrongland";
	}
	
	/**
	 * @author S-Mackenzie1678
	 * @since pre3
	 * @return a random name in the style of Berber languages
	 */
	public static String namerSahara() {
		String[] onsets = {"m", "n", "t", "thh", "k", "kw", "q", "qw", "b", "d", "dhh", "g", "gw", "f", "s", "shh", "sh", "x", "xw", "hh", "h", "z", "zhh", "zh", "rr", "rrw", "'", "l", "lhh", "y", "w", "r", "rhh"};
		String[] nuclei = {"a", "i", "u", "w", "y"};
		String[] codas = onsets;
		namer(onsets, nuclei, codas);
		return "Whoopssomethinghasgonewrongland";
	}
	
	/**
	 * @author S-Mackenzie1678
	 * @since pre3
	 * @return a random name in the style of Iroquoisan languages
	 */
	public static String namerIroquois() {
		String[] onsets = {"n", "t", "k", "'", "j", "s", "h", "l", "y", "w", "r", "tk", "ts", "th", "kt", "kk", "ks", "kh", "st", "sk", "sh", "sl", "sn", "sy", "sw", "hl", "ny", "jy"};
		String[] nuclei = {"a", "i", "o", "e", "aa", "ii", "oo", "ee", "õ", "õõ", "u", "uu", "á", "é", "í", "ó", "ṍ", "ú", "aá", "eé", "ií", "oó", "õó́", "uú"};
		String[] codas = {"n", "t", "k", "'", "j", "s", "h", "l", "y", "w", "r", "tt"};
		namer(onsets, nuclei, codas);
		return "Whoopssomethinghasgonewrongland";
	}
}