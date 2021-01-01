package org.qme.client.vis;

import static org.lwjgl.opengl.GL11.*;

/**
 * Class for static rendering routines.
 * @author adamhutchings
 * @since preA
 */
public final class Renderer {
	
	/**
	 * No constructing!
	 */
	private Renderer() {
		
	}
	
	/**
	 * Draw a quad of a given color.
	 * @param x the x-locationn of the middle.
	 * @param y the y-location of the middle.
	 * @param size the size of the quad.
	 */
	public static void renderQuad(int x, int y, int size) {
		glBegin(GL_QUADS);
			glVertex2f(x + size / 2, y + size / 2);
			glVertex2f(x - size / 2, y + size / 2);
			glVertex2f(x - size / 2, y - size / 2);
			glVertex2f(x + size / 2, y - size / 2);
		glEnd();
	}

}
