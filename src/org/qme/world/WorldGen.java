package org.qme.world;

import java.util.Random;
/**
 * Code for world generation.
 * @author jrpicks
 * @author S-Mackenzie1678
 * @since pre0
 */
public class WorldGen {
	
	// Other stuff can of course go here
	
	/**
	 * Main world generation
	 * @author jrpicks
	 * @author S-Mackenzie1678
	 * @return the array of tile types that can be fed in to a constructor later
	 */
	
	/**
	 * this is a utility function that checks if there are any ungenerated tiles left.
	 * @see org.qme.world.TileType
	 * @param world - the map to be evaluated.
	 * @param xSize - how wide the map is.
	 * @param ySize - how tall the map is.
	 * @return whether or not the map is filled out
	 */
	public static boolean filledWorld(TileType[][] world, int xSize, int ySize) {
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				if(world[i][j] == TileType.UNGENERATED) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * this fills out the map with the biomes by generating sparse, random seeds, and then spreading them to neighboring tiles
	 * @param xSize - how wide the map is.
	 * @param ySize - how tall the map is.
	 * @return a blobby map of tile types
	 */
	public static TileType[][] newWorldMapSeeded(int xSize, int ySize) {
		// Come on, figurative code here!
		TileType[][] world = new TileType[xSize][ySize];
		// Generates seeds
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				world[i][j] = TileType.UNGENERATED;
				Random rand = new Random();	// God I hate that all is class
				if(rand.nextInt(25) == 0) {	// One in 25 chance
					int seedType = rand.nextInt(8);
					if(seedType == 0) {
						world[i][j] = TileType.OCEAN;
					} else if(seedType == 1) {
						world[i][j] = TileType.SEA;
					} else if(seedType == 2) {
						world[i][j] = TileType.PLAINS;
					} else if(seedType == 3) {
						world[i][j] = TileType.DESERT;
					} else if(seedType == 4) {
						world[i][j] = TileType.FOREST;
					} else if(seedType == 5) {
						world[i][j] = TileType.MOUNTAIN;
					} else if(seedType == 6) {
						world[i][j] = TileType.HIGH_MOUNTAIN;
					} else if(seedType == 7) {
						world[i][j] = TileType.FERTILE_PLAINS;
					}
				}
			}
		}
		
		//Fills rest of tiles
		while(!filledWorld(world, xSize, ySize)) {
			for (int k = 0; k < xSize; k++) {
				for (int l = 0; l < ySize; l++) {
					if(world[k][l] != TileType.UNGENERATED) {
						if(k != 0 && world[k - 1][l] == TileType.UNGENERATED) {
							world[k - 1][l] = world[k][l];
						}
						if(l != 0 && world[k][l - 1] == TileType.UNGENERATED) {
							world[k][l - 1] = world[k][l];
						}
						if(k != (xSize - 1) && world[k + 1][l] == TileType.UNGENERATED) {
							world[k + 1][l] = world[k][l];
							k++;	// To make sure it doesn't then spread the tile to the right on the same pass
						}
						if(l != (ySize - 1) && world[k][l + 1] == TileType.UNGENERATED) {
							world[k][l + 1] = world[k][l];
							l++;	// To make sure it doesn't then spread the tile it just spread to
						}
					}
				}
			}
		}
		return world;
	}
	
	/**
	 * this fills out a world as randomly as computers can be
	 * @author S-Mackenzie1678
	 * @param xSize
	 * @param ySize
	 * @return a random TileType map
	 */
	public static TileType[][] newWorldMapRandom(int xSize, int ySize) {
		TileType[][] world = new TileType[xSize][ySize];
		
		for (int i = 0; i < xSize; i++) {
			for (int j = 0; j < ySize; j++) {
				Random rand = new Random();
				int seedType = rand.nextInt(8);
				if(seedType == 0) {
					world[i][j] = TileType.OCEAN;
				} else if(seedType == 1) {
					world[i][j] = TileType.SEA;
				} else if(seedType == 2) {
					world[i][j] = TileType.PLAINS;
				} else if(seedType == 3) {
					world[i][j] = TileType.DESERT;
				} else if(seedType == 4) {
					world[i][j] = TileType.FOREST;
				} else if(seedType == 5) {
					world[i][j] = TileType.MOUNTAIN;
				} else if(seedType == 6) {
					world[i][j] = TileType.HIGH_MOUNTAIN;
				} else if(seedType == 7) {
					world[i][j] = TileType.FERTILE_PLAINS;
				}
			}
		}
		
		return world;
	}

}