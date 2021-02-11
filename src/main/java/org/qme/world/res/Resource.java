package org.qme.world.res;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.qme.client.vis.tex.TextureManager;
import org.qme.world.TileType;

import java.io.InputStream;
import java.util.HashMap;

/**
 * An abstract class for resources
 * @author jakeroggenbuck
 * @since 0.3.0
 */
public class Resource {

    /**
     * Which type it is
     */
    public ResourceType type;

    private static final HashMap<ResourceType, HashMap<TileType, Integer>> spawnChances = loadLootTable();

    public Resource(ResourceType type) {
        this.type = type;
    }

    /**
     * Spawn chance is a whole number, 0-100
     * @param type the resource to check
     * @param tileType the tile to check
     * @return the chance of spawning
     */
    public static int getSpawnChance(ResourceType type, TileType tileType) {

        if (!spawnChances.containsKey(type)) {
            return 0;
        }

        if (!spawnChances.get(type).containsKey(tileType)) {
            return 0;
        }

        return spawnChances.get(type).get(tileType);
    }

    /**
     * Loads the loot tables from spawn_tables.json
     * @return loaded loot tables
     */
    private static HashMap<ResourceType, HashMap<TileType, Integer>> loadLootTable() {

        HashMap<ResourceType, HashMap<TileType, Integer>> toReturn = new HashMap<>();

        InputStream stream = TextureManager.class.getResourceAsStream("/misc/spawn_tables.json");
        JSONObject types = new JSONObject(new JSONTokener(stream));

        if (types == null) {
            return toReturn;
        }

        for (String resourceType : types.keySet()) {
            JSONObject chances = types.getJSONObject(resourceType);

            HashMap<TileType, Integer> chancesMap = new HashMap<>();

            for (String tileType : chances.keySet()) {
                chancesMap.put(TileType.valueOf(tileType.toUpperCase()), chances.getInt(tileType));
            }

            toReturn.put(ResourceType.valueOf(resourceType.toUpperCase()), chancesMap);
        }

        return toReturn;
    }

    public ResourceType getType() {
        return type;
    }

    public String getTexturePath() {
        return "items/" + (type.name().toLowerCase()) + ".png";
    }
}
