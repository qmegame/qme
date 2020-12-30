package org.qme.world;

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
		
		//TODO: Calculate number of continents (side / 11 rounded up)
		// Continents should average 22/3 * side tiles rounded down
		// Avg radius of continent is therefore sqrt(22/3 * side) / 2
		//TODO: For each continent:
			//TODO: Find random center that is ocean
			//TODO: Assign it a non-mountain type
			//TODO: Spread land random non-mountain land types
			// The chance of a tile being a continent is
			// (1 - taxicab distance from center / sqrt(22/3 * side))
			//TODO: Give continent mountain range:
				// 75% chance that it'll have a mountain range
				// 25% chance that it'll have none
				//TODO: Pick random tile that borders center tile and make it mountain
				//TODO: Continue until the a mountain borders an ocean or sea
			//TODO: Give continent river:
				// 75% chance 1 river
				// 12.5% chance 2 river
				//TODO: Pick random non-mountain tile that borders center: make it river
				//TODO: Continue until river touches ocean.
		
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
}