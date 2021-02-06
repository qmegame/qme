package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceGrain extends Resource {
    public ResourceGrain() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.PLAINS) {
            return 65;
        } else if (type == TileType.FERTILE_PLAINS) {
            return 85;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.GRAIN;
    }

    @Override
    public String getTexturePath() {
        return "Grain.png";
    }
}
