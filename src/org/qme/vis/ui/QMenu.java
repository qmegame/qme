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
	
	public ArrayList<UIComponent> components;
	
	public QMenu(QApplication app, ArrayList<UIComponent> c) {
		
		super(app);
		components = c;
		
	}

}
