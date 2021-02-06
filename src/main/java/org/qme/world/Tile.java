package org.qme.world;

import java.util.ArrayList;
import java.util.Random;

import org.qme.client.Application;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.gui.GUIManager;
import org.qme.client.vis.gui.MouseResponder;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.client.vis.wn.WindowManager;
import org.qme.world.res.*;

/**
 * Represents a single tile in the world. As with other objects, rendering and
 * bounds detection code will not be in this class, and rather with other
 * rendering / bounds detection code, etc.
 * @author adamhutchings, santiago
 * @since 0.1.0
 */
public class Tile extends UIComponent {

	public final int x;
	public final int y;
	public final TileType type;

	public ArrayList<Resource> resources = new ArrayList<Resource>();
	
	/**
	 * Creates a new instance of a renderable Tile
	 * @param x the x coordinate of the tile in relation to the tile plane (Not in pixels)
	 * @param y the y coordinate of the tile in relation to the tile plane (Not in pixels)
	 * @param type the type of this tile
	 */
	public Tile(int x, int y, TileType type) {
		super();
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
		ResourceType[] resourceList = (ResourceType.values());
		for (int i = 0; i < resourceList.length; i++) {
			ResourceType res = resourceList[i];
			if (roll < Resource.getSpawnChance(res, this.type)) {
				// TODO: Seagulls here
				if ( (res != ResourceType.SALT) || (this.resources.size() == 0) )
					this.resources.add(new Resource(res));
			}
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return this.x * RenderMaster.TILE_SIZE + 50 > x && this.x * RenderMaster.TILE_SIZE < x && this.y * RenderMaster.TILE_SIZE + 50 > GLFWInteraction.windowSize() - y && this.y * RenderMaster.TILE_SIZE < GLFWInteraction.windowSize() - y;
	}

	@Override
	public void mouseClickOff() {
		this.setVisible(true);
		GUIManager.resourcesUI.showFor(this);
	}

	@Override
	public void mouseClickOn() {
		this.setVisible(false);
	}
}
