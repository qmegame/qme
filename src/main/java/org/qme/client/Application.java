package org.qme.client;

import org.qme.client.vis.gui.*;
import org.qme.client.vis.gui.comp.QBox;
import org.qme.client.vis.gui.comp.QFont;
import org.qme.client.vis.gui.comp.QLabel;
import org.qme.client.vis.tex.TextureManager;
import org.qme.client.vis.wn.GLFWInteraction;
import org.qme.client.vis.wn.Scrolling;
import org.qme.init.AssetInit;
import org.qme.init.GLInit;
import org.qme.init.PreInit;
import org.qme.io.AudioFiles;
import org.qme.io.AudioPlayer;
import org.qme.io.Logger;
import org.qme.io.Severity;
import org.qme.utils.FramerateManager;
import org.qme.utils.Language;
import org.qme.utils.Performance;
import org.qme.world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

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

	public static boolean running = false;

	private int frameCount;
	private int fps;
	private long lastSecond;

	public static QBox box;
	public static QLabel debugLabel;
	public static QLabel profilerLabel;

	public static final int RENDER_SCALE = 3;

	/**
	 * Make audio player
	 */
	public static AudioPlayer audioPlayer = new AudioPlayer(AudioFiles.menu);

	/**
	 * World
	 */
	public static World world;

  	/**
   	 * All mouse responders
	 */
	private static final ArrayList<MouseResponder> responders = new ArrayList<>();

	/**
	 * Add an object
	 */
	public static void registerMouseResponder(MouseResponder r) {
		responders.add(r);
	}

	/**
	 * Get the responder list
	 */
	public static ArrayList<MouseResponder> getResponders() {
		return responders;
	}

	/**
	 * The constructor is private. Only one instance allowed.
	 */
	private Application() {

		PreInit.init();

		GLInit.init();

		AssetInit.init();

	}

	/**
	 * The global instance of application.
	 */
	public static final Application app = new Application();
	
	/**
	 * Run the application forever (or until an exit request is sent)
	 */
	public void mainloop() {

		running = true;

		FramerateManager.refreshUpdater.start();
    
		while (GLFWInteraction.shouldBeOpen() && running) {

			Performance.beginFrame();

			Scrolling.moveWorld();

			GLFWInteraction.repaint();

			// Updates debug label each frame
			GUIManager.debugUI.update(fps, frameCount);

			// Updates profiler data
			HashMap<String, Float> timings = Performance.getTimings();
			GUIManager.profilerUI.update(timings);

			// Calculates fps
			if (System.currentTimeMillis() - lastSecond > 1000) {
				fps = frameCount;
				frameCount = 0;
				lastSecond = System.currentTimeMillis();
			} else {
				frameCount++;
			}
		
		}

		running = false; // Shut down refresh thread
		
	}

}
