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
public class Orchard extends Structure {

    public Orchard(Tile tile) {
        super(tile, StructureType.ORCHARD);
    }

    public ArrayList<ResourceType> getCost() {
        ArrayList<ResourceType> r = new ArrayList<ResourceType>();
        for (int i = 0; i < 10; i++) {
            r.add(ResourceType.LUMBER);
        }
        return r;
    }

    @Override
    public boolean placeable(Tile tile) {
        return tile.resources.isEmpty() && (tile.type == TileType.PLAINS || tile.type == TileType.FERTILE_PLAINS);
    }

    @Override
    public void rollResources() {
        // Random fun times
        Random rand = new Random();
        final int roll = rand.nextInt(100);

        // This always happens
        for(int i = 0; i < 1; i++) {
            this.tile.resources.add(new Resource(ResourceType.FRUIT));
            // TODO: make this happen x amount of times when design figures out how much this produces
        }

        // Regular fertile plains
        if(this.tile.type == TileType.FERTILE_PLAINS) {
            if(roll < 85) {
                this.tile.resources.add(new Resource(ResourceType.TALL_GRASS));
            }
            if(roll < 50) {
                this.tile.resources.add(new Resource(ResourceType.TALL_GRASS));
            }
            // TODO: make this do what design wants it to do when they are clearer about what they mean
            if(roll < 10) {
                this.tile.resources.add(new Resource(ResourceType.GRAPES));
            }
        } else { // Regular plains
            if(roll < 65) {
                this.tile.resources.add(new Resource(ResourceType.TALL_GRASS));
            }
            if(roll < 70) {
                this.tile.resources.add(new Resource(ResourceType.TALL_GRASS));
            }
            // TODO: make this do what design wants it to do when they are clearer about what they mean
            // Both of those todos come from there not being resources implemented, and they should be
            // Implemented into the regular tiles before they're implemented here
        }
    }
}