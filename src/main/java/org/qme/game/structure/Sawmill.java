package org.qme.game.structure;

import java.util.ArrayList;
import java.util.Random;

import org.qme.world.Tile;
import org.qme.world.TileType;
import org.qme.world.res.Resource;
import org.qme.world.res.ResourceType;

/**
 * @since 0.4.0
 * @author santiago
 */
public class Sawmill extends Structure {

    public Sawmill(Tile tile) {
        super(tile, StructureType.SAWMILL);
    }

    /**
     * The cost to build
     * @return The cost
     * @since 0.4.0
     * @author santiago
     */
    public ArrayList<ResourceType> getCost() {
        ArrayList<ResourceType> r = new ArrayList<ResourceType>();
        for(int i = 0; i < 5; i++) {
            r.add(ResourceType.LUMBER);
        }
        for(int j = 0; j < 2; j++) {
            r.add(ResourceType.IRON_ORE);
        }
        return r;
    }

    @Override
    public boolean placeable(Tile tile) {
        return tile.resources.isEmpty() && tile.type == TileType.FOREST;
    }

    @Override
    public void rollResources() {
        // Random fun times
        Random rand = new Random();
        final int roll = rand.nextInt(100);

        // This always happens
        for(int i = 0; i < 3; i++) {
            this.tile.resources.add(new Resource(ResourceType.LUMBER));
        }

        // Regular forest things
        if(roll < 65) {
            this.tile.resources.add(new Resource(ResourceType.LUMBER));
        }
        if(roll < 80) {
            this.tile.resources.add(new Resource(ResourceType.LEAF));
        }
        if(roll < 20) {
            this.tile.resources.add(new Resource(ResourceType.FRUIT));
        }
        // TODO: add sap and large game here once they're added into normal forest tiles
        // I'm fairly sure that they were there once. Were they taken out?
    }
}
