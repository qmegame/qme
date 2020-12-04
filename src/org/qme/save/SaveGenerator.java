package org.qme.save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.qme.main.Main;
import org.qme.main.QApplication;
import org.qme.player.PoliticalEntity;
import org.qme.structure.Settlement;

public class SaveGenerator {
	public void generateWorldSave(File file, QApplication app) throws IOException{
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
}