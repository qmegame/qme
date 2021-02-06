package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSand extends Resource {
    public ResourceSand() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.DESERT) {
            return 100;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.SAND;
    }

    @Override
    public String getTexturePath() {
        return "Sand.png";
    }
}
