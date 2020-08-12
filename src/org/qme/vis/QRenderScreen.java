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
	
	QApplication app;
	
	public QRenderScreen(QApplication a) {
		app = a;
	}
	
	@Override
	public void paint(Graphics g) {
		
		// Just render every object if it can be
		for (QObject qr : app.objects) {
			if (qr instanceof QRenderable) {
				((QRenderable) qr).render(g);
			}
		}
		
	}

}
