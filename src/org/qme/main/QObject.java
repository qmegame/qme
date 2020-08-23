package org.qme.main;

import org.qme.vis.ui.UIComponent;

/**
 * QObject elements are stored in a QApplication instance,
 * and each one is updated every screen repaint.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.main.QApplication
 */
public abstract class QObject {
	
	public QApplication application;
	
	public boolean active;
	
	public boolean clicked;
	
	/**
	 * So multiple tooltips don't pop up
	 */
	public boolean tooltip;
	
	/**
	 * If the mouse is over this object or not.
	 * Can be used, or not.
	 */
	public boolean hoveredOver;
	
	/**
	 * Adds the object to the objects list in the specified application.
	 * @author adamhutchings
	 * @since pre0
	 * @see org.qme.main.QApplication
	 * @param app - the application to add this object to
	 */
	public QObject(QApplication app) {
		
		app.objects.add(this);
		
		application = app;
		
		active = true;
		hoveredOver = false;
		tooltip = false;
		
		if (this instanceof UIComponent) {
			if (((UIComponent) this).getActiveState() != app.getState()) {
				this.active = false;
			}
		}
		
	}
	
	/**
	 * This is called every reload to update the global
	 * state appropriately.
	 * @author adamhutchings
	 * @since pre0
	 * @param app - a handle to update other objects, if need be
	 * @see org.qme.main.QApplication.reload
	 */
	public abstract void update(QApplication app);

}
