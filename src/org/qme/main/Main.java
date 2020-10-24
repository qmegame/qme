package org.qme.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.qme.audio.AudioWrapper;
import org.qme.logging.FileWatcher;
import static org.qme.logging.FileWatcher.ERROR_LOG;
import org.qme.logging.PreferencesFile;
import org.qme.menus.EscapeMenu;
import org.qme.menus.GameMenu;
import org.qme.menus.SelectionMenu;
import org.qme.menus.SettingsMenu;
import org.qme.menus.StartMenu;
import org.qme.menus.TechViewMenu;

import static org.qme.util.GlobalConstants.FRAMERATE;
import static org.qme.util.GlobalConstants.SCREEN_HEIGHT;
import static org.qme.util.GlobalConstants.SCREEN_WIDTH;
import static org.qme.util.GlobalConstants.TOOLTIPS;
import org.qme.vis.QDebug;
import org.qme.vis.QGameInfo;
import org.qme.vis.tex.TroopTextureManager;

/**
 * Holds the main method and the FRAMERATE constant.
 * FRAMERATE may be moved to a different place later.
 * @author adamhutchings
 * @since pre0
 */
public class Main {
	
	/**
	 * We'll put this somewhere else later. Hopefully. Maybe.
	 * @author adamhutchings
	 * @since pre3
	 */
	public final static void displayError(String status, boolean exit) {
		JOptionPane.showMessageDialog(null, status);
		if (exit) {
			System.exit(-1);
		}
	}
	
	/**
	 * "on" or "off"
	 * @author adamhutchings
	 * @since pre3
	 */
	public static String tooltipString() {
		try {
			return (PreferencesFile.getPreference("tooltips").equals("true") ? "on" : "off");
		} catch (Exception e) {
			displayError("oh no", true);
		}
		// dead line
		return "";
	}
	
	/**
	 * The music that is playing
	 */
	AudioWrapper music = null;
	
	/**
	 * Creates a new QApplication instance and reloads it.
	 * @author adamhutchings
	 * @author S-Mackenzie1678
	 * @since pre0
	 * @see org.qme.main.QApplication
	 * @param args - command line arguments: unused for now
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		/**
		 * @author S-Mackenzie1678
		 * @since pre2
		 * @see org.qme.main.ErrorFileWatcher
		 */
		try {
			FileOutputStream errors = new FileOutputStream(ERROR_LOG);
			System.setErr(new PrintStream(errors));
		} catch(IOException e) {
			displayError("The file " + System.getProperty("user.dir") + System.getProperty("file.separator") + ERROR_LOG + " does not exist. Please create it.", true);
			System.exit(0);
		}
		
		PreferencesFile.setup();
		
		// Set up preferences
		try {
			TOOLTIPS = (PreferencesFile.getPreference("tooltips").equals("true"));
		} catch (Exception e1) {
			displayError("Oops", true);
		}
		
		try {
			
			FileWatcher alert = new FileWatcher(new File(ERROR_LOG));
			
			TroopTextureManager.loadTroopTextures();
			
			QApplication app = new QApplication();
			
			StartMenu.makeMenu(app);
			SelectionMenu.makeMenu(app);
			EscapeMenu.makeMenu(app);
			SettingsMenu.makeMenu(app);
			GameMenu.makeMenu(app);
			TechViewMenu.makeMenu(app);
			
			new QDebug(app, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 15);
			
			new QGameInfo(app);
		
			// Main loop time
			while (true) {
				
				// Wait for some time
				TimeUnit.MILLISECONDS.sleep(1000 / FRAMERATE);
				
				app.reload();
				
				alert.check();
			}

			
			
		} catch(Exception e) {}
  
  }

}
