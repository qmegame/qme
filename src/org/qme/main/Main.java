package org.qme.main;

import java.util.concurrent.TimeUnit;

import org.qme.vis.ui.QButton;

/**
 * Holds the main method and the FRAMERATE constant.
 * FRAMERATE may be moved to a different place later.
 * @author adamhutchings
 * @since pre0
 */
public class Main {
	
	/**
	 * This represents how often the screen is reloaded.
	 * Only in this class temporarily - may be moved.
	 * @author adamhutchings
	 * @since pre0
	 */
	public static final int FRAMERATE = 30;
	
	/**
	 * Creates a new QApplication instance and reloads it.
	 * @author adamhutchings
	 * @since pre0
	 * @see org.qme.main.QApplication
	 * @param args - command line arguments: unused for now
	 * @throws InterruptedException - from the sleep call
	 */
	public static void main(String[] args) throws InterruptedException {
		
		QApplication app = new QApplication();
		
		// Main loop time
		while (true) {
			
			// Wait for some time
			TimeUnit.MILLISECONDS.sleep(1000 / FRAMERATE);
			
			app.reload();
		}
		
	}

}
