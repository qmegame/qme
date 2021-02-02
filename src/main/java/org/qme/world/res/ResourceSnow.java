package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSnow extends AbstractResource {
    ResourceSnow() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.HIGH_MOUNTAIN) {
            return 100;
        } else if (type == TileType.MOUNTAIN) {
            return 20;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.Snow;
    }

    @Override
    public String getTexturePath() {
        return "Snow.png";
    }
}
