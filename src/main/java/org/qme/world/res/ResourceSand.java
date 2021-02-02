package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSand extends AbstractResource {
    ResourceSand() {
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
        return ResourceType.Sand;
    }

    @Override
    public String getTexturePath() {
        return "Sand.png";
    }
}
