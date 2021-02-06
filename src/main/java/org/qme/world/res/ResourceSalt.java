package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSalt extends Resource {
    public ResourceSalt() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.OCEAN) {
            return 200;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.SALT;
    }

    @Override
    public String getTexturePath() {
        return "Salt.png";
    }
}
