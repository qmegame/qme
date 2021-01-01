package org.qme.client;

import org.qme.client.Request.RequestType;
import org.qme.client.vis.WindowManager;

/**
 * Represents an instance of the client running the game. The game starting
 * generates one of these always, whereas the main Application instance may
 * actually be acting in place of another one.
 * @author adamhutchings
 * @since preA
 */
public final class Client {
	
	/**
	 * Initialize the window.
	 */
	public Client() {
		this.init();
	}
	
	/**
	 * Initialize everything.
	 */
	private void init() {
		Request.addRequest(new Request(RequestType.GENERATE_NEW_WORLD));
	}
	
	/**
	 * Render the screen.
	 */
	public void render() {
		WindowManager.repaint();
	}
	
	/**
	 * Draw everything (in order from back to front)
	 */
	public void drawAll() {
		
		if (Application.app.worldExists()) {
			
		}
		
	}

}
