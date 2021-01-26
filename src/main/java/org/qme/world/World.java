package org.qme.world;

import org.qme.world.gen.WorldGenerator;

/**
 * Represents a world in the game, with tiles.
 * @author adamhutchings
 * @since preA
 */
public class World implements Runnable {

	/**
	 * The size of the world
	 */
	public static final int WORLD_SIZE = 500;
	
	/**
	 * All tiles in the world.
	 */
	private Tile[][] tiles;

	/**
	 * Set up for Multithreading
	 */
	private Thread t;
	private String threadName;


	public World(String name) {
		threadName = name;
		System.out.println("Creating " +  threadName );
	}
	/**
	 * Create the world by initializing all tiles with appropriate types.
	 */
	@Override
	public void run() {
		tiles = new Tile[WORLD_SIZE][WORLD_SIZE];
		TileType[][] typelist = WorldGenerator.generateWorldMap(WORLD_SIZE);
		try {
			for (int i = 0; i < WORLD_SIZE; i++) {
				for (int j = 0; j < WORLD_SIZE; j++) {
					tiles[i][j] = new Tile(i, j, typelist[i][j]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thread " +  threadName + " interrupted.");
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start () {
		System.out.println("Starting " +  threadName );
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
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
