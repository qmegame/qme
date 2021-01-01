package org.qme.world;

/**
 * Represents a world in the game, with tiles.
 * @author adamhutchings
 * @since preA
 */
public class World {
	
	/**
	 * The size of the world
	 */
	private static final int WORLD_SIZE = 25;
	
	/**
	 * All tiles in the world.
	 */
	private final Tile[][] tiles;
	
	/**
	 * Create the world by initializing all tiles wiith appropriate types.
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

}