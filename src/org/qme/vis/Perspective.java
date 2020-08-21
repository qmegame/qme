package org.qme.vis;

import org.qme.util.QDimension;
import org.qme.util.QMatrix2;

/**
 * This class provides static utilities for converting the
 * coordinates of the world (tiles, units, etc.) to the
 * screen coordinate, through the 3/4 perspective.
 * @author adamhutchings
 * @since pre0
 */
public class Perspective {
	
	public static final int TILE_SIZE = 150;
	
	/**
	 * An angle of 45 would just be straight overhead - let's say 30.
	 */
	public static final int PERSPECTIVE_ANGLE = 30;
	
	/**
	 * This converts a 2D position on the screen into
	 * a set of coordinates in the world.
	 */
	public static QDimension<Float> screenToWorld(QDimension<Float> screen) {
		
		QDimension<Float> result = new QDimension<>(0.0f, 0.0f);
		
		result.x += (float) (screen.x * Math.cos(PERSPECTIVE_ANGLE));
		result.y += (float) (screen.x * Math.sin(PERSPECTIVE_ANGLE));
		
		result.x += (float) (screen.y * Math.cos(PERSPECTIVE_ANGLE));
		result.y -= (float) (screen.y * Math.sin(PERSPECTIVE_ANGLE));
		
		result.x *= TILE_SIZE;
		result.y *= TILE_SIZE;
		
		return result;
		
	}
	
	/**
	 * This converts a 2D position in world coordinates
	 * into a set of coordinates on the screen.
	 */
	public static QDimension<Float> worldToScreen(QDimension<Float> world) {
		
		double alpha = PERSPECTIVE_ANGLE * 360 / (2 * Math.PI);
		double beta  = PERSPECTIVE_ANGLE * 360 / (2 * Math.PI);
		
		QMatrix2 matrix = new QMatrix2(
			
			(float) - Math.sin (beta)       ,
			(float) - Math.cos (beta)       ,
			(float) - Math.cos(alpha)       ,
			(float)   Math.sin(alpha)
				
		);
		
		QDimension<Float> result = matrix.multiply(world);
		
		result.x /= (float) (- Math.sin(alpha + beta));
		result.y /= (float) (- Math.sin(alpha + beta));
		
		result.x /= TILE_SIZE;
		result.y /= TILE_SIZE;
		
		return result;
		
	}

}
