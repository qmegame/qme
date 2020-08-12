package org.qme.main;

/**
 * QObject elements are stored in a QApplication instance,
 * and each one is updated every screen repaint.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.main.QApplication
 */
public abstract class QObject {
	
	public QObject(QApplication app) {
		app.objects.add(this);
	}
	
	abstract void update(QApplication app);

}
