package org.qme.client.vis;

import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.Scrolling;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.world.Tile;
import org.qme.world.TileType;

import static org.lwjgl.opengl.GL11.*;

/**
 * Class containing all main rendering utilities.
 * @author adamhutchings, jakeroggenbuck
 * @since 0.1.0
 */
public final class RenderMaster {

	private RenderMaster() {}

	public static float zoom = 10;

	// When texture packs are added this should be changed per pack inorder to allow for different sized tiles
	public static final float TILE_SIZE = 64;
	public static final float TILE_GAP = 0;
	public static final float TILE_X_OFFSET = 32;
	public static final float TILE_Y_OFFSET = 15;

	/**
	 * Check if a tile is in frame
	 * @param x the x position of the tile
	 * @param y the y position of the tile
	 * @returns if the tile is in frame
	 */
	public static boolean isInFrame(double x, double y) {
		double screenLeft = - 600;
		double screenRight = GLFWInteraction.getSize();

		double screenBottom = - 600;
		double screenTop = GLFWInteraction.getSize();

		if (x > screenLeft && x < screenRight) {
			return y > screenBottom && y < screenTop;
		}
		return false;
	}

	/**
	 * Draw a given tile.
	 * @param tile the tile to draw
	 */
	public static void drawTile(Tile tile) {

		int tileX = (int) ((tile.x * TILE_X_OFFSET * zoom) - (tile.y * TILE_X_OFFSET * zoom) - Scrolling.getXOffset());
		int tileY = (int) ((tile.y * TILE_Y_OFFSET * zoom) + (tile.x * TILE_Y_OFFSET * zoom) - Scrolling.getYOffset());

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
		glEnable(GL_BLEND);
		glBindTexture(GL_TEXTURE_2D, tex);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

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
		glDisable(GL_BLEND);
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
		return TextureManager.getTexture(texString);
	}
}
