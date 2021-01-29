package org.qme.world;

import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.Renderable;
import org.qme.client.vis.WindowManager;

/**
 * Represents a single tile in the world. As with other objects, rendering and
 * bounds detection code will not be in this class, and rather with other
 * rendering / bounds detection code, etc.
 * @author adamhutchings
 * @since 0.1.0
 */
public class Tile implements Renderable {

	public final int x;
	public final int y;
	public final TileType type;
	
	/**
	 * Creates a new instance of a renderable Tile
	 * @param x the x coordinate of the tile in relation to the tile plane (Not in pixels)
	 * @param y the y coordinate of the tile in relation to the tile plane (Not in pixels)
	 * @param type the type of this tile
	 */
	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		WindowManager.addObject(this);
	}

	@Override
	public void draw() {
		RenderMaster.drawTile(this);
	}

}
