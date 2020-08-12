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
	 * Creates the window, and sets appropriate settings.
	 * @author adamhutchings
	 * @since pre0
	 * @param qa - unused for now, may be used later
	 */
	public QInputScreen(QApplication qa) {
		super("QME");
		setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		setVisible(true);
		pack();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
