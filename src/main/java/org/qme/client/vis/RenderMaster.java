package org.qme.client.vis;

import org.qme.client.vis.tex.TextureManager;
import org.qme.world.Tile;
import org.qme.world.TileType;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

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
	 * lowest is the smallest amount of zoom, the farthest out
	 */
	public static final int LOWEST = 2;
	public static final int HIGHEST = 10;

	/**
	 * The amount to zoom in and out
	 */
	public static final float ZOOM_IN = 1.1F;
	public static final float ZOOM_OUT = 0.9F;

	/**
	 * The size of the tiles
	 */
	public static final float TILE_SIZE = 20;

	/**
	 * The amount  of spacing added in between each tile origin the gap between the tiles would effectively be TILE_SIZE-TILE_SPACING
	 */
	public static final float TILE_SPACING = 22;

	/**
	 * Check if a tile is in frame
	 */
	public static boolean isInFrame(double x, double y) {
		double screenLeft = - 200;
		double screenRight = WindowManager.size;

		double screenBottom = - 200;
		double screenTop = WindowManager.size;

		if (x > screenLeft && x < screenRight) {
			if (y > screenBottom && y < screenTop) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Draw a given tile.
	 * @param tile the tile to draw
	 */
	public static void drawTile(Tile tile) {

		int tileX = (int) (((tile.x) * TILE_SPACING * zoom) - WindowManager.getWindowX());
		int tileY = (int) (((tile.y) * TILE_SPACING * zoom) - WindowManager.getWindowY());

		if (isInFrame(tileX, tileY)) {
			// TEMP
			drawQuad(
					tileX, tileY,
					tileX + (int) (TILE_SIZE * zoom), tileY,
					tileX + (int) (TILE_SIZE * zoom), tileY + (int) (TILE_SIZE * zoom),
					tileX, tileY + (int) (TILE_SIZE * zoom),
					getTexture(tile.type)
			);
		}
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
	 * @param tex texture id of quad
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
			int tex
	) {
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, TextureManager.textures.get(tex));
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		glBegin(GL_QUADS);
			glTexCoord2f(1, 1);
			glVertex2f(vert1X, vert1Y);

			glTexCoord2f(0, 1);
			glVertex2f(vert2X, vert2Y);

			glTexCoord2f(0, 0);
			glVertex2f(vert3X, vert3Y);

			glTexCoord2f(1, 0);
			glVertex2f(vert4X, vert4Y);
		glEnd();
		glDisable(GL_TEXTURE_2D);
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

	/**
	 * Get a texture id from a tile type
	 * @param type which tile type
	 * @return the texture id of the tile
	 */
	private static int getTexture(TileType type) {
		switch (type) {
			case FOREST:
				return 1;
			case PLAINS:
				return 2;
			case FERTILE_PLAINS:
				return 3;
			case MOUNTAIN:
				return 4;
			case OCEAN:
				return 5;
			case SEA:
				return 6;
			case HIGH_MOUNTAIN:
				return 7;
			case DESERT:
				return 8;
			default:
				return 0;
		}
	}

}
