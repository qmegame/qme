package org.qme.client.vis;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

import org.qme.world.Tile;

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
	 * Draw a given tile.
	 * @param tile the tile to draw
	 */
	public static void drawTile(Tile tile) {
		// TEMP
		drawQuad(
			100,
			100,
			300,
			100,
			300,
			300,
			100,
			300,
			new Color(100, 0, 0)
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

}
