package org.qme.game.structure;

import java.util.ArrayList;
import java.util.Random;

import org.qme.world.Tile;
import org.qme.world.TileType;
import org.qme.world.res.Resource;
import org.qme.world.res.ResourceType;

public class Mineshaft extends Structure {

    public Mineshaft(Tile tile) {
        super(tile, StructureType.MINESHAFT);
    }

    public ArrayList<ResourceType> getCost() {
        ArrayList<ResourceType> r = new ArrayList<ResourceType>();
        for(int i = 0; i < 10; i++) {
            r.add(ResourceType.LUMBER);
        }
        return r;
    }

    @Override
    public boolean placeable(Tile tile) {
        return tile.resources.isEmpty() && (tile.type == TileType.MOUNTAIN || tile.type == TileType.HIGH_MOUNTAIN);
    }

    public ResourceType miningFor;

    @Override
    public void rollResources() {
        // Random fun times
        Random rand = new Random();
        final int roll = rand.nextInt(100);

        // Set up the resources
        if(this.tile.type == TileType.HIGH_MOUNTAIN) {
            // This always happens
            this.tile.resources.add(new Resource(ResourceType.SNOW));
            if (roll < 35) {
                this.tile.resources.add(new Resource(ResourceType.ROCK));
            }
            // Altered probabilities
            if (miningFor == ResourceType.GOLD_ORE) {
                if (roll < 30) {
                    this.tile.resources.add(new Resource(ResourceType.GOLD_ORE));
                }
                if (roll < 20) {
                    this.tile.resources.add(new Resource(ResourceType.IRON_ORE));
                }
                if (roll < 10) {
                    this.tile.resources.add(new Resource(ResourceType.COAL));
                }
            } else if (miningFor == ResourceType.IRON_ORE) {
                if (roll < 10) {
                    this.tile.resources.add(new Resource(ResourceType.GOLD_ORE));
                }
                if (roll < 60) {
                    this.tile.resources.add(new Resource(ResourceType.IRON_ORE));
                }
                if (roll < 10) {
                    this.tile.resources.add(new Resource(ResourceType.COAL));
                }
            } else if (miningFor == ResourceType.COAL) {
                if (roll < 10) {
                    this.tile.resources.add(new Resource(ResourceType.GOLD_ORE));
                }
                if (roll < 20) {
                    this.tile.resources.add(new Resource(ResourceType.IRON_ORE));
                }
                if (roll < 30) {
                    this.tile.resources.add(new Resource(ResourceType.COAL));
                }
            } else {
                this.tile.rollResources();
            }
        } else {
            // This is unaltered
            if (roll < 80) {
                this.tile.resources.add(new Resource(ResourceType.ROCK));
            }
            if (roll < 20) {
                this.tile.resources.add(new Resource(ResourceType.SNOW));
            }

            // Altered probabilities
            if (miningFor == ResourceType.GOLD_ORE) {
                if (roll < 15) {
                    this.tile.resources.add(new Resource(ResourceType.GOLD_ORE));
                }
                if (roll < 20) {
                    this.tile.resources.add(new Resource(ResourceType.IRON_ORE));
                }
                if (roll < 25) {
                    this.tile.resources.add(new Resource(ResourceType.COAL));
                }
            } else if (miningFor == ResourceType.IRON_ORE) {
                if (roll < 5) {
                    this.tile.resources.add(new Resource(ResourceType.GOLD_ORE));
                }
                if (roll < 60) {
                    this.tile.resources.add(new Resource(ResourceType.IRON_ORE));
                }
                if (roll < 25) {
                    this.tile.resources.add(new Resource(ResourceType.COAL));
                }
            } else if (miningFor == ResourceType.COAL) {
                if (roll < 5) {
                    this.tile.resources.add(new Resource(ResourceType.GOLD_ORE));
                }
                if (roll < 20) {
                    this.tile.resources.add(new Resource(ResourceType.IRON_ORE));
                }
                if (roll < 75) {
                    this.tile.resources.add(new Resource(ResourceType.COAL));
                }
            } else {
                this.tile.rollResources();
            }
        }
    }
}
