package org.qme.util;

/**
 * This exists for the purpose of keeping all
 * global variables in one place.
 * @author adamhutchings
 * @since pre3
 */
public class GlobalConstants {
	
	/**
	 * This represents how often the screen is reloaded.
	 * Moved from org.qme.main in development for pre3.
	 * @author adamhutchings
	 * @since pre0
	 */
	public static final int FRAMERATE = 30;
	
	/**
	 * Whether tooltips are on.
	 * @since pre3
	 */
	public static boolean TOOLTIPS = true;
	
	/**
	 * For how flat the final result should look.
	 */
	public static float SQUASH_FACTOR = 2f;
	
	/**
	 * The size that each tile is rendered as. Kind of.
	 */
	public static final int TILE_SIZE = 150;
	
	/**
	 * How fast the user "scrolls".
	 */
	public static int SCROLL_SPEED = 20;
	
	/**
	 * The default width of the screen generated.
	 * @author adamhutchings
	 * @since pre0
	 */
	public static final int SCREEN_WIDTH  = 800;
	
	/**
	 * The default height of the screen generated.
	 * @author adamhutchings
	 * @since pre0
	 */
	public static final int SCREEN_HEIGHT = 600;

}
