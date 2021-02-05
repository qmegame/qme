package org.qme.world;

import java.util.ArrayList;
import java.util.Random;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.Renderable;
import org.qme.client.vis.wn.WindowManager;
import org.qme.world.res.*;

/**
 * Represents a single tile in the world. As with other objects, rendering and
 * bounds detection code will not be in this class, and rather with other
 * rendering / bounds detection code, etc.
 * @author adamhutchings, santiago
 * @since 0.1.0
 */
public class Tile implements Renderable {

	public final int x;
	public final int y;
	public final TileType type;

	public ArrayList<AbstractResource> resources = new ArrayList<AbstractResource>();
	
	/**
	 * Creates a new instance of a renderable Tile
	 * @param x the x coordinate of the tile in relation to the tile plane (Not in pixels)
	 * @param y the y coordinate of the tile in relation to the tile plane (Not in pixels)
	 * @param type the type of this tile
	 */
	public Tile(int x, int y, TileType type) {
		this.x = x;
		this.y = y;
		this.type = type;
		WindowManager.addObject(this);
	}

	@Override
	public void draw() {
		RenderMaster.drawTile(this);
	}

	/**
	 * Function that should be called for each tile when a resource renewal is needed
	 * @since 0.3.0
	 * @author santiago
	 */
	public void resetResources() {
		// Remove old resources
		this.resources.clear();

		// Provide new resources
		this.rollResources();
	}

	/**
	 * Utility function that gives the tile resources
	 * Only to be used when the tile has no resources
	 * @since 0.3.0
	 * @author santiago
	 */
	private void rollResources() {
		Random rand = new Random();
		final int roll = rand.nextInt(100);

		// Check each resource
		// Ocean
		final ResourceBigFish bigFish = new ResourceBigFish();
		if(roll < bigFish.getSpawnChance(this.type)) {
			this.resources.add(bigFish);
		}
		final ResourceSeaweed seaweed = new ResourceSeaweed();
		if(roll < seaweed.getSpawnChance(this.type)) {
			this.resources.add(seaweed);
		}
		final ResourceSalt salt = new ResourceSalt();
		if(this.resources.get(0).getType() != ResourceType.BigFish
				&& this.resources.get(0).getType() != ResourceType.Seaweed) {
			this.resources.add(salt);
		}

		// Sea
		final ResourceLittleFish littleFish = new ResourceLittleFish();
		if(roll < littleFish.getSpawnChance(this.type)) {
			this.resources.add(littleFish);
		}
		final ResourceSmallShark smallShark = new ResourceSmallShark();
		if(roll < smallShark.getSpawnChance(this.type)) {
			this.resources.add(smallShark);
		}
		final ResourceSeagull seagull = new ResourceSeagull();
		if(this.resources.get(0).getType() != ResourceType.LittleFish
				&& this.resources.get(0).getType() != ResourceType.SmallShark
				&& this.resources.get(0).getType() != ResourceType.Seaweed) {
			this.resources.add(seagull);
		}

		// Plains
		final ResourceGrain grain = new ResourceGrain();
		if(roll < grain.getSpawnChance(this.type)) {
			this.resources.add(grain);
		}
		final ResourceGrass grass = new ResourceGrass();
		if(roll < grass.getSpawnChance(this.type)) {
			this.resources.add(grass);
		}
		final ResourceSmallGame smallGame = new ResourceSmallGame();
		if(roll < smallGame.getSpawnChance(this.type)) {
			this.resources.add(smallGame);
		}

		// Fertile plains
		final ResourceMediumGame mediumGame = new ResourceMediumGame();
		if(roll < mediumGame.getSpawnChance(this.type)) {
			this.resources.add(mediumGame);
		}
		final ResourceTallGrass tallGrass = new ResourceTallGrass();
		if(roll < tallGrass.getSpawnChance(this.type)) {
			this.resources.add(tallGrass);
		}
		final ResourceGrapes grapes = new ResourceGrapes();
		if(roll < grapes.getSpawnChance(this.type)) {
			this.resources.add(grapes);
		}

		// Forest
		final ResourceLumber lumber = new ResourceLumber();
		if(roll < lumber.getSpawnChance(this.type)) {
			this.resources.add(lumber);
		}
		final ResourceLeaf leaf = new ResourceLeaf();
		if(roll < leaf.getSpawnChance(this.type)) {
			this.resources.add(leaf);
		}
		final ResourceFruit fruit = new ResourceFruit();
		if(roll < fruit.getSpawnChance(this.type)) {
			this.resources.add(fruit);
		}
		final ResourceSap sap = new ResourceSap();
		if(roll < sap.getSpawnChance(this.type)) {
			this.resources.add(sap);
		}
		final ResourceLargeGame largeGame = new ResourceLargeGame();
		if(roll < largeGame.getSpawnChance(this.type)) {
			this.resources.add(largeGame);
		}

		// Jungle

		// High Mountains
		final ResourceRock rock = new ResourceRock();
		if(roll < rock.getSpawnChance(this.type)) {
			this.resources.add(rock);
		}
		final ResourceSnow snow = new ResourceSnow();
		if(roll < snow.getSpawnChance(this.type)) {
			this.resources.add(snow);
		}
		final ResourceCoal coal = new ResourceCoal();
		if(roll < coal.getSpawnChance(this.type)) {
			this.resources.add(coal);
		}
		final ResourceIronOre ironOre = new ResourceIronOre();
		if(roll < ironOre.getSpawnChance(this.type)) {
			this.resources.add(ironOre);
		}
		final ResourceGoldOre goldOre = new ResourceGoldOre();
		if(roll < goldOre.getSpawnChance(this.type)) {
			this.resources.add(goldOre);
		}

		// Mountains

		// Desert
		final ResourceSand sand = new ResourceSand();
		if(roll < sand.getSpawnChance(this.type)) {
			this.resources.add(sand);
		}
		final ResourceCacteye cacti = new ResourceCacteye();
		if(roll < cacti.getSpawnChance(this.type)) {
			this.resources.add(cacti);
		}
	}
}
