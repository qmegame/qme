package org.qme.vis;

import org.qme.util.QDimension;

/**
 * This class provides static utilities for converting the
 * coordinates of the world (tiles, units, etc.) to the
 * screen coordinate, through the 3/4 perspective.
 * @author adamhutchings
 * @since pre0
 */
public class Perspective {
	
	public static final int TILE_SIZE = 100;
	
	/**
	 * An angle of 45 would just be straight overhead - let's say 30.
	 */
	public static final int PERSPECTIVE_ANGLE = 30;
	
	/**
	 * This converts a 2D position on the screen into
	 * a set of coordinates in the world.
	 */
	public static QDimension<Float> screenToWorld(QDimension<Float> screen) {
		return new QDimension<Float>(0.0f, 0.0f);
	}
	
	/**
	 * This converts a 2D position in world coordinates
	 * into a set of coordinates on the screen.
	 */
	public static QDimension<Float> worldToScreen(QDimension<Float> world) {
		return new QDimension<Float>(0.0f, 0.0f);
	}

}
