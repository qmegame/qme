package org.qme.save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.qme.main.Main;
import org.qme.main.QApplication;
import org.qme.player.PoliticalEntity;
import org.qme.structure.Settlement;

public class SaveGenerator {
	
	public void generateSave(File worldSave, File playerSave, QApplication app) {
		try {
			SaveGenerator.generateWorldSave(worldSave, app);
			SaveGenerator.generatePlayerSave(playerSave, app);
		} catch(IOException e) {
			Main.displayError("Saving has gone wrong. Try trying again with different names", false);
		}
	}
	
	/**
	 * This makes the file for the world save
	 * @author S-Mackenzie1678
	 * @since pre6
	 * @param file
	 * @param app
	 * @throws IOException
	 */
	public static void generateWorldSave(File file, QApplication app) throws IOException{
		file.createNewFile();
		FileWriter save = new FileWriter(file);
		
		save.write(new Integer(app.world.xDimension).toString() + "\n");
		save.write(new Integer(app.world.yDimension).toString() + "\n");
		
		save.write("\n");
		
		for(int i = 0; i < app.world.xDimension; i++) {
			for(int j = 0; j < app.world.yDimension; j++) {
				save.write(new Integer(app.world.tiles[i][j].x).toString() + "\n");
				save.write(new Integer(app.world.tiles[i][j].y).toString() + "\n");
				save.write(app.world.tiles[i][j].getType().toString() + "\n");
				
				if(app.world.tiles[i][j].occupier == null) {
					for(int k = 0; k < 16; k++) {
						save.write("\n");
					}
				} else {
					save.write(new Boolean(app.world.tiles[i][j].occupier.actionable()).toString() + "\n");
					save.write(new Integer(app.world.tiles[i][j].occupier.morale).toString() + "\n");
					save.write(app.world.tiles[i][j].occupier.type.toString() + "\n");
					save.write(new Integer(app.world.tiles[i][j].occupier.owner.index).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].occupier.getAttack()).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].occupier.getDefense()).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].occupier.getHealth()).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].occupier.getMovement()).toString() + "\n");
					save.write(new Integer(app.world.tiles[i][j].occupier.getAttacks()).toString() + "\n");
					save.write(new Integer(app.world.tiles[i][j].occupier.getRange()).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].occupier.currentAttack()).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].occupier.currentDefense()).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].occupier.currentHealth()).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].occupier.currentMovement()).toString() + "\n");
					save.write(new Integer(app.world.tiles[i][j].occupier.currentAttacks()).toString() + "\n");
					save.write(new Integer(app.world.tiles[i][j].occupier.currentRange()).toString() + "\n");
				}
				
				if(app.world.tiles[i][j].settlement == null) {
					for(int k = 0; k < 5; k++) {
						save.write("\n");
					}
				} else {
					save.write(new Integer(app.world.tiles[i][j].settlement.directOwner.index ).toString() + "\n");
					save.write(new Integer(app.world.tiles[i][j].settlement.population).toString() + "\n");
					save.write(new Integer(app.world.tiles[i][j].settlement.maxPopulation).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].settlement.happiness).toString() + "\n");
					save.write(new Double(app.world.tiles[i][j].settlement.crime).toString() + "\n");
				}
				
				save.write(app.world.tiles[i][j].resource + "\n");
			}
		}
	}
	
	/**
	 * This makes the file for the player save
	 * @author S-Mackenzie1678
	 * @since pre6
	 * @param file
	 * @param app
	 * @throws IOException
	 */
	public static void generatePlayerSave(File file, QApplication app) throws IOException{
		file.createNewFile();
		FileWriter save = new FileWriter(file);
		
		for(int i = 0; i < app.game.civilizations.size(); i++) {
			save.write(new Integer(i).toString() + "\n");
			
			save.write(app.game.civilizations.get(i).name + "\n");
			
			if(app.game.civilizations.get(i).superior == null) {
				save.write("\n");
			} else {
				save.write(new Integer(app.game.civilizations.get(i).superior.index).toString() + "\n");
			}
			
			save.write(new Integer(app.game.civilizations.get(i).capital.tile.x).toString() + "\n");
			save.write(new Integer(app.game.civilizations.get(i).capital.tile.y).toString() + "\n");
		}
	}
}