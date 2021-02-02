package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceIronOre extends AbstractResource {
    ResourceIronOre() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.HIGH_MOUNTAIN) {
            return 40;
        } else if (type == TileType.MOUNTAIN) {
            return 40;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.IronOre;
    }

    @Override
    public String getTexturePath() {
        return "IronOre.png";
    }
}
