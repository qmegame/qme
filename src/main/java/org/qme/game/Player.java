package org.qme.game;

import org.qme.world.res.ResourceType;

/**
 * This represents a player (for now) with tallies of how many of each resource
 * the player has. The game creates one player for now, although more will be
 * added in the future.
 * @author adamhutchings
 * @since 0.4
 */
public class Player {

    /**
     * An array. The zero-th index is how many of the first element in the
     * ResourceType enum the player has, etc.
     */
    private int[] resourceCounts;

    public Player() {
        resourceCounts = new int[ ResourceType.values().length ];
        // I think Java does this automatically but just checking
        for (int i = 0; i < ResourceType.values().length; i++) {
            resourceCounts[i] = 0;
        }
    }

    public int getCount(ResourceType type) {
        return resourceCounts[type.ordinal()];
    }

    public void incrementCount(ResourceType type) {
        ++resourceCounts[type.ordinal()];
    }

}
