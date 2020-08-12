package org.qme.vis;

import java.awt.Graphics;

import javax.swing.JPanel;

import org.qme.main.QApplication;
import org.qme.main.QObject;

/**
 * This represents where objects get drawn onto
 * the screen. Owned by QApplication.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.main.QApplication
 */
@SuppressWarnings("serial")
public class QRenderScreen extends JPanel {
	
	/**
	 * The "owner" of this object.
	 */
	QApplication app;
	
	/**
	 * This constructor really only sets an instance variable.
	 * @author adamhutchings
	 * @since pre0
	 * @param a - the QApplication instance to set app to.
	 */
	public QRenderScreen(QApplication a) {
		app = a;
	}
	
	/**
	 * This method is invoked automatically by QApplication's
	 * reload method. It simply paints every object in the screen.
	 * @author adamhutchings
	 * @since pre0
	 * @see org.qme.main.QApplication
	 */
	@Override
	public void paint(Graphics g) {
		
		for (QObject qr : app.objects) {
			if (qr instanceof QRenderable) {
				((QRenderable) qr).render(g);
			}
		}
		
	}

}
