package org.qme.menus;

import org.qme.tech.Tech;
import org.qme.vis.ui.QButton;
import org.qme.vis.ui.TechViewer;

import static org.qme.util.GlobalConstants.SCREEN_WIDTH;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;

public class TechViewMenu {
	
	public static void makeMenu(QApplication app) {
		
		new TechViewer(app, Tech.WHEEL_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (2), TechViewer.TECH_VIEW_HEIGHT + 10
		);
		new TechViewer(app, Tech.ANIMAL_HUSBANDRY_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (1), TechViewer.TECH_VIEW_HEIGHT + 10
		);
		new TechViewer(app, Tech.AGRICULTURE_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (0), TechViewer.TECH_VIEW_HEIGHT + 10
		);
		new TechViewer(app, Tech.WRITING_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (1), TechViewer.TECH_VIEW_HEIGHT + 10
		);
		

		new TechViewer(app, Tech.WOOD_CHOPPING_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 2
		);
		new TechViewer(app, Tech.ARCHERY_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 2
		);
		new TechViewer(app, Tech.POTTERY_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (0), (TechViewer.TECH_VIEW_HEIGHT + 10) * 2
		);
		new TechViewer(app, Tech.ASTROLOGY_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 2
		);
		new TechViewer(app, Tech.FISHING_NET_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 2
		);
		
		new TechViewer(app, Tech.MASONRY_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 3
		);
		new TechViewer(app, Tech.BRONZE_WORKING_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 3
		);
		new TechViewer(app, Tech.IRRIGATION_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (0), (TechViewer.TECH_VIEW_HEIGHT + 10) * 3
		);
		new TechViewer(app, Tech.MEDICINE_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 3
		);
		new TechViewer(app, Tech.SAILING_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 3
		);
		
		new TechViewer(app, Tech.MINING_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 4
		);
		new TechViewer(app, Tech.HORSEBACK_RIDING_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 4
		);
		new TechViewer(app, Tech.FERMENTATION_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (0), (TechViewer.TECH_VIEW_HEIGHT + 10) * 4
		);
		new TechViewer(app, Tech.MATHEMATICS_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 4
		);
		new TechViewer(app, Tech.TRIREME_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 4
		);
		
		new TechViewer(app, Tech.QUARRYING_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 5
		);
		new TechViewer(app, Tech.SMELTING_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 5
		);
		new TechViewer(app, Tech.CURRENCY_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (0), (TechViewer.TECH_VIEW_HEIGHT + 10) * 5
		);
		new TechViewer(app, Tech.EDUCATION_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 5
		);
		new TechViewer(app, Tech.QUADRIREME_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 5
		);
		
		new TechViewer(app, Tech.PULLEY_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 6
		);
		new TechViewer(app, Tech.IRON_WORKING_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 6
		);
		new TechViewer(app, Tech.SCYTHE_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (0), (TechViewer.TECH_VIEW_HEIGHT + 10) * 6
		);
		new TechViewer(app, Tech.CHEMISTRY_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 6
		);
		new TechViewer(app, Tech.CELESTIAL_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 6
		);
		
		new TechViewer(app, Tech.CRANE_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 7
		);
		new TechViewer(app, Tech.ENGINEERING_TECH,
			(SCREEN_WIDTH / 2) - (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 7
		);
		new TechViewer(app, Tech.STONEWORKING_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (0), (TechViewer.TECH_VIEW_HEIGHT + 10) * 7
		);
		new TechViewer(app, Tech.ORGANIZED_ED_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (1), (TechViewer.TECH_VIEW_HEIGHT + 10) * 7
		);
		new TechViewer(app, Tech.GALLEY_TECH,
			(SCREEN_WIDTH / 2) + (TechViewer.TECH_VIEW_WIDTH + 10) * (2), (TechViewer.TECH_VIEW_HEIGHT + 10) * 7
		);
		
		// Quit time
		new QButton(app,
			SCREEN_WIDTH - QButton.BUTTON_WIDTH / 2, QButton.BUTTON_HEIGHT / 2,
			"Return to game"
		) {

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
