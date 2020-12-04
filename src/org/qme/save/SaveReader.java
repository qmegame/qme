package org.qme.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.qme.main.Main;
import org.qme.main.QApplication;
import org.qme.troops.Unit;
import org.qme.troops.UnitType;
import org.qme.world.Tile;
import org.qme.world.TileType;
import org.qme.world.World;

public class SaveReader {
	
	private QApplication generateGame(File worldSave, File playerSave) {
		try {
			Scanner readWorld = new Scanner(worldSave);
			Scanner readPlayer = new Scanner(playerSave);
			
			QApplication game = new QApplication();
			
			//TODO: 1. Generate indexed players
			//TODO: 3. Generate other player things
			
			World world = new World(game, 20, 20);
			int c = 1;
			while(readWorld.hasNextLine()) {
				String line = readWorld.nextLine();
				if(c == 1) {
					world.xDimension = Integer.valueOf(line);
				} else if(c == 2) {
					world.yDimension = Integer.valueOf(line);
				}
				
				for(int i = 0; i < world.xDimension; i++) {
					for(int j = 0; j < world.yDimension; j++) {
						int index = i * j + j;
						Tile tile = new Tile(game, i, j);
						
						if(c == index * 26 /* How many lines a tile is */ + 6 /* The offset */) {
							tile.type = TileType.valueOf(line);
						}
						
						for(int u = 7; u <= 22; u++) {
							if(c == index * 26 + u) {
								if(line == "") {
									tile.occupier = null;
								} else {
									
									tile.occupier.tileOn = tile;
									
									if(c == index * 26 + 7) {
										tile.occupier.actionable = Boolean.valueOf(line);
									
									} else if(c == index * 26 + 8) {
										tile.occupier.morale = Integer.valueOf(line);
									
									} else if(c == index * 26 + 9) {
										tile.occupier.type = UnitType.valueOf(line);
									
									} else if(c == index * 26 + 10) {
										tile.occupier.owner = game.game.civilizations.get(Integer.valueOf(line));
									
									} //TODO: 2. Generate rest of tiles (rest of units and all of settlements)
								}
							}
						}
						
						world.tiles[i][j] = tile;
					}
				}
				
				c++;
			}
			
			game.world = world;
			
			readWorld.close();
			readPlayer.close();
			
			return game;
		} catch(FileNotFoundException e) {
			Main.displayError("Those files don't appear to exist.", false);
			return new QApplication();
		}
	}
}