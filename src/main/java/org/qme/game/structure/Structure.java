package org.qme.game.structure;

import java.util.ArrayList;

import org.qme.world.res.ResourceType;
import org.qme.world.Tile;

/**
 * Abstract class for structures
 * @since 0.4.0
 * @author santiago
 */
public abstract class Structure {

    /**
     *
     * @param tile Where it's constructed
     * @param type What type
     * @since 0.4.0
     */
    public Structure(Tile tile, StructureType type) {
        this.type = type;
        this.tile = tile;
    }

    public final StructureType type;
    public final Tile tile;


    /**
     *  Checks whether or not a structure can be placed there
     * @param tile The tile it's being placed on
     * @return Whether or not the structure can be placed there
     */
    public boolean placeable(Tile tile) {
        switch(this.type) {
            case MINESHAFT: case SAWMILL: case FARM: case VINEYARD: case ORCHARD:
                return tile.structures.isEmpty();
                // It won't let me place a `break;` here
            default:
                return true;
        }
    }

    /**
     * What the structure costs to build
     * @return What the structure costs to build
     */
    public abstract ArrayList<ResourceType> getCost();
    
    public void rollResources() {} // I know, just trust me
}
