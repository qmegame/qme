package org.qme.vis;

import static org.lwjgl.opengl.GL32.*;

/**
 * Some simple code for interfacing with GL, and the 3/4 perspective QME uses to
 * render the world.
 * @author adamhutchings
 * @since preA
 */
public final class RenderUtil {
	
	/**
	 * No instantiation! Made protected just out of spite.
	 */
	protected RenderUtil() {
		
	}
	
	/**
	 * Render a quad on-screen. This needs to render two triangles, as GL_QUADS
	 * is definitely not recommended.
	 * @param quad the input quad
	 * @param color the input color
	 */
	public static void renderColoredQuad(Quad quad, Color color) {
		
		glColor4f(color.r, color.g, color.b, color.a);

		// Render upper-right, upper-left, lower-right
		glBegin(GL_TRIANGLES);
		glVertex2f(quad.vert0.x, quad.vert0.y);
		glVertex2f(quad.vert1.x, quad.vert1.y);
		glVertex2f(quad.vert3.x, quad.vert3.y);
		glEnd();
		
		// Render upper-left, lower-left, lower-right
		glBegin(GL_TRIANGLES);
		glVertex2f(quad.vert1.x, quad.vert1.y);
		glVertex2f(quad.vert2.x, quad.vert2.y);
		glVertex2f(quad.vert3.x, quad.vert3.y);
		glEnd();
		
	}
	
	/**
	 * Usage as a render coordinate, from -1.0f to 1.0f in both directions.
	 * @author adamhutchings
	 * @since preA
	 */
	public static class Vertex {
		
		/**
		 * The x coordinate
		 */
		public final float x;
		
		/**
		 * The y coordinate
		 */
		public final float y;
		
		/**
		 * Initialize with given values
		 * @param x the x input
		 * @param y the y input
		 */
		public Vertex(float x, float y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	/**
	 * A quad with four vertices, to render objects on. Starts with upper right
	 * vertex and goes counterclockwise.
	 * @author adamhutchings
	 * @since preA
	 */
	public static class Quad {
		
		/**
		 * Upper right vertex
		 */
		public final Vertex vert0;
		
		/**
		 * Upper left vertex
		 */
		public final Vertex vert1;
		
		/**
		 * Lower left vertex
		 */
		public final Vertex vert2;
		
		/**
		 * Lower right vertex
		 */
		public final Vertex vert3;
		
		/**
		 * Initialize all members
		 * @param v0 upper right vertex
		 * @param v1 upper left vertex
		 * @param v2 lower left vertex
		 * @param v3 lower right vertex
		 */
		public Quad(Vertex v0, Vertex v1, Vertex v2, Vertex v3) {
			vert0 = v0;
			vert1 = v1;
			vert2 = v2;
			vert3 = v3;
		}
		
	}
	
	/**
	 * A color with RGBA components
	 * @author adamhutchings
	 *
	 */
	public static class Color {
		
		/**
		 * R component
		 */
		public final float r;
		
		/**
		 * G component
		 */
		public final float g;
		
		/**
		 * B component
		 */
		public final float b;
		
		/**
		 * A component
		 */
		public final float a;
		
		/**
		 * Input all data values.
		 * @param r red value
		 * @param g green value
		 * @param b blue value
		 * @param a alpha value
		 */
		public Color(float r, float g, float b, float a) {
			this.r = r;
			this.g = g;
			this.b = b;
			this.a = a;
		}
		
		/**
		 * Input all data values, except for alpha which is 0.0 (opaque)
		 * @param r red value
		 * @param g green value
		 * @param b blue value
		 */
		public Color(float r, float g, float b) {
			this(r, g, b, 0.0f);
		}
		
	}

}
