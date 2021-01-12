package org.qme.client.vis;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.qme.world.Tile;
import org.qme.world.TileType;

/**
 * Class containing all main rendering utilities.
 * @author adamhutchings
 * @since preA
 */
public final class RenderMaster {
	
	/**
	 * No instantiation
	 */
	private RenderMaster() {}
	
	/**
	 * The zoom for the tiles
	 */
	public static double zoom = 10;

	/**
	 * The size of the tiles
	 */
	public static final float TILE_SIZE = 20;

	/**
	 * The amount  of spacing added in between each tile origin the gap between the tiles would effectively be TILE_SIZE-TILE_SPACING
	 */
	public static final float TILE_SPACING = 22;

	/**
	 * Draw a given tile.
	 * @param tile the tile to draw
	 */
	public static void drawTile(Tile tile) {
		
		int tileX = (int) (((tile.x) * TILE_SPACING * zoom) - WindowManager.getWindowX());
		int tileY = (int) (((tile.y) * TILE_SPACING * zoom) - WindowManager.getWindowY());

		int size = WindowManager.size()/2;

		// Marks center of screen for testing zooming
		drawQuad(size - 5, size - 5,
				size + 5, size - 5,
				size + 5, size + 5,
				size - 5, size + 5,
				new Color(100, 100, 100, 100));

		// TEMP
		drawQuad(
			tileX, tileY,
			tileX + (int) (TILE_SIZE * zoom), tileY,
			tileX + (int) (TILE_SIZE * zoom), tileY + (int) (TILE_SIZE * zoom),
			tileX, tileY + (int) (TILE_SIZE * zoom),
			getColor(tile.type)
		);
		
	}
	
	/**
	 * Draw a quad with eight given positions. Position should go in
	 * counterclockwise order!
	 * @param vert1X x position of the first vertex
	 * @param vert1Y y position of the first vertex
	 * @param vert2X x position of the second vertex
	 * @param vert2Y y position of the second vertex
	 * @param vert3X x position of the third vertex
	 * @param vert3Y y position of the third vertex
	 * @param vert4X x position of the fourth vertex
	 * @param vert4Y y position of the fourth vertex
	 * @param color the color to draw with (later a texture)
	 */
	private static void drawQuad(
			int vert1X,
			int vert1Y,
			int vert2X,
			int vert2Y,
			int vert3X,
			int vert3Y,
			int vert4X,
			int vert4Y,
			Color color
	) {
		glColor4f(
				((float) color.getRed()) / 256f,
				((float) color.getGreen()) / 256f,
				((float) color.getBlue()) / 256f,
				((float) color.getAlpha()) / 256f
		);
		glBegin(GL_TRIANGLES);
			glVertex2f(vert1X, vert1Y);
			glVertex2f(vert2X, vert2Y);
			glVertex2f(vert3X, vert3Y);
		glEnd();
		glBegin(GL_TRIANGLES);
			glVertex2f(vert1X, vert1Y);
			glVertex2f(vert3X, vert3Y);
			glVertex2f(vert4X, vert4Y);
		glEnd();
	}
	
	/**
	 * Get the color to render from a type
	 * @param type which tile type
	 * @return the color
	 */
	private static Color getColor(TileType type) {
		switch (type) {
		case DESERT:
			return new Color(200, 200, 0);
		case FERTILE_PLAINS:
			return new Color(0, 250, 0);
		case FOREST:
			return new Color(0, 200, 0);
		case HIGH_MOUNTAIN:
			return new Color(75, 75, 75);
		case MOUNTAIN:
			return new Color(150, 150, 150);
		case OCEAN:
			return new Color(0, 0, 100);
		case PLAINS:
			return new Color(100, 200, 0);
		case SEA:
			return new Color(0, 175, 175);
		default:
			return new Color(100, 0, 0); // invalid
		}
	}

}
