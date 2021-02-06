package org.qme.world.gen;

import org.qme.io.Logger;
import org.qme.io.Severity;
import org.qme.utils.Direction;
import org.qme.world.TileType;
import org.qme.world.gen.AddContinent;
import org.qme.world.World;

import java.util.Random;

/**
 * The class that houses the generateWorldMap function
 * And also houses auxiliary functions that area called in that function
 * @author santiago, Tom
 * @since 0.1.0
 */
public class WorldGenerator {
	static final Random rand = new Random();
	static final String EXPANDED = "expanded ";

	// The higher the constant, the less of that feature generates
	private static final int MOUNTAIN_CONSTANT = 30;
	private static final int RIVER_CONSTANT = 120;

	WorldGenerator() {
		throw new IllegalStateException("World Generation");
	}

	/**
	 * The function that generates the map for a game
	 * @author santiago
	 * @since 0.1.0
	 * @param side The length of a side of the map
	 * @return The kinds of tiles on the map
	 */

	public static TileType[][] generateWorldMap(double side) {
		Logger.log("Generating Map", Severity.NORMAL);
		
		// Generate blank world
		TileType[][] world;
		
		Logger.log("before ocean", Severity.DEBUG);
		// Fill world with ocean
		world = WorldGenerator.ocean(side);
		Logger.log("after ocean", Severity.DEBUG);
		
		final int continents = 5 * (1 + (int) Math.ceil(side / 11));
		Logger.log("# of cont: " + Integer.toString(continents),
				Severity.DEBUG);
		
		Logger.log("before continents", Severity.DEBUG);
		for(int continentCount = 0; continentCount < continents; continentCount++) {
			int i = rand.nextInt((int) side);
			int j = rand.nextInt((int) side);
			Logger.log("before individual continent", Severity.DEBUG);
			world = AddContinent.addContinent(world, side, i, j);
		}
		
		// Make coastal oceans into seas
		Logger.log("before final ocean to sea", Severity.DEBUG);
		world = WorldGenerator.oceanToSea(world, side);

		world = WorldGenerator.landReclaim(world, side);

		Logger.log("before grid fixing", Severity.DEBUG);

		for (int i = 0; i < GRID_FIXES; ++i)
			world = removeGridding(world);

		// Make mountain ranges
		for (int j = 0; j < (int) (side * side / MOUNTAIN_CONSTANT); j++) {
			world = WorldGenerator.addMountainFinal(world, (int) side);
		}

		// Make final rivers
		for (int k = 0; k < (int) (side * side / RIVER_CONSTANT); k++) {
			world = addRiverFinal(world, (int) side);
		}
		// Make coastal oceans into seas
		Logger.log("before ocean to sea", Severity.DEBUG);
		world = WorldGenerator.oceanToSea(world, side);

		// Return generated (not yet) world
		Logger.log("done", Severity.DEBUG);
		return world;
	}
	
	/**
	 * A utility function to create oceans
	 * Mainly used to free up i and j variable names
	 * @author santiago
	 * @since 0.1.0
	 * @param parchedSide A side length of the world
	 * @return A TileType[][] of all TileType.OCEAN
	 */
	private static TileType[][] ocean(double parchedSide) {

		// Make return blank world
		TileType[][] wet = new TileType[(int) parchedSide][(int) parchedSide];
		Logger.log("before each tile for loops", Severity.DEBUG);

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
	 * A utility function to check if a tile touches an ocean
	 * @author santiago
	 * @since 0.1.0
	 * @param world The world
	 * @param x The tile's x
	 * @param y The tile's y
	 * @return Whether or not the tile touches an ocean
	 */
	private static boolean touchesOcean(TileType[][] world, int x, int y) {
		try {
			if(getTileDirectional(world, x, y, Direction.UP) == TileType.OCEAN) { return true; }
			if(getTileDirectional(world, x, y, Direction.DOWN) == TileType.OCEAN) { return true; }
			if(getTileDirectional(world, x, y, Direction.LEFT) == TileType.OCEAN) { return true; }
			if(getTileDirectional(world, x, y, Direction.RIGHT) == TileType.OCEAN) { return true; }
			return false;
		} catch(ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}

	/**
	 * A utility function to check if a tile touches a sea
	 * @author santiago
	 * @since 0.2.0
	 * @param world The world
	 * @param x The tile's x
	 * @param y The tile's y
	 * @return Whether or not the tile touches a sea
	 */
	private static boolean touchesSea(TileType[][] world, int x, int y) {
		try {
			if (getTileDirectional(world, x, y, Direction.UP) == TileType.SEA) {
				return true;
			}
			if (getTileDirectional(world, x, y, Direction.DOWN) == TileType.SEA) {
				return true;
			}
			if (getTileDirectional(world, x, y, Direction.LEFT) == TileType.SEA) {
				return true;
			}
			if (getTileDirectional(world, x, y, Direction.RIGHT) == TileType.SEA) {
				return true;
			}
			return false;
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}
	
	/**
	 * A utility function that checks whether a tile touches ocean on two sides
	 * @author santiago
	 * @since 0.1.0
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
			if(isOcean(getTileDirectional(world, x, y, Direction.UP))) {
				oceansTouched++;
			}
			if(isOcean(getTileDirectional(world, x, y, Direction.DOWN))) {
				oceansTouched++;
			}
			if(isOcean(getTileDirectional(world, x, y, Direction.LEFT))) {
				oceansTouched++;
			}
			if(isOcean(getTileDirectional(world, x, y, Direction.RIGHT))) {
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
	 * @since 0.1.0
	 * @param world The world in which all ocean tiles will be checked
	 * @param side The length of the world's sides
	 * @return The world but with sea tiles surrounding the land
	 */
	private static TileType[][] oceanToSea(TileType[][] world, double side) {
		Logger.log("ocean to sea called", Severity.DEBUG);
		// Set up return
		TileType[][] shallowWorld;
		shallowWorld = world;
		
		// Check tiles
		for(int i = 1; i < side - 1; i++) {
			// Without starting at 1 and ending early, they'd be Out of Bounds
			for(int j = 1; j < side - 1; j++) {
				if(shallowWorld[i][j] == TileType.OCEAN &&
				 WorldGenerator.touchesLand(shallowWorld, i, j)) {
					shallowWorld[i][j] = TileType.SEA;
				}
			}
		}
		Logger.log("Ocean to sea for loop finished", Severity.DEBUG);
		// Return
		return shallowWorld;
	}
	
	/**
	 * A utility function to determine if a tile touches land
	 * @author santiago
	 * @since 0.1.0
	 * @param world The world with no continental shelves
	 * @param x The tile to be checked
	 * @param y The tile to be checked
	 */
	private static boolean touchesLand(TileType[][] world, int x, int y) {
		try {
			if(!isOcean(getTileDirectional(world, x, y, Direction.UP))) {
				return true;
			}
			if(!isOcean(getTileDirectional(world, x, y, Direction.DOWN))) {
				return true;
			}
			if(!isOcean(getTileDirectional(world, x, y, Direction.LEFT))) {
				return true;
			}
			if(!isOcean(getTileDirectional(world, x, y, Direction.RIGHT))) {
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
	 * @since 0.1.0
	 * @return A random non-mountain, non-water tile
	 */
	static TileType assignRandomFlatLand() {
		int type = rand.nextInt(8);
		if (type == 0 || type == 4) {
			return TileType.PLAINS;
		} else if (type == 1 || type == 5) {
			return TileType.DESERT;
		} else if (type == 2 || type == 6) {
			return TileType.FOREST;
		} else if (type == 7) {
			return TileType.JUNGLE;
		} else {
			return TileType.FERTILE_PLAINS;
		}
	}

	/**
	 * @author santiago
	 * @since 0.2.0
	 * @param world The world
	 * @param side How long a side of the world is
	 * @return The world but with no little "ponds"
	 */

	private static TileType[][] landReclaim(TileType[][] world, double side) {
		TileType[][] dubaiWorld = world;
		for(int i = 1; i < (side - 1); i++) {
			for(int j = 1; j < (side - 1); j++) {
				if(WorldGenerator.isOcean(dubaiWorld[i][j])
						&& WorldGenerator.oceanSurroundCount(dubaiWorld, i, j) == 0) {
					dubaiWorld[i][j] = WorldGenerator.assignRandomFlatLand();
				}
			}
		}
		return dubaiWorld;
	}

	/**
	 * If the given tile is an ocean.
	 */
	private static boolean isOcean(TileType type) {
		return type == TileType.OCEAN || type == TileType.SEA;
	}

	/**
	 * How many oceans are surrounding this tile.
	 */
	public static int oceanSurroundCount(TileType[][] world, int x, int y) {
		int startX = Math.max(0, x - 1);
		int endX = Math.min(world[0].length - 1, x + 1);
		int startY = Math.max(0, y - 1);
		int endY = Math.min(world.length - 1, y + 1);
		int total = 0;
		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				if (isOcean(world[i][j])) total++;
			}
		}
		return total;
	}

	private static final int GRID_FIXES = 2 ;

	private static final int GRIDDING_LIMIT = 4;

	/**
	 * Fix up a world by removing the weird gridding stuff.
	 */
	public static TileType[][] removeGridding(TileType[][] worldIn) {
		TileType[][] world = worldIn;
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				if (oceanSurroundCount(world, i, j) > GRIDDING_LIMIT) {
					world[i][j] = TileType.OCEAN;
				}
			}
		}
		return world;
	}

	/**
	 * The final mountain addition (after continents)
	 * @param world The world
	 * @param side The side length
	 */
	private static TileType[][] addMountainFinal(TileType[][] world, int side) {
		TileType[][] mountainWorld = world;
		// Get range direction and length
		final int rangeLength = 7 + (rand.nextInt(5) - 2);
		final Direction rangeDirection = Direction.values()[rand.nextInt(4)];

		// Get start of range
		final int startX = rand.nextInt(side);
		final int startY = rand.nextInt(side);

		// Generate mountains
		mountainWorld[startX][startY] = WorldGenerator.assignRandomMountain();
		for(int i = 1; i < rangeLength; i++) {
			try {
				if(rangeDirection == Direction.UP) {
					mountainWorld[startX][startY - i] = WorldGenerator.assignRandomMountain();
				} else if(rangeDirection == Direction.DOWN) {
					mountainWorld[startX][startY + i] = WorldGenerator.assignRandomMountain();
				} else if(rangeDirection == Direction.LEFT) {
					mountainWorld[startX - 1][startY] = WorldGenerator.assignRandomMountain();
				} else {
					mountainWorld[startX + 1][startY] = WorldGenerator.assignRandomMountain();
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				break;
			}
		}

		// Sink floating mountains
		mountainWorld = WorldGenerator.mountainSink(mountainWorld, side);

		return mountainWorld;
	}

	/**
	 * Returns a random mountain
	 * @return A random mountain
	 */
	private static TileType assignRandomMountain() {
		if(rand.nextInt(4) == 0) {
			return TileType.HIGH_MOUNTAIN;
		} else {
			return TileType.MOUNTAIN;
		}
	}

	/**
	 * This runs to sink any ill-formed mountain chains
	 * @param world The world
	 * @param side The side length
	 * @return A world without random mountain chains
	 */
	private static TileType[][] mountainSink(TileType[][] world, int side) {
		TileType[][] sunkenWorld = world;

		for(int i = 0; i < side; i++) {
			for(int j = 0; j < side; j++) {
				if(WorldGenerator.isMountain(sunkenWorld[i][j]) && WorldGenerator.touchesTwoOceans(sunkenWorld, i, j)) {
					sunkenWorld[i][j] = TileType.OCEAN;
				}
			}
		}

		return sunkenWorld;
	}

	private static boolean isMountain(TileType tile) {
		return tile == TileType.MOUNTAIN || tile == TileType.HIGH_MOUNTAIN;
	}

	/**
	 * From a reference tile, get a tile next to it, in a direction
	 * @param world The world
	 * @param x X of the reference tile
	 * @param y Y of the reference tile
	 * @param direction Which direction to get the tile
	 * @return The tile in that direction from the reference
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static TileType getTileDirectional(TileType[][] world, int x, int y, Direction direction)
			throws ArrayIndexOutOfBoundsException{
		if(direction == Direction.UP) {
			return world[x][y - 1];
		} else if(direction == Direction.DOWN) {
			return world[x][y + 1];
		} else if(direction == Direction.LEFT) {
			return world[x - 1][y];
		} else {
			return world[x + 1][y];
		}
	}

	private static TileType[][] addRiverFinal(TileType[][] world, int side) {
		TileType[][] riverWorld = world;
		// Get river direction
		final Direction riverDirection = Direction.values()[rand.nextInt(4)];

		// Get start of river
		int startX = rand.nextInt(side);
		int startY = rand.nextInt(side);
		riverWorld[startX][startY] = TileType.OCEAN;

		// Generate river until touches ocean
		try {
			while (!isOcean(getTileDirectional(riverWorld, startX, startY, riverDirection))) {
				try {
					if (riverDirection == Direction.UP) {
						riverWorld[startX][startY - 1] = TileType.OCEAN;
						startY--;
					} else if (riverDirection == Direction.DOWN) {
						riverWorld[startX][startY + 1] = TileType.OCEAN;
						startY++;
					} else if (riverDirection == Direction.LEFT) {
						riverWorld[startX - 1][startY] = TileType.OCEAN;
						startX--;
					} else {
						riverWorld[startX + 1][startY] = TileType.OCEAN;
						startX++;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			return world;
		}

		// Return
		return riverWorld;
	}
}
