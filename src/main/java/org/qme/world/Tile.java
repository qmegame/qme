package org.qme.world;

import java.util.ArrayList;
import java.util.Random;

import org.qme.client.vis.Layer;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.gui.GUIManager;
import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.client.vis.wn.Scrolling;
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

	@Override
	public Layer layer() {
		return Layer.WORLD;
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

	public int scaledTilePosX() {
		double scaledOffsetX = RenderMaster.TILE_X_OFFSET * RenderMaster.zoom;
		return (int) ((this.x * scaledOffsetX) - (this.y * scaledOffsetX) - Scrolling.getXOffset());
	}

	public int scaledTilePosY() {
		double scaledOffsetY = RenderMaster.TILE_Y_OFFSET * RenderMaster.zoom;
		return (int) ((this.y * scaledOffsetY) + (this.x * scaledOffsetY) - Scrolling.getYOffset());
	}

	@Override
	public boolean contains(int x, int y) {

		// TODO: Improve this garbage

		if (GUIManager.resourcesUI.box.contains(x, y) && GUIManager.resourcesUI.isVisible()) {
			return false;
		}

		if (GUIManager.optionsUI.isVisible() || GUIManager.pauseUI.isVisible()) {
			return false;
		}


		int tileX = this.scaledTilePosX();
		int tileY = this.scaledTilePosY();

		int tileSizeActual = (int) (RenderMaster.TILE_SIZE * RenderMaster.zoom);

		double thirdTileSizeActual = tileSizeActual / 3.0;
		double twoThirdsTileSizeActual = tileSizeActual * 2.0 / 3.0;

		int size = GLFWInteraction.getSize() - y;
		return x > tileX + (thirdTileSizeActual) &&
						x < tileX + (twoThirdsTileSizeActual) &&
						size > tileY + (thirdTileSizeActual) &&
						size < tileY + (twoThirdsTileSizeActual);

	}

	@Override
	public void mouseClickOff(int x, int y) {
		this.setVisible(true);
		GUIManager.resourcesUI.showFor(this);
		GUIManager.resourcesUI.whatToHarvest(this);
	}

	@Override
	public void mouseClickOn(int x, int y) {
		this.setVisible(false);
	}
}
