package org.qme;

import org.qme.client.Application;

/**
 * Holds the main method to launch the application.
 * @author adamhutchings
 * @since 0.1.0
 */
public class Main {
	
	/**
	 * Main method for the whole application.
	 * @param args unused command-line arguments
	 */
	public static void main(String[] args) {
		Application.app.mainloop();
	}

}
