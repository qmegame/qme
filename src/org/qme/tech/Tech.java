package org.qme.tech;

/**
 * Just for technologies.
 * @author adamhutchings
 * @since pre2
 */
public class Tech {
	
	/**
	 * A blank technology. It's wonderful.
	 */
	public static final Tech NULL_TECH = new Tech();
	
	public Tech parent;
	
	private Tech(Tech p) {
		parent = p;
	}
	
	private Tech() {
		// This recursion is questionable.
		this(NULL_TECH);
	}
	
	/**
	 * All the technologies. The constructor takes in the
	 * parent - NULL_TECH means no parent technology.
	 */
	public static final Tech WHEEL_TECH            = new Tech(NULL_TECH);
	public static final Tech AGRICULTURE_TECH      = new Tech(NULL_TECH);
	public static final Tech WRITING_TECH          = new Tech(NULL_TECH);
	public static final Tech ANIMAL_HUSBANDRY_TECH = new Tech(NULL_TECH);
	public static final Tech WOOD_CHOPPING_TECH    = new Tech(WHEEL_TECH);
	public static final Tech ARCHERY_TECH          = new Tech(ANIMAL_HUSBANDRY_TECH);
	public static final Tech POTTERY_TECH          = new Tech(AGRICULTURE_TECH);
	public static final Tech ASTROLOGY_TECH        = new Tech(WRITING_TECH);
	public static final Tech FISHING_NET_TECH      = new Tech(NULL_TECH);
	public static final Tech MASONRY_TECH          = new Tech(WOOD_CHOPPING_TECH);
	public static final Tech BRONZE_WORKING_TECH   = new Tech(ARCHERY_TECH);
	public static final Tech IRRIGATION_TECH       = new Tech(POTTERY_TECH);
	public static final Tech MEDICINE_TECH         = new Tech(ASTROLOGY_TECH);
	public static final Tech SAILING_TECH          = new Tech(FISHING_NET_TECH);
	public static final Tech MINING_TECH           = new Tech(MASONRY_TECH);
	public static final Tech HORSEBACK_RIDING_TECH = new Tech(BRONZE_WORKING_TECH);
	public static final Tech FERMENTATION_TECH     = new Tech(IRRIGATION_TECH);
	public static final Tech MATHEMATICS_TECH      = new Tech(MEDICINE_TECH);
	public static final Tech TRIREME_TECH          = new Tech(SAILING_TECH);
	public static final Tech QUARRYING_TECH        = new Tech(MINING_TECH);
	public static final Tech SMELTING_TECH         = new Tech(HORSEBACK_RIDING_TECH);
	public static final Tech CURRENCY_TECH         = new Tech(FERMENTATION_TECH);
	public static final Tech EDUCATION_TECH        = new Tech(MATHEMATICS_TECH);
	public static final Tech QUADRIREME_TECH       = new Tech(TRIREME_TECH);
	public static final Tech PULLEY_TECH           = new Tech(MINING_TECH);
	public static final Tech IRON_WORKING_TECH     = new Tech(SMELTING_TECH);
	public static final Tech SCYTHE_TECH           = new Tech(CURRENCY_TECH);
	public static final Tech CHEMISTRY_TECH        = new Tech(EDUCATION_TECH);
	public static final Tech CELESTIAL_TECH        = new Tech(QUADRIREME_TECH);
	public static final Tech CRANE_TECH            = new Tech(PULLEY_TECH);
	public static final Tech ENGINEERING_TECH      = new Tech(IRON_WORKING_TECH);
	public static final Tech STONEWORKING_TECH     = new Tech(SCYTHE_TECH);
	public static final Tech ORGANIZED_ED_TECH     = new Tech(CHEMISTRY_TECH);
	public static final Tech GALLEY_TECH           = new Tech(CELESTIAL_TECH);

	public String getName() {
		     if (this == NULL_TECH)             return "None";
		else if (this == WHEEL_TECH)            return "Wheel";
		else if (this == AGRICULTURE_TECH)      return "Agriculture";
		else if (this == WRITING_TECH)          return "Writin'";
		else if (this == ANIMAL_HUSBANDRY_TECH) return "Animal Husbandry";
		else if (this == WOOD_CHOPPING_TECH)    return "Wood Choppin'";
		else if (this == ARCHERY_TECH)          return "Archery";
		else if (this == POTTERY_TECH)          return "Pottery";
		else if (this == ASTROLOGY_TECH)        return "Astrology";
		else if (this == FISHING_NET_TECH)      return "Fishin' Nets";
		else if (this == MASONRY_TECH)          return "Masonry";
		else if (this == BRONZE_WORKING_TECH)   return "Bronze Workin'";
		else if (this == IRRIGATION_TECH)       return "Irrigation";
		else if (this == MEDICINE_TECH)         return "Medicine";
		else if (this == SAILING_TECH)          return "Sailin'";
		else if (this == MINING_TECH)           return "Minin'";
		else if (this == HORSEBACK_RIDING_TECH) return "Horse Ridin'";
		else if (this == FERMENTATION_TECH)     return "Fermentation";
		else if (this == MATHEMATICS_TECH)      return "Mathematics";
		else if (this == TRIREME_TECH)          return "Trireme";
		else if (this == QUARRYING_TECH)        return "Quarryin'";
		else if (this == SMELTING_TECH)         return "Smeltin'";
		else if (this == CURRENCY_TECH)         return "Currency";
		else if (this == EDUCATION_TECH)        return "Education";
		else if (this == QUADRIREME_TECH)       return "Quadriremes";
		else if (this == PULLEY_TECH)           return "Pulley";
		else if (this == IRON_WORKING_TECH)     return "Ironworkin'";
		else if (this == SCYTHE_TECH)           return "Scythe";
		else if (this == CHEMISTRY_TECH)        return "Chemistry";
		else if (this == CELESTIAL_TECH)        return "Astronomy";
		else if (this == CRANE_TECH)            return "Cranes";
		else if (this == ENGINEERING_TECH)      return "Engineerin'";
		else if (this == STONEWORKING_TECH)     return "Stoneworkin'";
		else if (this == ORGANIZED_ED_TECH)     return "Organized Ed";
		else if (this == GALLEY_TECH)           return "Galleys";
		else                                    return "Error: unrecognized tech";
	}

}
