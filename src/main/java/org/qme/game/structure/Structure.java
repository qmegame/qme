package org.qme.game.structure;

import org.qme.world.Tile;

public class Structure {

    public Structure(StructureType type) {
        this.type = type;
    }

    public StructureType type;

    /**
     *  Checks whether or not a structure can be placed there
     * @param tile The tile it's being placed on
     * @return Whether or not the structure can be placed there
     */
    public static boolean placeable(Tile tile) {return false; /*TODO: have this do things*/}
}
