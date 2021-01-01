package org.qme.client;

import java.util.ArrayList;

import org.qme.client.Request.RequestType;
import org.qme.client.vis.Renderable;
import org.qme.client.vis.WindowManager;
import org.qme.world.Tile;

/**
 * Represents an instance of the client running the game. The game starting
 * generates one of these always, whereas the main Application instance may
 * actually be acting in place of another one.
 * @author adamhutchings
 * @since preA
 */
public final class Client {
	
	private final ArrayList<Renderable> renderables
		= new ArrayList<>();
	
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
			Tile[][] tiles = Application.app.getWorldTiles();
			for (Tile[] strip : tiles) {
				for (Tile tile : strip) {
					tile.draw();
				}
			}
		}
		
	}
	
	/**
	 * Register all objects.
	 * @param renderables the objects
	 */
	public void registerDrawables(Renderable[] renderables) {
		for (Renderable e : renderables) {
			this.renderables.add(e);
		}
	}
	
	/**
	 * Pass all objects to WindowManager to draw.
	 * @return the objects to draw
	 */
	public ArrayList<Renderable> getRenderables() {
		return renderables;
	}

}
