package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSeagull extends Resource {
    public ResourceSeagull() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.SEA) {
            return 200;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.SEAGULL;
    }

    @Override
    public String getTexturePath() {
        return "Seagull.png";
    }
}
