package org.qme.world.gen;

import java.util.Random;
import org.qme.world.TileType;

/**
 * This is the updated world generation that will be put into use eventually
 * @author S-Mackenzie1678
 * @since pre1
 * @see org.qme.world.WorldGen
 */
public class WorldGenNeo {
	
	/**
	 * This gives a random tile when needed
	 * @author S-Mackenzie1678
	 * @return a random tile
	 * @since pre1
	 */
	static private TileType randomNonOceanTile() {
		Random rand = new Random();
		int tile = rand.nextInt(6);
		if (tile == 0) {
			return TileType.PLAINS;
		}
		if (tile == 1) {
			return TileType.DESERT;
		}
		if (tile == 2) {
			return TileType.FOREST;
		}
		if (tile == 3) {
			return TileType.MOUNTAIN;
		}
		if (tile == 4) {
			return TileType.HIGH_MOUNTAIN;
		}
		if (tile == 5) {
			return TileType.FERTILE_PLAINS;
		}
		return TileType.UNGENERATED;
	}
	/**
	 * This checks to see if a bit of a world is filled
	 * @param world
	 * @param topLeftX
	 * @param topLeftY
	 * @param bottomLeftY
	 * @param topRightX
	 * @author S-Mackenzie1678
	 * @return whether or not the world bit is filled
	 * @since pre1
	 */
	static private boolean filledBit(TileType[][] world, int topLeftX, int topLeftY, int bottomLeftY, int topRightX) {
		int chunkWidth = topRightX - topLeftX + 1;
		int chunkHeight = bottomLeftY - topLeftY + 1;
		for (int i = 0; i < chunkWidth; i++) {
			for (int j = 0; j < chunkHeight; j++) {
				if(world[topLeftX + i][topLeftY + j] == TileType.OCEAN) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * This starts the process of making a world.
	 * @param xSize
	 * @param ySize
	 * @author S-Mackenzie1678
	 * @return an array of all TileType.OCEAN
	 * @see org.qme.world.TileType
	 * @since pre1
	 */
	static private TileType[][] ocean(int xSize, int ySize) {
		TileType[][] world = new TileType[xSize][ySize];
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				world[i][j] = TileType.OCEAN;
			}
		}
		return world;
	}
	/**
	 * This generates a continent
	 * @param world
	 * @param xSize
	 * @param ySize
	 * @param xCenter
	 * @param yCenter
	 * @param xRadius
	 * @param yRadius
	 * @author S-Mackenzie1678
	 * @return The updated map with the continent added
	 * @since pre1
	 */
	static private TileType[][] continent(TileType[][] world, int xSize, int ySize, int xCenter, int yCenter, int xRadius, int yRadius) {
		// Set up return variable and center tile
		TileType[][] futureWorld = new TileType[xSize][ySize];
		futureWorld = world;
		futureWorld[xCenter][yCenter] = randomNonOceanTile();
		// Generate seeds
		int xDiam = 1 + 2 * xRadius;
		int yDiam = 1 + 2 * yRadius;
		for (int i = 0; i < xDiam; i++) {
			for (int j = 0; j < yDiam; j++) {
				Random rand = new Random();
				int seed = rand.nextInt(400);
				if(seed < 18) {
					futureWorld[xCenter - xRadius + i][yCenter - yRadius + j] = randomNonOceanTile();
				}
			}
		}
		// Expand seeds
		while (!filledBit(futureWorld, xCenter - xRadius, yCenter - yRadius, yCenter + yRadius, xCenter + xRadius)) {
			for (int k = 0; k < xDiam; k++) {
				for (int l = 0; l < yDiam; l++) {
					int xIndex = xCenter - xRadius + k;
					int yIndex = yCenter - yRadius + l;
					if(futureWorld[xIndex][yIndex] != TileType.OCEAN) {
						if(k != 0) {
							futureWorld[xIndex - 1][yIndex] = futureWorld[xIndex][yIndex];
						}
						if(l != 0) {
							futureWorld[xIndex][yIndex - 1] = futureWorld[xIndex][yIndex];
						}
						if(k != (xDiam - 1)) {
							futureWorld[xIndex + 1][yIndex] = futureWorld[xIndex][yIndex];
							k++;	// Ensures that the one to the right isn't spread further.
						}
						if(l != (yDiam - 1)) {
							futureWorld[xIndex][yIndex + 1] = futureWorld[xIndex][yIndex];
							l++;	// Ensures that the one below isn't spread further.
						}
					}
				}
			}
		}
		// Add one layer of bounding sea
		for (int m = 0; m < yDiam; m++) {
			futureWorld[xCenter - xRadius][yCenter - yRadius + m] = TileType.SEA;
			futureWorld[xCenter + xRadius][yCenter - yRadius + m] = TileType.SEA;
		}
		for (int n = 0; n < xDiam; n++) {
			futureWorld[xCenter - xRadius + n][yCenter - yRadius] = TileType.SEA;
			futureWorld[xCenter - xRadius + n][yCenter + yRadius] = TileType.SEA;
		}
		// Add second layer with 50% chance
		Random rand = new Random();
		for (int o = 0; o < yDiam; o++) {
			if(rand.nextInt(2) == 1) {
				futureWorld[xCenter - xRadius + 1][yCenter - yRadius + o] = TileType.SEA;
			}
			if(rand.nextInt(2) == 1) {
				futureWorld[xCenter + xRadius - 1][yCenter - yRadius + o] = TileType.SEA;
			}
		}
		for (int p = 0; p < xDiam; p++) {
			if(rand.nextInt(2) == 1) {
				futureWorld[xCenter - xRadius + p][yCenter - yRadius + 1] = TileType.SEA;
			}
			if(rand.nextInt(2) == 1) {
				futureWorld[xCenter - xRadius + p][yCenter + yRadius - 1] = TileType.SEA;
			}
		}
		return futureWorld;
	}
	
	public static TileType[][] newWorldMap(int xSize, int ySize) {
		TileType[][] world = new TileType[xSize][ySize];
		world = ocean(xSize, ySize);
		if(xSize < 22 || ySize < 22) {
			return world;	// This algorithm doesn't work below this size
		}
		for (int i = 11; i < (xSize - 12); i++) {
			for (int j = 11; j < (ySize - 12); j++) {
				Random rand = new Random();
				if(world[i][j] == TileType.OCEAN && rand.nextInt(xSize * ySize) < 10) {	// 10 Continents per world seems about right
					world = continent(world, xSize, ySize, i, j, 9 + rand.nextInt(3), 9 + rand.nextInt(3));
				}
			}
		}
		return world;
	}
}