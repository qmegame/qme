package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceLargeGame extends AbstractResource {
    ResourceLargeGame() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.FOREST) {
            return 35;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.LargeGame;
    }

    @Override
    public String getTexturePath() {
        return "LargeGame.png";
    }
}
