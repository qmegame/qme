package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceGrass extends Resource {
    public ResourceGrass() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.PLAINS) {
            return 70;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.GRASS;
    }

    @Override
    public String getTexturePath() {
        return "Grass.png";
    }
}
