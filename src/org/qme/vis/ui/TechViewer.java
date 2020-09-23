package org.qme.vis.ui;

import java.awt.Graphics;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.vis.QLayer;
import org.qme.vis.QRenderable;

/**
 * A single tech in the view
 * @author adamhutchings
 * @since pre3
 */
public class TechViewer extends QObject implements QRenderable, UIComponent {

	public TechViewer(QApplication app) {
		super(app);
	}

	@Override
	public GlobalState getActiveState() {
		return GlobalState.TECH_TREE_VIEW;
	}

	@Override
	public void mouseClickOn() {
		// Nothing
	}

	@Override
	public void mouseClickOff() {
		// Nothing
	}

	@Override
	public void mouseHoverOn() {
		// Nothing
	}

	@Override
	public void mouseHoverOff() {
		// Nothing
	}

	@Override
	public boolean clickIsIn(int x, int y) {
		return false;
	}

	@Override
	public QLayer getLayer() {
		return QLayer.UI_LAYER;
	}

	@Override
	public void render(Graphics g) {
		// Nothing
	}

	@Override
	public void update(QApplication app) {
		// Nothing
	}

}
