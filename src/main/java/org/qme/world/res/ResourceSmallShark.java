package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSmallShark extends AbstractResource {
    ResourceSmallShark() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.SEA) {
            return 5;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.SmallShark;
    }

    @Override
    public String getTexturePath() {
        return "SmallShark.png";
    }
}