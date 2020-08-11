package org.qme.vis;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.qme.main.QApplication;

/**
 * This class represents a screen. The main function is to handle input,
 * but it also contains a QRenderScreen object inside it.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.vis.QRenderScreen
 */
@SuppressWarnings("serial")
public class QInputScreen extends JFrame {
	
	public static final int SCREEN_WIDTH  = 800;
	public static final int SCREEN_HEIGHT = 600;

	public QInputScreen(QApplication qa) {
		/**
		 * Sets up everything we need.
		 * @author adamhutchings
		 */
		super("QME");
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setVisible(true);
		pack();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
