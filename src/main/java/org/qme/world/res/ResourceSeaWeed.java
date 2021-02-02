package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSeaWeed extends AbstractResource {
    ResourceSeaWeed() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.OCEAN) {
            return 30;
        } else if (type == TileType.SEA) {
            return 25;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.BigFish;
    }

    @Override
    public String getTexturePath() {
        return "BigFish.png";
    }
}