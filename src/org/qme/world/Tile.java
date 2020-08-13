package org.qme.world;

/**
 * A tile in the world. For now, just has a type
 * and a location. Nothing big.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.world.TileType
 */
public class Tile {
	
	// Location
	int x, y;
	
	// Type
	private TileType type;
	
	/**
	 * Simple generation with coordinates.
	 * @param x x-coordinate of tile.
	 * @param y y-coordinate of tile.
	 */
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Auto-generated method stub.
	 * @return tile type
	 */
	public TileType getType() {
		return type;
	}

	/**
	 * Auto-generated method stub.
	 * @param type new tile type
	 */
	public void setType(TileType type) {
		this.type = type;
	}

}
