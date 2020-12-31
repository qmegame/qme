package org.qme.world;

import static org.lwjgl.opengl.GL11.*;

import org.qme.client.Application;
import org.qme.vis.Renderable;
import org.qme.vis.Renderer;
import org.qme.vis.WindowManager;

/**
 * Represents a single tile in the world. As with other objects, rendering and
 * bounds detection code will not be in this class, and rather with other
 * rendering / bounds detection code, etc.
 * @author adamhutchings
 * @since preA
 */
public class Tile implements Renderable {
	
	/**
	 * The x coordinate of the tile.
	 */
	public final int x;
	
	/**
	 * The y coordinate of the tile.
	 */
	public final int y;
	
	/**
	 * Constructor - initialize with coordinates
	 * @param x the input x coordinate
	 * @param y the input y coordinate
	 */
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		Application.app.addObject(this);
	}

	/**
	 * Draw a red square (for now)
	 */
	@Override
	public void draw() {
		glColor4f(0.0f, 0.0f, 0.0f, 0.0f);
		Renderer.renderQuad(
			WindowManager.size() / 2,
			WindowManager.size() / 2,
			200
		);
	}

}
