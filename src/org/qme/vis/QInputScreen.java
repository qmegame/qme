package org.qme.vis;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.vis.ui.UIComponent;

/**
 * This class represents a screen. The main function is to handle input,
 * but it also contains a QRenderScreen object inside it.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.vis.QRenderScreen
 */
@SuppressWarnings("serial")
public class QInputScreen extends JFrame implements KeyListener, MouseListener {
	
	/**
	 * How fast the user "scrolls".
	 */
	public static final int SCROLL_SPEED = 20;
	
	/**
	 * The default width of the screen generated.
	 * @author adamhutchings
	 * @since pre0
	 */
	public static final int SCREEN_WIDTH  = 800;
	
	/**
	 * The default height of the screen generated.
	 * @author adamhutchings
	 * @since pre0
	 */
	public static final int SCREEN_HEIGHT = 600;
	
	/**
	 * The app that this "belongs" to
	 */
	QApplication a;
	
	/**
	 * How far the window has been scrolled
	 */
	public int xOffset;
	
	/**
	 * How far the window has been scrolled
	 */
	public int yOffset;

	/**
	 * Creates the window, and sets appropriate settings.
	 * @author adamhutchings
	 * @since pre0
	 * @param qa - unused for now, may be used later
	 */
	public QInputScreen(QApplication qa) {
		super("QME");
		a = qa;
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setVisible(true);
		pack();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		addMouseListener(this);
		xOffset = 0;
		yOffset = 0;
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
		
		for (QObject qo : a.objects) {
			if (qo instanceof UIComponent) {
				
				uc = (UIComponent) qo;
				
				if (uc.clickIsIn(e.getX(), e.getY())) {
					uc.mouseClickOn();
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
	public void mouseReleased(MouseEvent e) {
		
		UIComponent uc; // For internal use
		
		for (QObject qo : a.objects) {
			if (qo instanceof UIComponent) {
				
				uc = (UIComponent) qo;
				
				if (uc.clickIsIn(e.getX(), e.getY())) {
					uc.mouseClickOff();
				}
				
			}
		}
		
	}

	@Override
	/**
	 * This method is called on a key press.
	 * Currently just moves the screen up and down.
	 * @author adamhutchings
	 */
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_A:
			xOffset -= SCROLL_SPEED;
			break;
			
		case KeyEvent.VK_D:
			xOffset += SCROLL_SPEED;
			break;
			
		case KeyEvent.VK_W:
			yOffset -= SCROLL_SPEED;
			break;
			
		case KeyEvent.VK_S:
			yOffset += SCROLL_SPEED;
			break;
			
		}
		
	}
	
	// Useless stub methods

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
