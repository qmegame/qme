package org.qme.world.res;

import org.qme.world.TileType;

public class ResourceLeaf extends AbstractResource {
    public ResourceLeaf() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.FOREST) {
            return 80;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.Leaf;
    }

    @Override
    public String getTexturePath() {
        return "Leaf.png";
    }
}
