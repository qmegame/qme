package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceBigFish extends Resource {
    public ResourceBigFish() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.OCEAN) {
            return 50;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.BIG_FISH;
    }

    @Override
    public String getTexturePath() {
        return "BigFish.png";
    }
}
