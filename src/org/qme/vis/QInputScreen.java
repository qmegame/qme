package org.qme.vis;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ConcurrentModificationException;

import javax.swing.JFrame;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import static org.qme.util.GlobalConstants.SCREEN_WIDTH;
import static org.qme.util.GlobalConstants.SCREEN_HEIGHT;
import static org.qme.util.GlobalConstants.SCROLL_SPEED;
import org.qme.vis.ui.UIComponent;

/**
 * This class represents a screen. The main function is to handle input,
 * but it also contains a QRenderScreen object inside it.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.vis.QRenderScreen
 */
@SuppressWarnings("serial")
public class QInputScreen extends JFrame implements KeyListener {
	
	QApplication app;
	
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
		addKeyListener(this);
		xOffset = 0;
		yOffset = 0;
		setResizable(false);
		app = qa;
	}
	
	/**
	 * This method dispatches hovers to all the
	 * UIComponent objects.
	 * @author adamhutchings
	 * @since pre1
	 */
	public void updateHovers(Point mouseLoc) {
		
		UIComponent uc; // For internal use
		
		try {
			
			for (QObject qo : a.objects) {
				if (qo instanceof UIComponent && qo.active) {
					
					uc = (UIComponent) qo;
					
					if (uc.clickIsIn(mouseLoc.x, mouseLoc.y)) {
						
						if (!(qo.hoveredOver)) {
							
							uc.mouseHoverOn();
							qo.hoveredOver = true;
							
						}
						
					} else {
						
						if (qo.hoveredOver) {
							
							uc.mouseHoverOff();
							qo.hoveredOver = false;
							
						}
						
					}
					
				}
			}
			
		} catch (ConcurrentModificationException cme) {}
		catch ( NullPointerException npe) {}
		
	}

	@Override
	/**
	 * This method is called on a key press.
	 * Currently just moves the screen up and down and left and right.
	 * @author adamhutchings
	 * @author S-Mackenzie1678
	 */
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
		case KeyEvent.VK_A:
			xOffset -= SCROLL_SPEED;
			break;
			
		case KeyEvent.VK_D:
			xOffset += SCROLL_SPEED;
			break;
			
		case KeyEvent.VK_S:
			yOffset += SCROLL_SPEED;
			break;
			
		case KeyEvent.VK_W:
			yOffset -= SCROLL_SPEED;
			break;
			
		case KeyEvent.VK_ESCAPE:
			app.setState(GlobalState.ESCAPE_MENU);
			
		case KeyEvent.VK_Q:
			QDebug.toggleDebug();
			
		}
		
	}
	
	// Useless stub methods

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}
