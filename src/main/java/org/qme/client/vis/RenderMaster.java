package org.qme.client.vis;

import org.qme.client.vis.tex.TextureManager;
import org.qme.world.Tile;
import org.qme.world.TileType;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Class containing all main rendering utilities.
 * @author adamhutchings, jakeroggenbuck
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
	public static float zoom = 10;

	/**
	 * lowest is the smallest amount of zoom, the farthest out
	 */
	public static final int LOWEST = 1;
	public static final int HIGHEST = 8;

	/**
	 * The amount to zoom in and out
	 */
	public static final float ZOOM_IN = 1.1F;
	public static final float ZOOM_OUT = 0.9F;

	/**
	 * The size of the tiles
	 */
	public static final float TILE_SIZE = 32;

	/**
	 * The amount  of spacing added in between each tile origin the gap between the tiles would effectively be TILE_SIZE-TILE_SPACING
	 */
	public static final float TILE_SPACING = 0.5f;

	/**
	 * Check if a tile is in frame
	 */
	public static boolean isInFrame(double x, double y) {
		double screenLeft = - 400;
		double screenRight = WindowManager.size;

		double screenBottom = - 400;
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

		float tileSpacingActual = (TILE_SIZE + TILE_SPACING) * zoom;

		int tileX = (int) (((tile.x) * tileSpacingActual) - WindowManager.getWindowX());
		int tileY = (int) (((tile.y) * tileSpacingActual) - WindowManager.getWindowY());

		if (isInFrame(tileX, tileY)) {
			int tileSizeActual = (int) (TILE_SIZE * zoom);
			// TEMP
			drawQuad(
					tileX, tileY,
					tileX + tileSizeActual, tileY,
					tileX + tileSizeActual, tileY + tileSizeActual,
					tileX, tileY + tileSizeActual,
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
		glBindTexture(GL_TEXTURE_2D, tex);
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
	 * Draws a specific region of a texture
	 * @param originX the bottom left x coordinate for the origin point for the texture
	 * @param originY the bottom left y coordinate for the origin point for the texture
	 * @param texX the bottom left x coordinate for the location on the texture
	 * @param texY the bottom left y coordinate for the location on the texture
	 * @param originWidth the width of the image to render
	 * @param originHeight the height of the image to render
	 * @param texWidth the width of the texture
	 * @param texHeight the height of the texture
	 */
	public static void drawRegion(float originX, float originY, float texX, float texY, float originWidth, float originHeight, float texWidth, float texHeight) {
		/* Vertex positions */
		float x1 = originX;
		float y1 = originY;
		float x2 = originX + originWidth;
		float y2 = originY + originHeight;

		/* Texture coordinates */
		float s1 = texX / texWidth;
		float t1 = texY / texHeight;
		float s2 = (texX + originWidth) / texWidth;
		float t2 = (texY + originHeight) / texHeight;

		glBegin(GL_QUADS);
			glTexCoord2f(s1, t1);
			glVertex2f(x1, y1);

			glTexCoord2f(s1, t2);
			glVertex2f(x1, y2);

			glTexCoord2f(s2, t2);
			glVertex2f(x2, y2);

			glTexCoord2f(s2, t1);
			glVertex2f(x2, y1);
		glEnd();
	}

	/**
	 * Get a texture id from a tile type
	 * @param type which tile type
	 * @return the texture id of the tile
	 */
	private static int getTexture(TileType type) {
		String texString =
				// HIGH_MOUNTAIN -> high_mountain -> high-mountain -> high-mountain.png
				type.name().toLowerCase().replace('_', '-') + ".png";
		return TextureManager.textures.get(texString);
	}
}
