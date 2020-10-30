package org.qme.world.gen;

import java.util.Random;

import org.qme.world.TileType;
/**
 * Code for random-ish world generation.
 * @author S-Mackenzie1678
 * @since pre4
 */
public class WorldGenSquigglyBlob {
	
	// Other stuff can of course go here
	
	/**
	 * Main world generation
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
					world[i][j] = assignRandom();
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
	 * this fills out a world with more random elements
	 * @author S-Mackenzie1678
	 * @param xSize
	 * @param ySize
	 * @return a random TileType map
	 */
	public static TileType[][] newWorldMapRandom(int xSize, int ySize, int randomPerBlob) {
		TileType[][] world = new TileType[xSize][ySize];
		world = newWorldMapSeeded(xSize, ySize);
		Random rand = new Random();
		
		for (int i = 1; i < (xSize - 1); i++) {
			for (int j = 1; j < (ySize - 1); j++) {
				if(rand.nextInt(randomPerBlob) == 0) {	// Results in about (25/randomPerBlob) randoms per blob
					world[i][j] = assignRandom();
					int additional = rand.nextInt(5);	// Whether or not an additional tile will be randomized
					if(additional >= 1) {
						world[i + 1][j] = world [i][j];
					}
					if(additional >= 2) {
						world[i][j + 1] = world[i][j];
					}
					if(additional >= 3) {
						world[i - 1][j] = world[i][j];
					}
					if(additional >= 4) {
						world[i][j - 1] = world[i][j];
					}
					
				}
			}
		}
		world = oceanFixer(world, xSize, ySize);
		return world;
	}
	
	private static TileType assignRandom() {
		TileType a;
		Random rand = new Random();
		int type = rand.nextInt(16);
		if(type == 0 || type == 8 || type == 10 || type == 13) {
			a = TileType.OCEAN;
		} else if(type == 1 || type == 11 || type == 14) {
			a = TileType.SEA;
		} else if(type == 2 || type == 9 || type == 12) {
			a = TileType.PLAINS;
		} else if(type == 3) {
			a = TileType.DESERT;
		} else if(type == 4) {
			a = TileType.MOUNTAIN;
		} else if(type == 5 || type == 15) {
			a = TileType.FOREST;
		} else if(type == 6) {
			a = TileType.HIGH_MOUNTAIN;
		} else {
			a = TileType.FERTILE_PLAINS;
		}
		return a;
	}
	
	private static TileType[][] oceanFixer(TileType[][] cliff, int xSize, int ySize) {
		TileType[][] world = cliff.clone();
		for(int i = 1; i < (xSize - 1); i++) {
			for(int j = 1; j < (ySize - 1); j++) {
				if(world[i][j] == TileType.OCEAN) {
					if(shallow(world, i, j)) {
						world[i][j] = TileType.SEA;
					}
				} else if(world[i][j] == TileType.SEA) {
					if(deep(world, i, j)) {
						world[i][j] = TileType.OCEAN;
					}
				}
			}
		}
		return world;
	}
	
	private static boolean shallow(TileType[][] world, int x, int y) {
		if((world[x + 1][y] != TileType.OCEAN) && (world[x + 1][y] != TileType.SEA)) {
			return true;
		}
		if(world[x - 1][y] != TileType.OCEAN && world[x - 1][y] != TileType.SEA) {
			return true;
		}
		if(world[x][y + 1] != TileType.OCEAN && world[x][y + 1] != TileType.SEA) {
			return true;
		}
		if(world[x][y - 1] != TileType.OCEAN && world[x][y - 1] != TileType.SEA) {
			return true;
		}
		return false;
	}
	
	private static boolean deep(TileType[][] world, int x, int y) {
		if(world[x + 1][y] == TileType.OCEAN || world[x + 1][y] == TileType.SEA) {
			if(world[x - 1][y] == TileType.OCEAN || world[x - 1][y] == TileType.SEA) {
				if(world[x][y + 1] == TileType.OCEAN || world[x][y + 1] == TileType.SEA) {
					if(world[x][y - 1] == TileType.OCEAN || world[x][y - 1] == TileType.SEA) {
						return true;
					}
				}
			}
		}
		return false;
	}

}