package org.qme.world;

import java.lang.Math;
import java.util.Random;

import org.qme.io.Logger;
import org.qme.io.Severity;

/**
 * The class that houses the generateWorldMap function
 * And also houses auxiliary functions that area called in that function
 * @author santiago
 * @since preA
 */
public class WorldGenerator {
	private static final Random rand = new Random();
	private static final String EXPANDED = "expanded ";

	/**
	 * The function that generates the map for a game
	 * @author santiago
	 * @since preA
	 * @param side The length of a side of the map
	 * @return The kinds of tiles on the map
	 */
	public static TileType[][] generateWorldMap(int side) {
		Logger.log("function called", Severity.NORMAL);
		
		// Generate blank world
		TileType[][] world = new TileType[side][side];
		
		Logger.log("before ocean", Severity.NORMAL);
		// Fill world with ocean
		world = WorldGenerator.ocean(side);
		Logger.log("after ocean", Severity.NORMAL);
		
		final int continents = 1 + (int) Math.ceil(side / 11);
		Logger.log("# of cont: " + Integer.toString(continents),
				Severity.NORMAL);
		
		Logger.log("before continents", Severity.NORMAL);
		for(int continentCount = 0; continentCount < continents; continentCount++) {
			int i = rand.nextInt(side);
			int j = rand.nextInt(side);
			Logger.log("before individual continent", Severity.NORMAL);
			world = WorldGenerator.addContinent(world, side, i, j);
			
			// Make coastal oceans into seas
			Logger.log("before ocean to sea", Severity.NORMAL);
			world = WorldGenerator.oceanToSea(world, side);
		}
		
		// Make coastal oceans into seas
		Logger.log("before final ocean to sea", Severity.NORMAL);
		world = WorldGenerator.oceanToSea(world, side);
		
		// Return generated (not yet) world
		Logger.log("done", Severity.NORMAL);
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
		Logger.log("before each tile for loops", Severity.NORMAL);
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

		// Set up return world
		TileType[][] newWorld = new TileType[side][side];
		newWorld = world;
		
		Logger.log("assign center tile to random", Severity.NORMAL);
		// Assign center tile to a random non-mountain land type
		newWorld[centerX][centerY] = WorldGenerator.assignRandomFlatLand();
		Logger.log("centerX is: " + Integer.toString(centerX), Severity.NORMAL);
		Logger.log("centerY is: " + Integer.toString(centerY), Severity.NORMAL);
		
		// Set up extreme points with most extreme values
		int leftmost = 0;
		int upmost = 0;
		int rightmost = side - 1;
		int downmost = side - 1;
		
		Logger.log("before expand left", Severity.NORMAL);
		// Expand left
		for(int i = 1 /* helps with math*/ ; i <= centerX; i++) {
			try {
				if(WorldGenerator.isType(newWorld[centerX - i][centerY],
						TileType.OCEAN)) {
					
					// Set chance that tile is land
					double chance = Math.pow(0.95, i) / 0.95;
					if(i > side * 2 / 3) {
						chance = 0;
					}
					
					if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
						newWorld[centerX - i][centerY] =
								WorldGenerator.assignRandomFlatLand();
					} else {
						i--;
						
						// Give leftmost a value
						leftmost = centerX - i;
						
						break; // If it's not triggered, stop expanding left
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				leftmost = centerX - i + 1;
				break;
			}
		}
		
		Logger.log(EXPANDED + Integer.toString(centerX - leftmost) +
				" tiles. before expand up", Severity.NORMAL);
		// Expand up
		for(int j = 1 /* helps with math*/ ; j <= centerY; j++) {
			try {
				if(WorldGenerator.isType(newWorld[centerX][centerY - j], TileType.OCEAN)) {
					
					// Set chance that tile is land
					double chance = Math.pow(0.95, j) / 0.95;
					if(j > side * 2 / 3) {
						chance = 0;
					}
					
					// Determine if tile is land
					if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
						newWorld[centerX][centerY - j] =
								WorldGenerator.assignRandomFlatLand();
					} else {
						j--;
						
						// Give upmost a value
						upmost = centerY - j;
						
						break; // If it's not triggered, stop expanding up
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				upmost = centerY - j + 1;
				break;
			}
		}
		
		Logger.log(EXPANDED + Integer.toString(centerY - upmost) +
				" tiles. before expand right", Severity.NORMAL);
		// Expand right
		for(int ii = 1 /* helps with math*/ ; ii < side; ii++) {
			try {
				if(WorldGenerator.isType(newWorld[centerX + ii][centerY], TileType.OCEAN)) {
					
					// Set chance that tile is land
					double chance = Math.pow(0.95, ii) / 0.95;
					if(ii > side * 2 / 3) {
						chance = 0;
					}
					
					if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
						newWorld[centerX + ii][centerY] =
								WorldGenerator.assignRandomFlatLand();
					} else {
						ii--;
						
						// Give rightmost a value
						rightmost = centerX + ii;
						
						break; // If it's not triggered, stop expanding right
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				rightmost = centerX + ii - 1;
				break;
			}
		}
		
		Logger.log(EXPANDED + Integer.toString(centerX - rightmost) +
				" tiles. before expand down", Severity.NORMAL);
		// Expand down
		for(int jj = 1 /* helps with math*/ ; jj < side; jj++) {
			try {
				if(WorldGenerator.isType(newWorld[centerX][centerY + jj], TileType.OCEAN)) {
					
					// Set chance that tile is land
					double chance = Math.pow(0.95, jj) / 0.95;
					if(jj > side * 2 / 3) {
						chance = 0;
					}
					
					if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
						newWorld[centerX][centerY + jj] =
								WorldGenerator.assignRandomFlatLand();
					} else {
						jj--;
						
						// Give downmost a value
						downmost = centerY + jj;
						
						break; // If it's not triggered, stop expanding down
					}
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				downmost = centerY + jj - 1;
				break;
			}
		}
		
		Logger.log(EXPANDED + Integer.toString(centerY - downmost) +
				" tiles. before expand squares", Severity.NORMAL);
		// Expand continents into "squares"
		for(int k = leftmost; k <= rightmost; k++) {
			for(int l = upmost; l <= downmost; l++) {
				if(WorldGenerator.isType(newWorld[k][l], TileType.OCEAN)) {
					final double distance = Math.pow(Math.pow(Math.abs(centerX - k),
							16 / 15) + Math.pow(Math.abs(centerY - l), 16 / 15), 3 / 2);
					
					// Set chance that tile is land
					double chance = Math.pow(0.95, distance) / 0.95;
					if(distance > side * 2 / 3) {
						chance = 0;
					}
					
					if(rand.nextInt(10000) < Math.floor(10000 * chance)) {
						newWorld[k][l] = WorldGenerator.assignRandomFlatLand();
					}
				}
			}
		}
		
		Logger.log("before mountain", Severity.NORMAL);
		// Add the continent's mountain range(s)
		if(rand.nextInt(100) < 75) {
			newWorld = WorldGenerator.addMountain(newWorld, leftmost, upmost,
					rightmost, downmost, side);
		}
		
		Logger.log("before river", Severity.NORMAL);
		// Add the continent's river(s)
		if(rand.nextInt(100) < 75) {
			newWorld = WorldGenerator.addRiver(newWorld, leftmost, upmost,
					rightmost, downmost, side);
		}
		
		Logger.log("done with individual continent", Severity.NORMAL);
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
			int upBound, int rightBound, int downBound, int side) {
		
		// Create output
		TileType[][] mountainWorld = new TileType[side][side];
		mountainWorld = world;
		
		// Set random start for range
		final int startX = rand.nextInt(rightBound - leftBound + 1) + leftBound;
		final int startY = rand.nextInt(downBound - upBound + 1) + upBound;
		
		Logger.log("random start generated", Severity.NORMAL);
		// Make starting tile mountainous
		if(rand.nextInt(3) < 2) {
			mountainWorld[startX][startY] = TileType.MOUNTAIN;
		} else {
			mountainWorld[startX][startY] = TileType.HIGH_MOUNTAIN;
		}
		
		// Detect lead of mountain
		int headX = startX;
		int headY = startY;
		
		// Make sure mountain can't double back
		int previousDirection = rand.nextInt(4);
		
		Logger.log("before while loop", Severity.NORMAL);
		// Extend to the sea
		int infiniteLoopAvoid = 0;
		while(!WorldGenerator.touchesOcean(mountainWorld, headX, headY) &&
				infiniteLoopAvoid < 1000) {
			
			// Get direction to extend
			int nextDirection = rand.nextInt(4);
			
			// Ensure mountain range can't double back
			if(nextDirection == previousDirection) {
				Logger.log("next direction fix triggered; not fixed yet", Severity.NORMAL);
				nextDirection++;
				nextDirection %= 4;
				Logger.log("next direction double back avoided", Severity.NORMAL);
			}
			
			Logger.log("expanding range", Severity.NORMAL);
			// Extend mountain range
			if(nextDirection == 0) {
				if(WorldGenerator.isType(mountainWorld[headX - 1][headY],
						TileType.MOUNTAIN)|| WorldGenerator.isType(
								mountainWorld[headX - 1][headY], TileType.HIGH_MOUNTAIN)) {
					break;
				} else {
					if(rand.nextInt(3) < 2) {
						mountainWorld[headX - 1][headY] = TileType.MOUNTAIN;
					} else {
						mountainWorld[headX - 1][headY] = TileType.HIGH_MOUNTAIN;
					}
					headX--;
				}
			} else if(nextDirection == 1) {
				if(WorldGenerator.isType(mountainWorld[headX][headY - 1],
						TileType.MOUNTAIN)|| WorldGenerator.isType(
								mountainWorld[headX][headY - 1], TileType.HIGH_MOUNTAIN)) {
					break;
				} else {
					if(rand.nextInt(3) < 2) {
						mountainWorld[headX][headY - 1] = TileType.MOUNTAIN;
					} else {
						mountainWorld[headX][headY - 1] = TileType.HIGH_MOUNTAIN;
					}
					headY--;
				}
			} else if(nextDirection == 2) {
				if(WorldGenerator.isType(mountainWorld[headX + 1][headY],
						TileType.MOUNTAIN)|| WorldGenerator.isType(
								mountainWorld[headX + 1][headY], TileType.HIGH_MOUNTAIN)) {
					break;
				} else {
					if(rand.nextInt(3) < 2) {
						mountainWorld[headX + 1][headY] = TileType.MOUNTAIN;
					} else {
						mountainWorld[headX + 1][headY] = TileType.HIGH_MOUNTAIN;
					}
					headX++;
				}
			} else {
				if(WorldGenerator.isType(mountainWorld[headX][headY + 1],
						TileType.MOUNTAIN)|| WorldGenerator.isType(
								mountainWorld[headX][headY + 1], TileType.HIGH_MOUNTAIN)) {
					break;
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
			
			// Give correct previousDirection
			previousDirection = nextDirection;
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
		try {
			if(WorldGenerator.isType(world[x - 1][y], TileType.OCEAN)) {
				return true;
			}
			if(WorldGenerator.isType(world[x][y - 1], TileType.OCEAN)) {
				return true;
			}
			if(WorldGenerator.isType(world[x + 1][y], TileType.OCEAN)) {
				return true;
			}
			if(WorldGenerator.isType(world[x][y + 1], TileType.OCEAN)) {
				return true;
			}
			
			return false;
		} catch(ArrayIndexOutOfBoundsException e) {
			return true;
		}
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
			int upBound, int rightBound, int downBound, int side) {
		Logger.log("create river started", Severity.NORMAL);
		// Create output
		TileType[][] flowingWorld = new TileType[side][side];
		flowingWorld = world;
		
		// Set random start for range
		final int startX = rand.nextInt(rightBound - leftBound + 1) + leftBound;
		final int startY = rand.nextInt(downBound - upBound + 1) + upBound;
		
		// Set starting tile
		flowingWorld[startX][startY] = TileType.OCEAN;
		
		// Detect river's downstream
		int headX = startX;
		int headY = startY;
		Logger.log("starting tile set, before while loop", Severity.NORMAL);
		// Extend to the ocean (or theoretically a looping river (but that's cool))
		int infiniteLoopAvoid = 0;
		while(!WorldGenerator.touchesTwoOceans(flowingWorld, headX, headY) &&
				infiniteLoopAvoid < 1000) {
			
			// Get extend direction
			int nextDirection = rand.nextInt(4);
			Logger.log("extend river", Severity.NORMAL);
			// Extend river
			try {
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
			} catch(ArrayIndexOutOfBoundsException e) {
				return flowingWorld;
			}
			
			// Break infinite loops
			infiniteLoopAvoid++;
		}
		Logger.log("river loop exited", Severity.NORMAL);
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
		
		try {
			// Set up count
			int oceansTouched = 0;
			
			// Count
			if(WorldGenerator.isType(world[x - 1][y], TileType.OCEAN)) {
				oceansTouched++;
			}
			if(WorldGenerator.isType(world[x][y - 1], TileType.OCEAN)) {
				oceansTouched++;
			}
			if(WorldGenerator.isType(world[x + 1][y], TileType.OCEAN)) {
				oceansTouched++;
			}
			if(WorldGenerator.isType(world[x][y + 1], TileType.OCEAN)) {
				oceansTouched++;
			}
			
			// Return
			return oceansTouched >= 2;
		} catch(ArrayIndexOutOfBoundsException e) {
			return true;
		}
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
		Logger.log("ocean to sea called", Severity.NORMAL);
		// Set up return
		TileType[][] shallowWorld = new TileType[side][side];
		shallowWorld = world;
		
		// Check tiles
		for(int i = 1; i < side - 1; i++) {
			// Without starting at 1 and ending early, there'd be Out of Bounds
			for(int j = 1; j < side - 1; j++) {
				if(WorldGenerator.isType(shallowWorld[i][j], TileType.OCEAN) &&
				 WorldGenerator.touchesLand(shallowWorld, i, j)) {
					shallowWorld[i][j] = TileType.SEA;
				}
			}
		}
		Logger.log("Ocean to sea for loop finished", Severity.NORMAL);
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
		try {
			if(!WorldGenerator.isType(world[x - 1][y], TileType.OCEAN) &&
					!WorldGenerator.isType(world[x - 1][y], TileType.SEA)) {
				return true;
			}
			if(!WorldGenerator.isType(world[x][y - 1], TileType.OCEAN) &&
					!WorldGenerator.isType(world[x][y - 1], TileType.SEA)) {
				return true;
			}
			if(!WorldGenerator.isType(world[x + 1][y], TileType.OCEAN) &&
					!WorldGenerator.isType(world[x + 1][y], TileType.SEA)) {
				return true;
			}
			if(!WorldGenerator.isType(world[x][y + 1], TileType.OCEAN) &&
					!WorldGenerator.isType(world[x][y + 1], TileType.SEA)) {
				return true;
			}
			return false;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**
	 * A function that is used to assign a random non-mountain, non-water tile
	 * @author santiago
	 * @since preA
	 * @return A random non-mountain, non-water tile
	 */
	private static TileType assignRandomFlatLand() {
		int type = rand.nextInt(7);
		if(type == 0 || type == 4) {
			return TileType.PLAINS;
		} else if(type == 1 || type == 5) {
			return TileType.DESERT;
		} else if(type == 2 || type == 6) {
			return TileType.FOREST;
		} else {
			return TileType.FERTILE_PLAINS;
		}
	}
	
	/**
	 * Does this please Adam?
	 * @author santiago
	 * @since preA
	 * @param tile The tested tile
	 * @param type The type to test for
	 * @return Whether the tile is the specified type
	 */
	public static boolean isType(TileType tile, TileType type) {
		return tile == type;
	}
}