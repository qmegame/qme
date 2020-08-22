package org.qme.vis.ui;

import java.util.ArrayList;

import org.qme.main.QApplication;
import org.qme.main.QObject;

/**
 * Represents a set of UI components.
 * @author adamhutchings
 * @since pre1
 */
public abstract class QMenu extends QObject {
	
	public ArrayList<QObject> components;
	
	public QMenu(QApplication app, ArrayList<QObject> c) {
		
		super(app);
		components = c;
		
	}
	
	/**
	 * Hides everything.
	 * @author adamhutchings
	 * @since pre1
	 */
	public void deactivate() {
		
		for (QObject component : components) {
			component.active = false;
		}
		
	}
	
	/**
	 * Shows everything.
	 * @author adamhutchings
	 * @since pre1
	 */
	public void reactivate() {
		
		for (QObject component : components) {
			component.active = true;
		}
			
	}

}
