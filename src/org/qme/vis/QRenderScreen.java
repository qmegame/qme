package org.qme.vis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ConcurrentModificationException;

import javax.swing.JPanel;

import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.vis.ui.UIComponent;

/**
 * This represents where objects get drawn onto
 * the screen. Owned by QApplication.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.main.QApplication
 */
@SuppressWarnings("serial")
public class QRenderScreen extends JPanel implements MouseListener {
	
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
		addMouseListener(this);
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
		
		// Clear the screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, QInputScreen.SCREEN_WIDTH, QInputScreen.SCREEN_HEIGHT);
		
		// For internal usage
		QLayer layer;
		
		for (int i = 0; i < QLayer.values().length; i++) {
			
			layer = QLayer.values()[i];
		
			for (QObject qr : app.objects) {
				if (qr instanceof QRenderable && qr.active && (((QRenderable) qr).getLayer() == layer)) {
					((QRenderable) qr).render(g);
				}
			}
		
		}
		
	}
	
	@Override
	/**
	 * This method dispatches click events to all the
	 * UIComponent objects.
	 * @author adamhutchings
	 * @since pre0
	 */
	public void mousePressed(MouseEvent e) {
		
		UIComponent uc; // For internal use
		
		try {
		
			for (QObject qo : app.objects) {
				if (qo instanceof UIComponent && qo.active) {
					
					uc = (UIComponent) qo;
					
					if (uc.clickIsIn(
						e.getX(), e.getY()
					)) {
						qo.clicked = true;
						uc.mouseClickOn();
					}
					
				}
			}
		} catch (ConcurrentModificationException cme) {}
		
	}

	@Override
	/**
	 * This method dispatches click events to all the
	 * UIComponent objects.
	 * @author adamhutchings
	 * @since pre0
	 */
	public void mouseReleased(MouseEvent e) {
		
		UIComponent uc; // For internal use
		
		try {
			
			for (QObject qo : app.objects) {
				if (qo instanceof UIComponent && qo.active) {
					
					uc = (UIComponent) qo;
					
					if (qo.clicked) {
						qo.clicked = false;
						uc.mouseClickOff();
					}
					
				}
			}
			
		} catch (ConcurrentModificationException cme) {
			
		}
		
	}
	
	// Useless stub methods

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
