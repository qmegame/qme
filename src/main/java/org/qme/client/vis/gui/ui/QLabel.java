package org.qme.client.vis.gui.ui;

import org.qme.client.vis.gui.UIComponent;
import org.qme.client.vis.wn.WindowManager;

/**
 * Class for creating labels
 * @author cameron
 * @since 0.3.0
 */
public class QLabel extends UIComponent {

    public QFont font;
    public String text;
    public int x;
    public int y;
    public boolean lineBreakUp = true;

    /**
     * Creates a new label
     * @param font the QFont to use for the label
     * @param text the label text
     * @param x the label's x position
     * @param y the label's y position
     */
    public QLabel(QFont font, String text, int x, int y) {
        super();
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new label
     * @param font the QFont to use for the label
     * @param text the label text
     * @param x the label's x position
     * @param y the label's y position
     * @param lineBreakUp should line breaks go up
     */
    public QLabel(QFont font, String text, int x, int y, boolean lineBreakUp) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        this.lineBreakUp = lineBreakUp;

        WindowManager.addObject(this);
    }

    @Override
    public void draw() {
        font.drawText(text, x, y, lineBreakUp);
    }
}
