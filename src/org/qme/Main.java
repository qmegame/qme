package org.qme;

import org.qme.vis.WindowManager;

/**
 * Holds the main method to launch the application.
 * @author adamhutchings
 * @since preA
 */
public class Main {
	
	/**
	 * Main method for the whole application.
	 * @param args unused command-line arguments
	 */
	public static void main(String[] args) {
		while (WindowManager.open()) {
			WindowManager.repaint();
		}
		
	}

}
