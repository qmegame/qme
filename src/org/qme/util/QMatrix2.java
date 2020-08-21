package org.qme.util;

/**
 * A utility class for a matrix.
 * @author adamhutchings
 * @since pre1
 */
public class QMatrix2 {
	
	/**
	 * Literally what's in the matrix
	 */
	private float contents[][];
	
	public QMatrix2(float a, float b, float c, float d) {
		
		contents = new float[2][2];
		
		// Assigning values
		contents[0][0] = a;
		contents[0][1] = b;
		contents[1][0] = c;
		contents[1][1] = d;
		
	}
	
	public float determinant() {
		return (contents[0][0] * contents[1][1]) - (contents[0][1] * contents[1][0]);
	}
	
	public QDimension<Float> multiply(QDimension<Float> vec) {
		return new QDimension<Float>(
			(contents[0][0] * vec.x) + (contents[0][1] * vec.y),
			(contents[1][0] * vec.x) + (contents[1][1] * vec.y)
		);
	}

}
