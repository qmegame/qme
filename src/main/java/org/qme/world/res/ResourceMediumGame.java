package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceMediumGame extends AbstractResource {
    ResourceMediumGame() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.FERTILE_PLAINS) {
            return 25;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.MediumGame;
    }

    @Override
    public String getTexturePath() {
        return "MediumGame.png";
    }
}
