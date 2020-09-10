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
	
	/**
	 * The size that each tile is rendered as. Kind of.
	 */
	public static final int TILE_SIZE = 150;
	
	/**
	 * sqrt(2) is no gap, apparently.
	 */
	public static final float TILE_GAP_FACTOR = 1.5f;
	
	/**
	 * It appears this merely rotates.
	 */
	public static final int PERSPECTIVE_ANGLE = 45;
	
	/**
	 * For how flat the final result should look.
	 */
	public static final float SQUASH_FACTOR = 2f;
	
	/**
	 * This converts a 2D position on the screen into
	 * a set of coordinates in the world.
	 */
	public static QDimension<Float> screenToWorld(QDimension<Float> screen) {
		
		QDimension<Float> result = new QDimension<>(0.0f, 0.0f);
		
		result.x += (float) (screen.x * Math.cos(PERSPECTIVE_ANGLE * (2 * Math.PI) / 360));
		result.y += (float) (screen.x * Math.sin(PERSPECTIVE_ANGLE * (2 * Math.PI) / 360));
		
		result.x += (float) (screen.y * Math.cos(PERSPECTIVE_ANGLE * (2 * Math.PI) / 360));
		result.y -= (float) (screen.y * Math.sin(PERSPECTIVE_ANGLE * (2 * Math.PI) / 360));
		
		result.x *= TILE_SIZE;
		result.y *= TILE_SIZE;
		
		return result;
		
	}
	
	/**
	 * This converts a 2D position in world coordinates
	 * into a set of coordinates on the screen.
	 */
	public static QDimension<Float> worldToScreen(QDimension<Float> world) {
		
		double alpha = PERSPECTIVE_ANGLE * (2 * Math.PI) / 360;
		double beta  = PERSPECTIVE_ANGLE * (2 * Math.PI) / 360;
		
		QMatrix2 matrix = new QMatrix2(
			
			(float) - Math.sin (beta)       ,
			(float) - Math.cos (beta)       ,
			(float) - Math.cos(alpha)       ,
			(float)   Math.sin(alpha)
				
		);
		
		QDimension<Float> result = matrix.multiply(world);
		
		result.x /= (float) (- Math.sin(alpha + beta));
		result.y /= (float) (- Math.sin(alpha + beta));
		
		result.x *= TILE_SIZE;
		result.y *= TILE_SIZE;
		
		result.y /= SQUASH_FACTOR;
		
		return result;
		
	}

}
