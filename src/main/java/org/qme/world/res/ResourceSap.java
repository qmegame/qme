package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSap extends AbstractResource {
    ResourceSap() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.FOREST) {
            return 15;
        } else if (type == TileType.JUNGLE){
            return 50;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.Sap;
    }

    @Override
    public String getTexturePath() {
        return "Sap.png";
    }
}
