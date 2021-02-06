package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceGoldOre extends Resource {
    public ResourceGoldOre() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.HIGH_MOUNTAIN) {
            return 20;
        } else if (type == TileType.MOUNTAIN) {
            return 10;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.GOLD_ORE;
    }

    @Override
    public String getTexturePath() {
        return "GoldOre.png";
    }
}
