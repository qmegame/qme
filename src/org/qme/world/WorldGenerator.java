package org.qme.world;

import java.lang.Math;
import java.util.Random;

/**
 * The class that houses the generateWorldMap function
 * And also houses auxiliary functions that area called in that function
 * @author santiago
 * @since preA
 */
public class WorldGenerator {
	
	/**
	 * The function that generates the map for a game
	 * @author santiago
	 * @since preA
	 * @param side The length of a side of the map
	 * @return The kinds of tiles on the map
	 */
	public TileType[][] generateWorldMap(int side) {
		
		// Generate blank world
		TileType[][] world = new TileType[side][side];
		
		// Fill world with ocean
		world = WorldGenerator.ocean(side);
		
		final int continents = (int) Math.ceil(side / 11);
		// Continents should average 22/3 * side tiles rounded down
		// Avg radius of continent is therefore sqrt(22/3 * side) / 2
		
		for(int continentCount = 0; continentCount < continents; continentCount++) {
			Random rand = new Random();
			int i = rand.nextInt(side);
			int j = rand.nextInt(side);
			world = WorldGenerator.addContinent(world, side, i, j);
		}
		
		// Make coastal oceans into seas
		world = WorldGenerator.oceanToSea(world, side);
		
		// Return generated (not yet) world
		return world;
	}
	
	/**
	 * A utility function to create oceans
	 * Mainly used to free up i and j variable names
	 * @author santiago
	 * @since preA
	 * @param parchedSide A side length of the world
	 * @return A TileType[][] of all TileType.OCEAN
	 */
	private static TileType[][] ocean(int parchedSide) {
		
		// Make return blank world
		TileType[][] wet = new TileType[parchedSide][parchedSide];
		
		// Fill each tile with ocean
		for(int i = 0; i < parchedSide; i++) {
			for(int j = 0; j < parchedSide; j++) {
				wet[i][j] = TileType.OCEAN;
			}
		}
		
		// Return the ocean-world
		return wet;
	}
	
	/**
	 * A utility function to generate and add a continent
	 * @author santiago
	 * @since preA
	 * @param world The array to be modified
	 * @param side The size of the world
	 * @param centerX The center of the contnient
	 * @param centerY The center of the continent
	 * @return The inputted array with a continent added
	 */
	private static TileType[][] addContinent(TileType[][] world,
			int side, int centerX, int centerY) {
		
		// Set up random for later
		Random rand = new Random();
		
		// Set up return world
		TileType[][] newWorld = world;
		
		// Assign center tile to a random non-mountain land type
		int centerType = rand.nextInt(7);
		if(centerType == 0 || centerType == 4) {
			newWorld[centerX][centerY] = TileType.PLAINS;
		} else if(centerType == 1 || centerType == 5) {
			newWorld[centerX][centerY] = TileType.FOREST;
		} else if(centerType == 2 || centerType == 6) {
			newWorld[centerX][centerY] = TileType.DESERT;
		} else {
			newWorld[centerX][centerY] = TileType.FERTILE_PLAINS;
		}
		
		// Set up extreme points with most extreme values
		int leftmost = 0;
		int upmost = 0;
		int rightmost = side - 1;
		int downmost = side - 1;
		
		// Expand left
		for(int i = 1 /* helps with math*/ ; i <= centerX; i++) {
			if(newWorld[centerX - i][centerY] == TileType.OCEAN) {
				final double chance = 1 - i * Math.sqrt(22 / 3);
				if(rand.nextInt(10000) < Math.ceil(10000 * chance)) {
					int leftType = rand.nextInt(7);
					if(leftType == 0 || leftType == 4) {
						newWorld[centerX - i][centerY] = TileType.PLAINS;
					} else if(leftType == 1 || leftType == 5) {
						newWorld[centerX - i][centerY] = TileType.FOREST;
					} else if(leftType == 2 || leftType == 6) {
						newWorld[centerX - i][centerY] = TileType.DESERT;
					} else {
						newWorld[centerX - i][centerY] = TileType.FERTILE_PLAINS;
					}
				} else {
					i--;
					
					// Give leftmost a value
					leftmost = centerX - i;
					
					break; // If it's not triggered, stop expanding left
				}
			}
		}
		
		// Expand up
		for(int j = 1 /* helps with math*/ ; j <= centerY; j++) {
			if(newWorld[centerX][centerY - j] == TileType.OCEAN) {
				final double chance = 1 - j * Math.sqrt(22 / 3);
				if(rand.nextInt(10000) < Math.ceil(10000 * chance)) {
					int upType = rand.nextInt(7);
					if(upType == 0 || upType == 4) {
						newWorld[centerX][centerY - j] = TileType.PLAINS;
					} else if(upType == 1 || upType == 5) {
						newWorld[centerX][centerY - j] = TileType.FOREST;
					} else if(upType == 2 || upType == 6) {
						newWorld[centerX][centerY - j] = TileType.DESERT;
					} else {
						newWorld[centerX][centerY - j] = TileType.FERTILE_PLAINS;
					}
				} else {
					j--;
					
					// Give upmost a value
					upmost = centerY - j;
					
					break; // If it's not triggered, stop expanding up
				}
			}
		}
		
		// Expand right
		for(int ii = 1 /* helps with math*/ ; ii < side; ii++) {
			if(newWorld[centerX + ii][centerY] == TileType.OCEAN) {
				final double chance = 1 - ii * Math.sqrt(22 / 3);
				if(rand.nextInt(10000) < Math.ceil(10000 * chance)) {
					int rightType = rand.nextInt(7);
					if(rightType == 0 || rightType == 4) {
						newWorld[centerX + ii][centerY] = TileType.PLAINS;
					} else if(rightType == 1 || rightType == 5) {
						newWorld[centerX + ii][centerY] = TileType.FOREST;
					} else if(rightType == 2 || rightType == 6) {
						newWorld[centerX + ii][centerY] = TileType.DESERT;
					} else {
						newWorld[centerX + ii][centerY] = TileType.FERTILE_PLAINS;
					}
				} else {
					ii--;
					
					// Give rightmost a value
					rightmost = centerX + ii;
					
					break; // If it's not triggered, stop expanding right
				}
			}
		}
		
		// Expand down
		for(int jj = 1 /* helps with math*/ ; jj < side; jj++) {
			if(newWorld[centerX][centerY + jj] == TileType.OCEAN) {
				final double chance = 1 - jj * Math.sqrt(22 / 3);
				if(rand.nextInt(10000) < Math.ceil(10000 * chance)) {
					int rightType = rand.nextInt(7);
					if(rightType == 0 || rightType == 4) {
						newWorld[centerX][centerY + jj] = TileType.PLAINS;
					} else if(rightType == 1 || rightType == 5) {
						newWorld[centerX][centerY + jj] = TileType.FOREST;
					} else if(rightType == 2 || rightType == 6) {
						newWorld[centerX][centerY + jj] = TileType.DESERT;
					} else {
						newWorld[centerX][centerY + jj] = TileType.FERTILE_PLAINS;
					}
				} else {
					jj--;
					
					// Give downmost a value
					downmost = centerY + jj;
					
					break; // If it's not triggered, stop expanding down
				}
			}
		}
		
		// Expand continents into "squares"
		for(int k = leftmost; k <= rightmost; k++) {
			for(int l = upmost; l <= downmost; l++) {
				if(newWorld[k][l] == TileType.OCEAN) {
					final int distance = Math.abs(centerX - k) + Math.abs(centerY - l);
					final double chance = 1 - distance * Math.sqrt(22 / 3);
					if(rand.nextInt(10000) < Math.ceil(10000 * chance)) {
						int type = rand.nextInt(7);
						if(type == 0 || type == 4) {
							newWorld[k][l] = TileType.PLAINS;
						} else if(type == 1 || type == 5) {
							newWorld[k][l] = TileType.FOREST;
						} else if(type == 2 || type == 6) {
							newWorld[k][l] = TileType.DESERT;
						} else {
							newWorld[k][l] = TileType.FERTILE_PLAINS;
						}
					}
				}
			}
		}
		
		// Add the continent's mountain range(s)
		if(rand.nextInt(100) < 75) {
			newWorld = WorldGenerator.addMountain(newWorld, leftmost, upmost,
					rightmost, downmost);
		}
		
		// Add the continent's river(s)
		if(rand.nextInt(100) < 75) {
			newWorld = WorldGenerator.addRiver(newWorld, leftmost, upmost,
					rightmost, downmost);
		}
		
		// Return the world plus continent
		return newWorld;
	}
	
	/**
	 * A utility function to generate a mountain range on a continent
	 * @author santiago
	 * @since preA
	 * @param world A non-mountainous, flat expanse
	 * @param leftBound The left extreme of the continent
	 * @param upBound The up extreme of the continent
	 * @param rightBound The right extreme of the continent
	 * @param downBound The down extreme of the continent
	 * @return A world with a continent with a mountain range
	 */
	private static TileType[][] addMountain(TileType[][] world, int leftBound,
			int upBound, int rightBound, int downBound) {
		
		// Create output
		TileType[][] mountainWorld = world;
		
		// Set random start for range
		Random rand = new Random();
		final int startX = rand.nextInt(rightBound - leftBound + 1) + leftBound;
		final int startY = rand.nextInt(downBound - upBound + 1) + upBound;
		
		// Make starting tile mountainous
		if(rand.nextInt(3) < 2) {
			mountainWorld[startX][startY] = TileType.MOUNTAIN;
		} else {
			mountainWorld[startX][startY] = TileType.HIGH_MOUNTAIN;
		}
		
		// Detect lead of mountain
		int headX = startX;
		int headY = startY;
		
		// Extend to the sea
		int infiniteLoopAvoid = 0;
		while(!WorldGenerator.touchesOcean(mountainWorld, headX, headY) &&
				infiniteLoopAvoid < 1000) {
			
			// Get direction to extend
			int nextDirection = rand.nextInt(4);
			
			// Extend mountain range
			if(nextDirection == 0) {
				if(mountainWorld[headX - 1][headY] == TileType.MOUNTAIN ||
						mountainWorld[headX - 1][headY] == TileType.HIGH_MOUNTAIN) {
					headX--;
				} else {
					if(rand.nextInt(3) < 2) {
						mountainWorld[headX - 1][headY] = TileType.MOUNTAIN;
					} else {
						mountainWorld[headX - 1][headY] = TileType.HIGH_MOUNTAIN;
					}
					headX--;
				}
			} else if(nextDirection == 1) {
				if(mountainWorld[headX][headY - 1] == TileType.MOUNTAIN ||
						mountainWorld[headX][headY - 1] == TileType.HIGH_MOUNTAIN) {
					headY--;
				} else {
					if(rand.nextInt(3) < 2) {
						mountainWorld[headX][headY - 1] = TileType.MOUNTAIN;
					} else {
						mountainWorld[headX][headY - 1] = TileType.HIGH_MOUNTAIN;
					}
					headY--;
				}
			} else if(nextDirection == 2) {
				if(mountainWorld[headX + 1][headY] == TileType.MOUNTAIN ||
						mountainWorld[headX + 1][headY] == TileType.HIGH_MOUNTAIN) {
					headX++;
				} else {
					if(rand.nextInt(3) < 2) {
						mountainWorld[headX + 1][headY] = TileType.MOUNTAIN;
					} else {
						mountainWorld[headX + 1][headY] = TileType.HIGH_MOUNTAIN;
					}
					headX++;
				}
			} else {
				if(mountainWorld[headX][headY + 1] == TileType.MOUNTAIN ||
						mountainWorld[headX][headY + 1] == TileType.HIGH_MOUNTAIN) {
					headY++;
				} else {
					if(rand.nextInt(3) < 2) {
						mountainWorld[headX][headY + 1] = TileType.MOUNTAIN;
					} else {
						mountainWorld[headX][headY + 1] = TileType.HIGH_MOUNTAIN;
					}
					headY++;
				}
			}
			
			// Break infinite loops
			infiniteLoopAvoid++;
		}
		
		// Return
		return mountainWorld;
	}
	
	/**
	 * A utility function to check if a tile touches an ocean
	 * @author santiago
	 * @since preA
	 * @param world The world
	 * @param x The tile's x
	 * @param y The tile's y
	 * @return Whether or not the tile touches an ocean
	 */
	private static boolean touchesOcean(TileType[][] world, int x, int y) {
		if(world[x - 1][y] == TileType.OCEAN) {
			return true;
		}
		if(world[x][y - 1] == TileType.OCEAN) {
			return true;
		}
		if(world[x + 1][y] == TileType.OCEAN) {
			return true;
		}
		if(world[x][y + 1] == TileType.OCEAN) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * A utility function that generates a river on a continent
	 * @author santiago
	 * @since preA
	 * @param world The riverless world
	 * @param leftBound The left extreme of the continent
	 * @param upBound The up extreme of the continent
	 * @param rightBound The right extreme of the continent
	 * @param downBound The down extreme of the continent
	 * @return A world with a continent with a river
	 */
	private static TileType[][] addRiver(TileType[][] world, int leftBound,
			int upBound, int rightBound, int downBound) {
		
		// Create output
		TileType[][] flowingWorld = world;
		
		// Set random start for range
		Random rand = new Random();
		final int startX = rand.nextInt(rightBound - leftBound + 1) + leftBound;
		final int startY = rand.nextInt(downBound - upBound + 1) + upBound;
		
		// Set starting tile
		flowingWorld[startX][startY] = TileType.OCEAN;
		
		// Detect river's downstream
		int headX = startX;
		int headY = startY;
		
		// Extend to the ocean (or theoretically a looping river (but that's cool))
		int infiniteLoopAvoid = 0;
		while(!WorldGenerator.touchesTwoOceans(flowingWorld, headX, headY) &&
				infiniteLoopAvoid < 1000) {
			
			// Get extend direction
			int nextDirection = rand.nextInt(4);
			
			// Extend river
			if(nextDirection == 0) {
				flowingWorld[headX - 1][headY] = TileType.OCEAN;
				headX--;
			} else if(nextDirection == 1) {
				flowingWorld[headX][headY - 1] = TileType.OCEAN;
				headY--;
			} else if(nextDirection == 2) {
				flowingWorld[headX + 1][headY] = TileType.OCEAN;
				headX++;
			} else {
				flowingWorld[headX][headY + 1] = TileType.OCEAN;
				headY++;
			}
			
			// Break infinite loops
			infiniteLoopAvoid++;
		}
		
		// Return
		return flowingWorld;
	}
	
	/**
	 * A utility function that checks whether a tile touches ocean on two sides
	 * @author santiago
	 * @since preA
	 * @param world The world with the tile
	 * @param x The tile to be checked
	 * @param y The tile to be checked
	 * @return True or false: it touches two ocean tiles
	 */
	private static boolean touchesTwoOceans(TileType[][] world, int x, int y) {
		
		// Set up count
		int oceansTouched = 0;
		
		// Count
		if(world[x - 1][y] == TileType.OCEAN) {
			oceansTouched++;
		} else if(world[x][y - 1] == TileType.OCEAN) {
			oceansTouched++;
		} else if(world[x + 1][y] == TileType.OCEAN) {
			oceansTouched++;
		} else if(world[x][y + 1] == TileType.OCEAN) {
			oceansTouched++;
		}
		
		// Return
		return oceansTouched >= 2;
	}
	
	/**
	 * A utility function to turn all ocean tiles that touch land into sea tiles
	 * @author santiago
	 * @since preA
	 * @param world The world in which all ocean tiles will be cheched
	 * @param side The length of the world's sides
	 * @return The world but with sea tiles surrounding the land
	 */
	private static TileType[][] oceanToSea(TileType[][] world, int side) {
		
		// Set up return
		TileType[][] shallowWorld = world;
		
		// Check tiles
		for(int i = 1; i < side - 1; i++) {
			// Without starting at 1 and ending early, there'd be Out of Bounds
			for(int j = 1; j < side - 1; j++) {
				if(shallowWorld[i][j] == TileType.OCEAN) {
					if(WorldGenerator.touchesLand(shallowWorld, i, j)) {
						shallowWorld[i][j] = TileType.SEA;
					}
				}
			}
		}
		
		// Return
		return shallowWorld;
	}
	
	/**
	 * A utility function to determine if a tile touches land
	 * @author santiago
	 * @since preA
	 * @param world The world with no continental shelves
	 * @param x The tile to be checked
	 * @param y The tile to be checked
	 * @return A world with nice continental shelves
	 */
	private static boolean touchesLand(TileType[][] world, int x, int y) {
		if(world[x - 1][y] != TileType.OCEAN && world[x - 1][y] != TileType.SEA) {
			return true;
		}
		if(world[x][y - 1] != TileType.OCEAN && world[x][y - 1] != TileType.SEA) {
			return true;
		}
		if(world[x + 1][y] != TileType.OCEAN && world[x + 1][y] != TileType.SEA) {
			return true;
		}
		if(world[x][y + 1] != TileType.OCEAN && world[x][y + 1] != TileType.SEA) {
			return true;
		}
		return false;
	}
}