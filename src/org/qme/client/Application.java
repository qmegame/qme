package org.qme.client;

import org.qme.vis.WindowManager;

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
		
	}
	
	/**
	 * The global instance of application.
	 */
	public static final Application app = new Application();
	
	/**
	 * Run the application forever (or until an exit request is sent)
	 */
	public void mainloop() {
		
		while (true) {
		
			// Handle all requests
			Request request;
			while ( (request = Request.takeRequest()) != null ) {
				if (!this.handleRequest(request))  {
					// The game should exit now
					return;
				}
			}
		
			WindowManager.repaint();
		
		}
		
	}
	
	/**
	 * Handle a single given request, while also returning whether the game
	 * should keep running.
	 * @param request the request to handle
	 * @return whether the game should keep running
	 */
	private boolean handleRequest(Request request) {
		switch (request.type()) {
		case EXIT:
			return false;
		}
		return true;
	}

}
