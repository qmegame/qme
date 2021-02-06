package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceRock extends Resource {
    public ResourceRock() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.HIGH_MOUNTAIN) {
            return 35;
        } else if (type == TileType.MOUNTAIN) {
            return 80;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.ROCK;
    }

    @Override
    public String getTexturePath() {
        return "Rock.png";
    }
}
