package org.qme.client;

import org.qme.client.vis.WindowManager;
import org.qme.world.World;

/**
 * The "controller", so to speak, of all events. It also helps to validate
 * requests from the player or the game, because instead of acting on anything
 * directly, all UI components (should) send in a request to the global
 * Application instance. For when QME goes online at some later date (hopefully)
 * this model will serve better, because request objects can be sent to a remote
 * location more easily.
 * @author adamhutchings
 * @since preA
 */
public final class Application {

	/**
	 * The constructor is private. Only one instance allowed.
	 */
	private Application() {
		new World();
	}

	/**
	 * The global instance of application.
	 */
	public static final Application app = new Application();
	
	/**
	 * Run the application forever (or until an exit request is sent)
	 */
	public void mainloop() {
		
		while (WindowManager.shouldBeOpen()) {
			
			WindowManager.repaint();
		
		}
		
	}

}
