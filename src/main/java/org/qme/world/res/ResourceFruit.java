package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceFruit extends AbstractResource {
    public ResourceFruit() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.FOREST) {
            return 20;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.Fruit;
    }

    @Override
    public String getTexturePath() {
        return "Fruit.png";
    }
}
