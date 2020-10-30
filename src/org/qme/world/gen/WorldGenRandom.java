package org.qme.world.gen;

import java.util.Random;

import org.qme.world.TileType;

/**
 * A completely random world
 * @author S-Mackenzie1678
 * @since pre4
 */
public class WorldGenRandom {
	public static TileType[][] newWorldMapRandom(int xSize, int ySize) {
		TileType[][] world = new TileType[xSize][ySize];
		for(int i = 0; i < xSize; i++) {
			for(int j = 0; j < ySize; j++) {
				Random rand = new Random();
				int type = rand.nextInt(8);
				if(type == 0) {
					world[i][j] = TileType.OCEAN;
				} else if(type == 1) {
					world[i][j] = TileType.SEA;
				} else if(type == 2) {
					world[i][j] = TileType.PLAINS;
				} else if(type == 3) {
					world[i][j] = TileType.DESERT;
				} else if(type == 4) {
					world[i][j] = TileType.MOUNTAIN;
				} else if(type == 5) {
					world[i][j] = TileType.FOREST;
				} else if(type == 6) {
					world[i][j] = TileType.HIGH_MOUNTAIN;
				} else {
					world[i][j] = TileType.FERTILE_PLAINS;
				}
			}
		}
		// Make sure that there's at least one land tile
		world[0][0] = TileType.HIGH_MOUNTAIN;
		return world;
	}
}