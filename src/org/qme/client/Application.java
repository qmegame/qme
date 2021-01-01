package org.qme.client;

import java.util.ArrayList;

import org.qme.client.vis.Renderable;
import org.qme.world.Tile;
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
	 * The client instance.
	 */
	private final Client client;
	
	/**
	 * What world is showing - could be null if there's no world.
	 */
	private World world;
	
	/**
	 * The constructor is private. Only one instance allowed.
	 */
	private Application() {
		client = new Client();
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
			
			client.render();
		
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
		case GENERATE_NEW_WORLD:
			this.world = new World();
			for (Tile[] strip : this.world.getTiles()) {
				// Register all tiles
				this.client.registerDrawables(strip);
			}
			break;
		}
		return true;
	}
	
	/**
	 * Whether a world exists.
	 * @return the status
	 */
	public boolean worldExists() {
		return this.world != null;
	}
	
	/**
	 * Get all tiles (wrapper)
	 * @return all tiles
	 */
	public Tile[][] getWorldTiles() {
		return this.world.getTiles();
	}
	
	/**
	 * Get all objects to render. Wrapper method.
	 * @return the objects
	 */
	public ArrayList<Renderable> getRenderables() {
		return client.getRenderables();
	}

}
