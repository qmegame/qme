package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceTallGrass extends AbstractResource {
    ResourceTallGrass() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.FERTILE_PLAINS) {
            return 50;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.TallGrass;
    }

    @Override
    public String getTexturePath() {
        return "TallGrass.png";
    }
}