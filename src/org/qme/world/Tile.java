package org.qme.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.util.ArrayList;

import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.vis.Perspective;
import org.qme.vis.QRenderable;
import org.qme.vis.ui.Tooltip;
import org.qme.vis.ui.UIComponent;

/**
 * A tile in the world. For now, just has a type
 * and a location. Nothing big.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.world.TileType
 */
public class Tile extends QObject implements QRenderable, UIComponent {
	
	// Location
	int x, y;
	
	// Type
	private TileType type;
	
	/**
	 * Simple generation with coordinates.
	 * @param x x-coordinate of tile.
	 * @param y y-coordinate of tile.
	 */
	public Tile(QApplication app, int x, int y) {
		super(app);
		this.x = x;
		this.y = y;
	}

	/**
	 * Auto-generated method stub.
	 * @return tile type
	 */
	public TileType getType() {
		return type;
	}

	/**
	 * Auto-generated method stub.
	 * @param type new tile type
	 */
	public void setType(TileType type) {
		this.type = type;
	}
	
	/**
	 * Nothing for now
	 */
	public void update(QApplication app) {
		
	}
	
	/**
	 * Get the bounding rectangle
	 */
	public Rectangle getRect() {
		return new Rectangle((x * Perspective.TILE_SIZE) + 10 - application.qiscreen.xOffset,
				(y * Perspective.TILE_SIZE) + 10 - application.qiscreen.yOffset,
				Perspective.TILE_SIZE - 10, Perspective.TILE_SIZE - 10);
	}
	
	@Override
	/**
	 * Draws a square representing the tile.
	 */
	public void render(Graphics g) {
		
		// Getting the right color (this is just a placeholder, we'll have real textures later)
		switch (type) {
		
		case UNGENERATED:
			g.setColor(TileColors.UNGENERATED_COLOR);
			break;
			
		case OCEAN:
			g.setColor(TileColors.OCEAN_COLOR);
			break;
			
		case SEA:
			g.setColor(TileColors.SEA_COLOR);
			break;
			
		case PLAINS:
			g.setColor(TileColors.PLAINS_COLOR);
			break;
			
		case DESERT:
			g.setColor(TileColors.DESERT_COLOR);
			break;
			
		case FOREST:
			g.setColor(TileColors.FOREST_COLOR);
			break;
			
		case MOUNTAIN:
			g.setColor(TileColors.MOUNTAIN_COLOR);
			break;
			
		case HIGH_MOUNTAIN:
			g.setColor(TileColors.HIGH_MOUNTAIN_COLOR);
			break;
			
		case FERTILE_PLAINS:
			g.setColor(TileColors.FERTILE_PLAINS_COLOR);
			break;
		
		}
		
		Rectangle rect = getRect();
		
		// Now, let's draw.
		g.fillRect(
			rect.x, rect.y, rect.width, rect.height
		);
		
		// (Maybe) draw the outline
		if (hoveredOver) {
			g.setColor(Color.BLACK);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
		}
		
	}
	
	@Override
	/**
	 * Does nothing
	 */
	public void mouseClickOff() {
		
	}
	
	@Override
	/**
	 * Makes a tooltip
	 * @author adamhutchings
	 */
	public void mouseClickOn() {
		
		if (!tooltip) {
			
			@SuppressWarnings("serial")
			ArrayList<String> info = new ArrayList<String>() {{
				add("Tile type");
			}};
			
			new Tooltip(this,
				MouseInfo.getPointerInfo().getLocation().x,
				MouseInfo.getPointerInfo().getLocation().y - 50,
				application, info
			);
			
		}
		
	}
	
	@Override
	/**
	 * Does nothing
	 */
	public void mouseHoverOn() {
		
	}
	
	@Override
	/**
	 * Does nothing
	 */
	public void mouseHoverOff() {
		
	}
	
	@Override
	/**
	 * Returns whether said click is inside the tile.
	 */
	public boolean clickIsIn(int x, int y) {
		return getRect().contains(x, y - 45);
	}

}
