package org.qme.world.gen;

import java.util.ArrayList;
import java.util.Random;

import org.qme.player.PoliticalEntity;
import org.qme.structure.Settlement;
import org.qme.world.Tile;
import org.qme.world.TileType;
import org.qme.world.World;

/**
 * Generates settlement
 * CURSED!
 * @author santiago
 * @since pre5
 */
public class SettlementGen {
	private static int settlementProbabilityModifier(Tile tile) {
		if(tile.getType() == TileType.OCEAN) {
			return 10000;
		}
		if(tile.getType() == TileType.SEA) {
			return 2100000000;
		}
		if(tile.getType() == TileType.DESERT) {
			return 4;
		}
		if(tile.getType() == TileType.PLAINS) {
			return 1;
		}
		if(tile.getType() == TileType.FOREST) {
			return 2;
		}
		if(tile.getType() == TileType.MOUNTAIN) {
			return 3;
		}
		if(tile.getType() == TileType.HIGH_MOUNTAIN) {
			return 5;
		}
		return 2100000000;
	}
	
	public static void settlementGive(World world) {
		ArrayList<PoliticalEntity> civs = world.tiles[0][0].application.game.civilizations;
		for(int w = 0; w < civs.size(); w++) {	
			while(civs.get(w).capital == null) {
				for(int i = 0; i < world.xDimension; i++) {
					for(int j = 0; j < world.yDimension; j++) {
						Random rand = new Random();
						if(rand.nextInt(17) == 0) {
							if(rand.nextInt(settlementProbabilityModifier(world.tiles[i][j])) == 0) {
								Settlement newCity = new Settlement(world.tiles[i][j].application, world.tiles[i][j], civs.get(w));
								world.tiles[i][j].structure = newCity;
								civs.get(w).capital = newCity;
								civs.get(w).superior = null;
								civs.get(w).territory.add(0, world.tiles[i][j]);
								civs.get(w).ownedCities.add(0, newCity);
							}
						}
					}
				}
			}
		}
	}
}