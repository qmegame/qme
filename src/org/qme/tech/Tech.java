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
	public static final Tech QUADRIREME_TECH         = new Tech(TRIREME_TECH);
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

}
