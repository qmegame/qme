package org.qme.main;

import java.util.ArrayList;

import org.qme.vis.QInputScreen;
import org.qme.vis.QRenderScreen;

/**
 * This class represents an instance of QME.
 * @author adamhutchings
 * @since pre0
 */
public class QApplication {
	
	// Visual elements
	public QInputScreen qiscreen;
	public QRenderScreen qrscreen;
	public ArrayList<QObject> objects;
	
	public QApplication() {
		qiscreen = new QInputScreen(this);
		qrscreen = new QRenderScreen(this);
		qiscreen.add(qrscreen);
		objects = new ArrayList<>();
	}
	
	public void reload() {
		
		// Update everything
		for (QObject qo : objects) {
			qo.update(this);
		}
		
		// Render everything
		qrscreen.repaint();
		
	}

}
