package org.qme.menus;

import org.qme.logging.PreferencesFile;
import org.qme.main.GlobalState;
import static org.qme.main.Main.displayError;
import static org.qme.main.Main.tooltipString;
import org.qme.main.QApplication;
import static org.qme.util.GlobalConstants.TOOLTIPS;
import org.qme.vis.QInputScreen;
import org.qme.vis.ui.QButton;

public class SettingsMenu {
	
	public static void makeMenu(QApplication app) {
		
		// Get back from settings
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, QInputScreen.SCREEN_HEIGHT - 100, "Back") {

			@Override
			public void mouseClickOff() {
				app.setState(GlobalState.ESCAPE_MENU);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.SETTINGS_MENU;				
			}
			
		};
		
		// Toggle tooltips
		new QButton(app, QInputScreen.SCREEN_WIDTH / 2, 100, "Tooltips: " + tooltipString()) {

			@Override
			public void mouseClickOff() {
				try {
					if (PreferencesFile.getPreference("tooltips").equals("true")) {
						TOOLTIPS = false;
						PreferencesFile.setPreference("tooltips", "false");
						// update the text
						text = "Tooltips: " + tooltipString();
					} else {
						TOOLTIPS = true;
						PreferencesFile.setPreference("tooltips", "true");
						// update the text
						text = "Tooltips: " + tooltipString();
					}
				} catch (Exception e) {
					displayError("oh, no, not exception e", true);
				}
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.SETTINGS_MENU;
			}
		};
		
	}

}
