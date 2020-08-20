package org.qme.main;

import java.awt.MouseInfo;
import java.util.ArrayList;

import org.qme.vis.QInputScreen;
import org.qme.vis.QRenderScreen;

/**
 * This class represents an instance of QME.
 * @author adamhutchings
 * @since pre0
 */
public class QApplication {
	
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
	 * Initializes the elements appropriately.
	 * @author adamhutchings
	 * @since pre0
	 */
	public QApplication() {
		qiscreen = new QInputScreen(this);
		qrscreen = new QRenderScreen(this);
		qiscreen.add(qrscreen);
		objects = new ArrayList<>();
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

}
