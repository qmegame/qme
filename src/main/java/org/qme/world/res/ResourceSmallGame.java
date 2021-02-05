package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceSmallGame extends AbstractResource {
    public ResourceSmallGame() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.PLAINS) {
            return 30;
        } else if (type == TileType.FERTILE_PLAINS) {
            return 15;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.SmallGame;
    }

    @Override
    public String getTexturePath() {
        return "SmallGame.png";
    }
}
