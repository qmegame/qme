package org.qme.client;

import org.qme.client.vis.WindowManager;
import org.qme.client.vis.gui.QFont;
import org.qme.client.vis.gui.QLabel;
import org.qme.world.World;

import java.awt.*;

import static java.awt.Font.PLAIN;

/**
 * The "controller", so to speak, of all events. It also helps to validate
 * requests from the player or the game, because instead of acting on anything
 * directly, all UI components (should) send in a request to the global
 * Application instance. For when QME goes online at some later date (hopefully)
 * this model will serve better, because request objects can be sent to a remote
 * location more easily.
 * @author adamhutchings
 * @since preA
 */
public final class Application {

	private final QLabel fpsLabel;
	private int fps;
	private long lastSecond;

	/**
	 * The constructor is private. Only one instance allowed.
	 */
	private Application() {
		new World();
		QFont mono = new QFont(new Font(Font.MONOSPACED, PLAIN, 16), true);
		fpsLabel = new QLabel(mono, "...", 2, WindowManager.windowSize() - 21);
	}
	
	/**
	 * The global instance of application.
	 */
	public static final Application app = new Application();
	
	/**
	 * Run the application forever (or until an exit request is sent)
	 */
	public void mainloop() {
		
		while (WindowManager.shouldBeOpen()) {
			
			WindowManager.repaint();

			// Track fps
			if (System.currentTimeMillis() - lastSecond > 1000) {
				fpsLabel.text = "FPS: " + fps;
				fps = 0;
				lastSecond = System.currentTimeMillis();
			} else {
				fps++;
			}

		
		}
		
	}

}
