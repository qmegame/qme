package org.qme.client;

import org.qme.utils.Multithreading;
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
	 * Create New Threads
	 */
	private Multithreading R1 = new Multithreading("Thread 1");
	private Multithreading R2 = new Multithreading("Thread 2");

	/**
	 * The constructor is private. Only one instance allowed.
	 */
	private Application() {
		World R1 = new World("Thread-1");
		R1.start();

		World R2 = new World("Thread-2");
		R2.start();
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
