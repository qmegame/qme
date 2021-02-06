package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceCacteye extends Resource {
    public ResourceCacteye() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.DESERT) {
            return 50;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.CACTUS;
    }

    @Override
    public String getTexturePath() {
        return "Cacteye.png";
    }
}
