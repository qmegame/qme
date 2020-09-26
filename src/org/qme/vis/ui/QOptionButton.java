package org.qme.vis.ui;

import org.qme.main.QApplication;

/**
 * A button, but it cycles through
 * options.
 * @author adamhutchings
 * @since pre3
 */
public abstract class QOptionButton extends QButton {
	
	/**
	 * All the things that can be displayed.
	 */
	String[] options;
	
	/**
	 * Which option is currently being shown.
	 */
	public int current;

	public QOptionButton(QApplication a, int x, int y, String[] opts, int curr) {
		super(a, x, y, opts[curr]);
		options = opts;
		current = curr;
	}
	
	public QOptionButton(QApplication a, int x, int y, String[] opts) {
		this(a, x, y, opts, 0);
	} 

	@Override
	public void mouseClickOff() {
		current++;
		if (!(current < options.length)) {
			current = 0;
		}
		text = options[current];
	}
	
	public String getOption() {
		return options[current];
	}

}
