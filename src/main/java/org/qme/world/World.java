package org.qme.world;

import org.qme.world.gen.WorldGenerator;

/**
 * Represents a world in the game, with tiles.
 * @author adamhutchings
 * @since 0.1.0
 */
public class World {
	
	/**
	 * Size of the world
	 */
	public static final int WORLD_SIZE = 25;

	private final Tile[][] tiles;
	
	/**
	 * Create the world by initializing all tiles with appropriate types.
	 */
	public World() {
		
		tiles = new Tile[WORLD_SIZE][WORLD_SIZE];
		TileType[][] typelist = WorldGenerator.generateWorldMap(WORLD_SIZE);
		for (int i = 0; i < WORLD_SIZE; i++) {
			for (int j = 0; j < WORLD_SIZE; j++) {
				tiles[i][j] = new Tile(i, j, typelist[i][j]);
			}
		}
		
	}
	
	/**
	 * Get all the tiles.
	 * @return the tile map for rendering
	 */
	public Tile[][] getTiles() {
		return tiles;
	}

}
