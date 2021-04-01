package org.qme.world.gen;

import org.qme.io.Logger;
import org.qme.io.Severity;
import org.qme.utils.Direction;
import org.qme.world.TileType;

import java.util.Random;

/**
 * The class that houses the generateWorldMap function
 * And also houses auxiliary functions that area called in that function
 * @author santiago, tom
 * @since 0.1.0
 */
public class WorldGenerator {
	static final Random rand = new Random();
	static final String EXPANDED = "expanded ";

	private static final int GRID_FIXES = 2 ;
	private static final int GRIDING_LIMIT = 4;

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
		double side_squared = side * side;
		int side_as_int = (int) side;
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
			int i = rand.nextInt(side_as_int);
			int j = rand.nextInt(side_as_int);
			Logger.log("before individual continent", Severity.DEBUG);
			world = AddStuff.addContinent(world, side, i, j);
		}
		
		// Make coastal oceans into seas
		Logger.log("before final ocean to sea", Severity.DEBUG);
		world = WorldGenerator.oceanToSea(world, side);

		WorldGenerator.landReclaim(world, side);

		Logger.log("before grid fixing", Severity.DEBUG);

		for (int i = 0; i < GRID_FIXES; ++i) {
			removeGriding(world);
		}

		// Make mountain ranges
		for (int j = 0; j < (int) (side_squared / MOUNTAIN_CONSTANT); j++) {
			world = AddStuff.addMountainFinal(world, side_as_int);
		}

		// Make final rivers
		for (int k = 0; k < (int) (side_squarede / RIVER_CONSTANT); k++) {
			world = AddStuff.addRiverFinal(world, side_as_int);
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
		int parched_side_as_int = (int) parchedSide;

		// Make return blank world
		TileType[][] wet = new TileType[parched_side_as_int][parched_side_as_int];
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
				 Touches.touchesLand(shallowWorld, i, j)) {
					shallowWorld[i][j] = TileType.SEA;
				}
			}
		}
		Logger.log("Ocean to sea for loop finished", Severity.DEBUG);
		// Return
		return shallowWorld;
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
		for(int i = 1; i < (side - 1); i++) {
			for(int j = 1; j < (side - 1); j++) {
				if(WorldGenerator.isOcean(world[i][j])
						&& WorldGenerator.oceanSurroundCount(world, i, j) == 0) {
					world[i][j] = WorldGenerator.assignRandomFlatLand();
				}
			}
		}
		return world;
	}

	/**
	 * If the given tile is an ocean.
	 */
	static boolean isOcean(TileType type) {
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

	/**
	 * Fix up a world by removing the weird griding stuff.
	 */
	public static TileType[][] removeGriding(TileType[][] worldIn) {
		for (int i = 0; i < worldIn.length; i++) {
			for (int j = 0; j < worldIn[0].length; j++) {
				if (oceanSurroundCount(worldIn, i, j) > GRIDING_LIMIT) {
					worldIn[i][j] = TileType.OCEAN;
				}
			}
		}
		return worldIn;
	}

	/**
	 * Returns a random mountain
	 * @return A random mountain
	 */
	static TileType assignRandomMountain() {
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
	static TileType[][] mountainSink(TileType[][] world, int side) {
		for(int i = 0; i < side; i++) {
			for(int j = 0; j < side; j++) {
				if(WorldGenerator.isMountain(world[i][j]) && Touches.touchesTwoOceans(world, i, j)) {
					world[i][j] = TileType.OCEAN;
				}
			}
		}
		return world;
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
}
