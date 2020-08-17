package org.qme.util;

/**
 * This is just to represent something like
 * a java Swing Dimension, but parametrized.
 * @author adamhutchings
 * @since pre0
 * @param <T> the type of the dimension
 */
public class QDimension<T> {

	public T x;
	public T y;
	
	/**
	 * Just a plain old data thing.
	 */
	public QDimension(T x, T y) {
		
		this.x = x;
		this.y = y;
		
	}
	
}
