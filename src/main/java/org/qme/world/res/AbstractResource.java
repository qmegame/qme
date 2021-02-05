package org.qme.world.res;

import org.qme.world.TileType;

/**
 * An abstract class for resources
 * @author jakeroggenbuck
 * @since 0.3.0
 */
public class AbstractResource {

    /**
     * Which type it is
     */
    public ResourceType type;

    public AbstractResource(ResourceType type) {
        this.type = type;
    }

    /**
     * Spawn chance is a whole number, 0-100
     * @param type which tile it spawns on
     * @return
     */
    public int getSpawnChance(TileType type) {
        return 0;
    }

    public ResourceType getType() {
        return type;
    }

    public String getTexturePath() {
        return "items/" + (type.name().toLowerCase()) + ".png";
    }
}
