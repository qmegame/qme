package org.qme.vis;

import org.qme.main.QApplication;
import org.qme.main.QObject;

/**
 * This represents any sort of animation. How it
 * works is it has a counter variable, and every update
 * it calls a function that updates a certain object
 * according to the counter variable.
 * @author adamhutchings
 * @since pre0
 */
public abstract class QAnimation extends QObject {
	
	/**
	 * The object this animation acts on
	 */
	private QObject modified;
	
	/**
	 * How long this object has been alive for, in
	 * frames (refresh cycles)
	 */
	private int count;
	
	/**
	 * How long this object can be alive, in
	 * frames (refresh cycles)
	 */
	private int lifetime;
	
	/**
	 * Calls the super constructor and initializes
	 * lifetime and count
	 * @param app the QApplication instance to bind to 
	 * @param life the 
	 */
	public QAnimation(QApplication app, int life) {
		
		super(app);
		
		lifetime = life;
		count = 0;
		
	}
	
	/**
	 * This gets called to update the object.
	 */
	abstract protected void animate(int c, QObject q);

	@Override
	/**
	 * Dispatches the appropriate animate call, and
	 * also deletes the object (or readies it for GC)
	 * if necessary.
	 * @author adamhutchings
	 * @since pre0
	 */
	public void update(QApplication app) {
		
		if (count >= lifetime) {
			
			// I hope this is the only reference necessary to remove.
			app.objects.remove(this);
			return;
			
		}
		
		animate(count, modified);
		count++;
		
	}

}
