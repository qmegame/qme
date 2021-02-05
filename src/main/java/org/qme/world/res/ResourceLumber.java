package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceLumber extends AbstractResource {
    public ResourceLumber() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.FOREST) {
            return 65;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.Lumber;
    }

    @Override
    public String getTexturePath() {
        return "Lumber.png";
    }
}
