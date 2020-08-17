package org.qme.world;

import org.qme.main.QApplication;

/**
 * Represents a 2D array of tiles
 * @author adamhutchings
 * @since pre0
 */
public class World {
	
	/**
	 * How long the x axis is
	 */
	public int xDimension;
	
	/**
	 * How long the y axis is
	 */
	public int yDimension;
	
	/**
	 * The actual tiles in the world
	 */
	public Tile[][] tiles;
	
	/**
	 * Generates a world map
	 * @author adamhutchings
	 * @since pre0
	 * @param x the x size of the world
	 * @param y the y size of the world
	 */
	public World(QApplication a, int x, int y, boolean random) {
		
		xDimension = x;
		yDimension = y;
		
		TileType[][] map;
		
		if (random) {
			map = WorldGen.newWorldMapRandom(x, y);
		} else {
			map = WorldGen.newWorldMapSeeded(x, y);
		}
		
		tiles = new Tile[x][y];
		
		// Initialize all of the tiles
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				
				tiles[i][j] = new Tile(a, i, j);
				
				tiles[i][j].setType(map[i][j]);
				
			}
		}
		
	}

}
