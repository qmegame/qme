package org.qme.world;

import org.qme.main.QApplication;
import org.qme.world.gen.SettlementGen;
import org.qme.world.gen.WorldGenNeo;
import org.qme.world.gen.WorldGenRandom;
import org.qme.world.gen.WorldGenSquigglyBlob;

/**
 * Represents a 2D array of tiles
 * @author adamhutchings
 * @author S-Mackenzie1678
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
	public World(QApplication a, int x, int y) {
		
		xDimension = x;
		yDimension = y;
		
		TileType[][] map;
		
		map = WorldGenSquigglyBlob.newWorldMapRandom(x, y, 7 /*magic*/);
		
		tiles = new Tile[x][y];
		
		// Initialize all of the tiles
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				
				tiles[i][j] = new Tile(a, i, j);
				
				tiles[i][j].setType(map[i][j]);
				
			}
		}
		
		SettlementGen.settlementGive(this);
	}

}
