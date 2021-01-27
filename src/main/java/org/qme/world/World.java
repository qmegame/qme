package org.qme.world;

import org.qme.utils.Multithreading;
import org.qme.world.gen.WorldGenerator;

/**
 * Represents a world in the game, with tiles.
 * @author adamhutchings
 * @since preA
 */
public class World {

	Multithreading r1 = new Multithreading("Thread-1");
	Multithreading r2 = new Multithreading("Thread-2");


	/**
	 * The size of the world
	 */
	public static final int WORLD_SIZE = 25;

	/**
	 * All tiles in the world.
	 */
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
