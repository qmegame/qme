package org.qme.vis.ui;

import org.qme.main.QApplication;
import org.qme.main.QObject;

/**
 * This class just represents a component of the user interface
 * system. It has methods for responding to clicks that are handled in
 * QInputScreen's interface methods. The four types of mouse events are
 * hover on, hover off, click on, click off
 * @author adamhutchings
 * @since pre0
 * @see org.qme.vis.InputScreen
 */
public abstract class UIComponent extends QObject {
	
	UIComponent(QApplication app) {
		super(app);
	}
	
	// The methods that should be overridden.
	public abstract void mouseHoverOn();
	public abstract void mouseHoverOff();
	public abstract void mouseClickOn();
	public abstract void mouseClickOff();
}
