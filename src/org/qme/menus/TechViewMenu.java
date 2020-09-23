package org.qme.menus;

import org.qme.tech.Tech;
import org.qme.vis.ui.QButton;
import org.qme.vis.ui.TechViewer;

import static org.qme.util.GlobalConstants.SCREEN_HEIGHT;
import static org.qme.util.GlobalConstants.SCREEN_WIDTH;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;

public class TechViewMenu {
	
	public static void makeMenu(QApplication app) {
		
		new TechViewer(app, Tech.ANIMAL_HUSBANDRY_TECH, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
		
		// Quit time
		new QButton(app, SCREEN_WIDTH / 2, (SCREEN_HEIGHT / 2) + 100, "Return to game") {

			@Override
			public void mouseClickOff() {
				app.setState(GlobalState.MAIN_GAME);
			}
			
			@Override
			public GlobalState getActiveState() {
				return GlobalState.TECH_TREE_VIEW;				
			}
			
		};
		
	}

}
