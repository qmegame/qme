package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceGrapes extends AbstractResource {
    ResourceGrapes() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.FERTILE_PLAINS) {
            return 10;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.Grapes;
    }

    @Override
    public String getTexturePath() {
        return "Grapes.png";
    }
}