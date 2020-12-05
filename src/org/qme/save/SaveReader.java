package org.qme.save;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.qme.main.Main;
import org.qme.main.QApplication;
import org.qme.player.PoliticalEntity;
import org.qme.structure.Settlement;
import org.qme.troops.Unit;
import org.qme.troops.UnitType;
import org.qme.world.Tile;
import org.qme.world.TileType;
import org.qme.world.World;

public class SaveReader {
	/**
	 * @author S-Mackenzie1678
	 * @since pre6
	 * @param worldSave
	 * @param playerSave
	 * @return Literally the entire game lol
	 */
	private QApplication generateGame(File worldSave, File playerSave) {
		try {
			Scanner readWorld = new Scanner(worldSave);
			Scanner readPlayer = new Scanner(playerSave);
			
			QApplication game = new QApplication();
			
			//TODO: 1. Generate indexed players
			ArrayList<PoliticalEntity> civilizations = new ArrayList<>();
			
			// Count the lines
			int lines = 0;
			while(readPlayer.hasNext()) {
				lines++;
			}
			
			// Set up the ArrayList and give players dummy capitals
			int players = lines / 12;
			for(int w = 0; w < players; w++) {
				civilizations.add(w, new PoliticalEntity(""));
				civilizations.get(w).capital = new Settlement(game, new Tile(game, 0, 0), civilizations.get(w));
			}
			
			// Make players what they are
			int b = 1;
			int currentPlayer = -1;
			while(readPlayer.hasNext()) {
				String line = readPlayer.nextLine();
				
				if(b % 12 == 1) {
					currentPlayer++;
					
				} else if(b % 12 == 2) {
					civilizations.get(currentPlayer).name = line;
					
				} else if(b % 12 == 3) {
					civilizations.get(currentPlayer).superior = civilizations.get(Integer.valueOf(line));
					
				} else if(b % 12 == 4) {
					civilizations.get(currentPlayer).capital.tile.x = Integer.valueOf(line);
					
				} else if(b % 12 == 5) {
					civilizations.get(currentPlayer).capital.tile.y = Integer.valueOf(line);
					
				} else if(b % 12 == 6) {
					civilizations.get(currentPlayer).science = Double.valueOf(line);
					
				} else if(b % 12 == 7) {
					civilizations.get(currentPlayer).production = Double.valueOf(line);
					
				} else if(b % 12 == 8) {
					civilizations.get(currentPlayer).gold = Double.valueOf(line);
					
				} else if(b % 12 == 9) {
					civilizations.get(currentPlayer).growth = Double.valueOf(line);
					
				} else if(b % 12 == 10) {
					civilizations.get(currentPlayer).happiness = Double.valueOf(line);
					
				} else if(b % 12 == 11) {
					civilizations.get(currentPlayer).ai = Boolean.valueOf(line);
				}
				
				b++;
			}
			
			// World generation
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
										game.game.civilizations.get(Integer.valueOf(line)).addTroop(tile.occupier);
									
									} else if(c == index * 26 + 11) {
										tile.occupier.attack = Double.valueOf(line);
										
									} else if(c == index * 26 + 12) {
										tile.occupier.defense = Double.valueOf(line);
										
									} else if(c == index * 26 + 13) {
										tile.occupier.health = Double.valueOf(line);
										
									} else if(c == index * 26 + 14) {
										tile.occupier.movement = Double.valueOf(line);
										
									} else if(c == index * 26 + 15) {
										tile.occupier.attacks = Integer.valueOf(line);
										
									} else if(c == index * 26 + 16) {
										tile.occupier.range = Integer.valueOf(line);
										
									} else if(c == index * 26 + 17) {
										tile.occupier.currentAttack = Double.valueOf(line);
										
									} else if(c == index * 26 + 18) {
										tile.occupier.currentDefense = Double.valueOf(line);
										
									} else if(c == index * 26 + 19) {
										tile.occupier.currentHealth = Double.valueOf(line);
										
									} else if(c == index * 26 + 20) {
										tile.occupier.currentMovement = Double.valueOf(line);
										
									} else if(c == index * 26 + 21) {
										tile.occupier.currentAttacks = Integer.valueOf(line);
										
									} else if(c == index * 26 + 22) {
										tile.occupier.currentRange = Integer.valueOf(line);
										
									}
								}
							}
						}
						
						for(int v = 23; v <= 27; v++) {
							if(c == index * 26 + v) {
								if(line == "") {
									tile.settlement = null;
								} else {
									tile.settlement.tile = tile;
									
									if(c == index * 26 + 23) {
										tile.settlement.directOwner = game.game.civilizations.get(Integer.valueOf(line));
										game.game.civilizations.get(Integer.valueOf(line)).ownedCities.add(tile.settlement);
										
									} else if(c == index * 26 + 24) {
										tile.settlement.population = Integer.valueOf(line);
										
									} else if(c == index * 26 + 25) {
										tile.settlement.maxPopulation = Integer.valueOf(line);
										
									} else if (c == index * 26 + 26) {
										tile.settlement.happiness = Double.valueOf(line);
										
									} else if(c == index * 26 + 27) {
										tile.settlement.crime = Double.valueOf(line);
									}
								}
							}
						}
						
						if(c == index * 26 + 28) {
							tile.resource = line;
						}
						
						world.tiles[i][j] = tile;
					}
				}
				
				c++;
			}
			
			// Give players their capitals
			for(int p = 0; p < players; p++) {
				civilizations.get(p).capital = world.tiles[civilizations.get(p).capital.tile.x][civilizations.get(p).capital.tile.y].settlement;
			}
			
			game.world = world;
			game.game.civilizations = civilizations;
			
			readWorld.close();
			readPlayer.close();
			
			return game;
		} catch(FileNotFoundException e) {
			Main.displayError("Those files don't appear to exist.", false);
			return new QApplication();
		}
	}
}