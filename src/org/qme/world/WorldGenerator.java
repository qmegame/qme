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
			//TODO: Give continent mountain range:
				// 75% chance that it'll have a mountain range
				// 25% chance that it'll have none
				//TODO: Pick random tile that borders center tile and make it mountain
				//TODO: Continue until the a mountain borders an ocean or sea
			//TODO: Give continent river:
				// 75% chance 1 river
				//TODO: Pick random non-mountain tile that borders center: make it river
				//TODO: Continue until river touches ocean.
		
		// Make coastal oceans into seas
		world = WorldGenerator.oceanToSea(world, side);
		
		// Return generated (not yet) world
		return world;
	}
	
	/**
	 * Utility function to create oceans
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
		
		// Find continent's height and length
		final int continentL = rightmost - leftmost + 1;
		final int continentH = downmost - upmost + 1;
		
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
	}
}