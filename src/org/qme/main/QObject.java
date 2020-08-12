package org.qme.main;

/**
 * QObject elements are stored in a QApplication instance,
 * and each one is updated every screen repaint.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.main.QApplication
 */
public abstract class QObject {
	
	/**
	 * Adds the object to the objects list in the specified application.
	 * @author adamhutchings
	 * @since pre0
	 * @see org.qme.main.QApplication
	 * @param app - the application to add this object to
	 */
	public QObject(QApplication app) {
		app.objects.add(this);
	}
	
	/**
	 * This is called every reload to update the global
	 * state appropriately.
	 * @author adamhutchings
	 * @since pre0
	 * @param app - a handle to update other objects, if need be
	 * @see org.qme.main.QApplication.reload
	 */
	abstract void update(QApplication app);

}
