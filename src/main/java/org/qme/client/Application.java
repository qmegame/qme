package org.qme.client;

import org.qme.client.vis.gui.QBox;
import org.qme.client.vis.gui.QFont;
import org.qme.client.vis.gui.QLabel;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.io.Logger;
import org.qme.io.Severity;
import org.qme.world.World;

import java.awt.*;

import static org.lwjgl.opengl.GL11C.*;

/**
 * The "controller", so to speak, of all events. It also helps to validate
 * requests from the player or the game, because instead of acting on anything
 * directly, all UI components (should) send in a request to the global
 * Application instance. For when QME goes online at some later date (hopefully)
 * this model will serve better, because request objects can be sent to a remote
 * location more easily.
 * @author adamhutchings
 * @since 0.1.0
 */
public final class Application {

	private int frameCount;
	private int fps;
	private long lastSecond;

	private static final String GAME_VERSION = Application.class.getPackage().getSpecificationVersion();
	private static final String JAVA_VERSION = System.getProperty("java.version");
	private static final String JAVA_VENDOR = System.getProperty("java.vendor");
	private static final String OPERATING_SYSTEM = System.getProperty("os.name");
	private static final String OPERATING_SYSTEM_VERSION = System.getProperty("os.version");
	private static final String ARCH_TYPE = System.getProperty("os.arch");
	private static String GPU_VENDOR;
	private static String GPU_RENDERER;

	public static QBox box;
	public static QLabel debugLabel;

	public static final int RENDER_SCALE = 3;

	/**
	 * The constructor is private. Only one instance allowed.
	 */
	private Application() {
		new World();

		if (GAME_VERSION == null) {
			Logger.log("Could not detect game version! The jar you are running was not compiled properly.", Severity.WARNING);
		}

		QFont font = new QFont(new Font(Font.MONOSPACED, Font.BOLD, 12), true);

		// Resources GUI
		box = new QBox(new Rectangle(5,5, 100, 120));
		box.setVisible(false);

		// Debug Label
		debugLabel = new QLabel(font, "...", 5, GLFWInteraction.windowSize() - (font.getHeight() + 2), true);
		debugLabel.setVisible(false);

		// Update debug information
		GPU_VENDOR = glGetString(GL_VENDOR);
		GPU_RENDERER = glGetString(GL_RENDERER);
	}

	/**
	 * The global instance of application.
	 */
	public static final Application app = new Application();
	
	/**
	 * Run the application forever (or until an exit request is sent)
	 */
	public void mainloop() {
		
		while (GLFWInteraction.shouldBeOpen()) {
			
			GLFWInteraction.repaint();

			// Updates debug label each frame
			debugLabel.text = "Running game version v" + GAME_VERSION + "\nFPS: " + fps + " (On: " + frameCount + ")\nJava: " + JAVA_VERSION + " (Vendor: " +  JAVA_VENDOR + ")" + "\nOS: " + OPERATING_SYSTEM + " (Arch: " + ARCH_TYPE + ") (Version: " + OPERATING_SYSTEM_VERSION + ") \nGL: " + GPU_VENDOR + " " + GPU_RENDERER;

			// Checks fps
			if (System.currentTimeMillis() - lastSecond > 1000) {
				fps = frameCount;
				frameCount = 0;
				lastSecond = System.currentTimeMillis();
			} else {
				frameCount++;
			}

		
		}
		
	}

}
