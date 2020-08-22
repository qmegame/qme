package org.qme.main;

import java.util.ArrayList;

import org.qme.vis.QInputScreen;
import org.qme.vis.QRenderScreen;
import org.qme.world.World;

/**
 * This class represents an instance of QME.
 * @author adamhutchings
 * @since pre0
 */
public class QApplication {
	
	/**
	 * What state the app is in. Used
	 * for toggling menus.
	 * @since pre1
	 */
	private GlobalState state;
	
	/**
	 * Represents the component of the screen that
	 * responds to key and mouse press events.
	 * @since pre0
	 */
	public QInputScreen qiscreen;
	/**
	 * Represents the component of the screen that
	 * renders graphical objects.
	 * @see org.qme.vis.QRenderable
	 */
	public QRenderScreen qrscreen;
	/**
	 * Every object that needs to be updated in the reload
	 * method. The QObject constructor automatically adds
	 * the object to this list.
	 */
	public ArrayList<QObject> objects;
	/**
	 * Actually a variable for the world lol.
	 */
	public World world;
	
	/**
	 * Initializes the elements appropriately.
	 * @author adamhutchings
	 * @since pre0
	 */
	public QApplication() {
		
		qiscreen = new QInputScreen(this);
		qrscreen = new QRenderScreen(this);
		
		qiscreen.add(qrscreen);
		
		objects = new ArrayList<>();
		state = GlobalState.MAIN_MENU;
		
	}
	
	/**
	 * Updates every object appropriately and
	 * calls the repaint method.
	 * @author adamhutchings
	 * @since pre0
	 * @see org.qme.main.QObject
	 * @see org.qme.vis.QRenderScreen
	 */
	public void reload() {
		
		// Check for mouse positions (hovering)
		qiscreen.updateHovers(qrscreen.getMousePosition());
		
		// Update everything
		for (QObject qo : objects) {
			qo.update(this);
		}
		
		// Render everything
		qiscreen.repaint();
		
	}
	
	public GlobalState getState() {
		return state;
	}
	
	public void setState(GlobalState s) {
		
		state = s;
		
	}

}
