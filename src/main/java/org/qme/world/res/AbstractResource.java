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
     * @return
     */
    public static int getSpawnChance(ResourceType type, TileType tileType) {
        switch (type) {
            case COAL:
                    switch (tileType) {
                        case MOUNTAIN: return 50;
                        case HIGH_MOUNTAIN: return 20;
                        default: return 0;
                    }
            case FRUIT:
                    switch (tileType) {
                        case FOREST: return 20;
                        default: return 0;
                    }
            case GOLD_ORE:
                    switch (tileType) {
                        case MOUNTAIN: return 10;
                        case HIGH_MOUNTAIN: return 20;
                        default: return 0;
                    }
            case GRAPE:
                    switch (tileType) {
                        case FERTILE_PLAINS: return 10;
                        default: return 0;
                    }
            case GRASS:
                    switch (tileType) {
                        case PLAINS: return 70;
                        default: return 0;
                    }
            case LEAF:
                    switch (tileType) {
                        case FOREST: return 80;
                        default: return 0;
                    }
            case SALT:
                    switch (tileType) {
                        case OCEAN: return 200;
                        default: return 0;
                    }
            case SEA_WEED:
                    switch (tileType) {
                        case OCEAN: return 30;
                        case SEA: return 25;
                        default: return 0;
                    }
            case TALL_GRASS:
                    switch (tileType) {
                        case FERTILE_PLAINS: return 50;
                        default: return 0;
                    }
        };
        return 0;
    }

    public ResourceType getType() {
        return type;
    }

    public String getTexturePath() {
        return "items/" + (type.name().toLowerCase()) + ".png";
    }
}
