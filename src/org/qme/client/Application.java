package org.qme.client;

import java.util.ArrayList;

import org.qme.io.Logger;
import org.qme.io.Severity;
import org.qme.vis.Renderable;
import org.qme.vis.WindowManager;
import org.qme.world.Tile;

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
	 * All objects that can be rendered.
	 */
	private final ArrayList<Renderable> renderables
		= new ArrayList<>();
	
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
		
		new Tile(0, 0);
		
		while (true) {
		
			// Handle all requests
			Request request;
			while ( (request = Request.takeRequest()) != null ) {
				if (!this.handleRequest(request))  {
					// The game should exit now
					return;
				}
			}
		
			WindowManager.repaint(renderables);
		
		}
		
	}
	
	/**
	 * Handle a single given request, while also returning whether the game
	 * should keep running.
	 * @param request the request to handle
	 * @return whether the game should keep running
	 */
	private boolean handleRequest(Request request) {
		Logger.log(
				"Handling request of type: " + request.type().name()
				, Severity.NORMAL
		);
		switch (request.type()) {
		case EXIT:
			return false;
		}
		Logger.log(
				"Finished handling request without request to exit.",
				Severity.NORMAL
		);
		return true;
	}
	
	/**
	 * Add an object to the list.
	 * @param r the object to render
	 */
	public void addObject(Renderable r) {
		renderables.add(r);
	}

}
