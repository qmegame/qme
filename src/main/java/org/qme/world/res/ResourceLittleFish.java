package org.qme.world.res;

        import org.qme.world.TileType;

public class ResourceLittleFish extends Resource {
    public ResourceLittleFish() {
        super();
    }

    @Override
    public int getSpawnChance(TileType type) {
        if (type == TileType.SEA) {
            return 25;
        } else {
            return 0;
        }
    }

    @Override
    public ResourceType getType() {
        return ResourceType.SMALL_FISH;
    }

    @Override
    public String getTexturePath() {
        return "LittleFish.png";
    }
}
