package org.qme.client.vis.gui.comp;

import org.lwjgl.system.MemoryUtil;
import org.qme.client.vis.RenderMaster;
import org.qme.client.vis.tex.TextureManager;
import org.qme.io.Logger;
import org.qme.io.Severity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_NEAREST;

/**
 * Represents a renderable font
 * Created with lots of help from https://github.com/SilverTiger/lwjgl3-tutorial/wiki/Fonts
 * @since 0.3.0
 * @author cameron
 */
public class QFont {

    public Font font;
    public boolean antiAlias;

    private int fontHeight;
    private final Map<Character, Glyph> glyphs = new HashMap<>();

    /**
     * Creates a new instance of a QFont
     * @param font the ttf font to be used
     * @param antiAlias if the font should use anti aliasing or not
     */
    public QFont(Font font, boolean antiAlias) {
        Logger.log("Loading font: " + font.getFontName(), Severity.NORMAL);
        this.font = font;
        this.antiAlias = antiAlias;
        loadFont();
    }

    /**
     * Gets the height of this font
     * @return the height of this font
     */
    public int getHeight() {
        return fontHeight;
    }

    /**
     * Calculates the width of this font for a given string
     * @param string the string to be calculated
     * @return the width of the text
     */
    public int getWidth(String string) {
        int width = 0;
        for(int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (glyphs.containsKey(ch)) {
                width += glyphs.get(ch).width;
            }
        }
        return width;
    }

    /**
     * Generates a texture for the font and saves it as an ID
     * If multiple fonts are using the same name a warning will be printed and only 1 will be loaded
     */
    private void loadFont() {
        int imageWidth = 0;
        int imageHeight = 0;

        // Calculates the size of the font texture
        for (int i = 32; i < 256; i++) {
            if (i == 127) {
                continue;
            }
            char c = (char) i;
            BufferedImage ch = loadChar(c);
            if (ch == null) {
                continue;
            }

            imageWidth += ch.getWidth();
            imageHeight = Math.max(imageHeight, ch.getHeight());
        }

        fontHeight = imageHeight;

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();

        // Calculates each character and their position on the font texture
        int x = 0;

        for (int i = 32; i < 256; i++) {
            if (i == 127) {
                continue;
            }
            char c = (char) i;
            BufferedImage charImage = loadChar(c);
            if (charImage == null) {
                continue;
            }

            int charWidth = charImage.getWidth();
            int charHeight = charImage.getHeight();

            Glyph ch = new Glyph(charWidth, charHeight, x, image.getHeight() - charHeight, image.getWidth(), image.getHeight());
            g.drawImage(charImage, x, 0, null);
            x += ch.width;
            glyphs.put(c, ch);
        }

        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);

        ByteBuffer buffer = MemoryUtil.memAlloc(width * height * 4);
        TextureManager.tryLoadTextureFromImage(height, width, pixels, buffer);

        if (TextureManager.getTexture(font.getFontName().replace(" ", "-")) != null) {
            Logger.log("The font " + font.getFontName() + " was initialised twice under the same name.", Severity.WARNING);
            return;
        }

        TextureManager.registerTexture(font.getFontName().replace(" ", "-"), TextureManager.loadTextureFromBuffer(buffer, width, height));
    }

    /**
     * Loads a char to a texture
     * @param c the character to be loaded
     * @return the character image
     */
    private BufferedImage loadChar(char c) {

        // Determine width and height of char by creating dummy image
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        if (antiAlias) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics();
        g.dispose();
        int charWidth = metrics.charWidth(c);
        int charHeight = metrics.getHeight();

        if (charWidth == 0) {
            return null;
        }

        // Create final image with set width and height
        image = new BufferedImage(charWidth, charHeight, BufferedImage.TYPE_INT_ARGB);
        g = image.createGraphics();
        if (antiAlias) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        g.setFont(font);
        g.setPaint(Color.WHITE);
        g.drawString(String.valueOf(c), 0, metrics.getAscent());
        g.dispose();
        return image;
    }

    /**
     * Draws the font at a specified location.
     * @param string the text to be draw
     * @param x the x position of the text
     * @param y the y position of the text
     */
    public void drawText(String string, int x, int y) {
        drawText(string, x, y, false);
    }

    /**
     * Draws the font at a specified location.
     * @param string the text to be draw
     * @param x the x position of the text
     * @param y the y position of the text
     * @param lineBreakUp if the line breaks should offset up
     */
    public void drawText(String string, int x, int y, boolean lineBreakUp) {
        int lines = 1;
        for(int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if(ch == '\n') {
                lines++;
            }
        }
        int textHeight = lines * (lineBreakUp ? -1 : 1) * fontHeight;
        int drawX = x;
        int drawY = y;
        if(textHeight > fontHeight) {
            drawY += textHeight - fontHeight;
        }

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBindTexture(GL_TEXTURE_2D, TextureManager.getTexture(font.getFontName().replace(" ", "-")));
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (ch == '\n') {
                drawY -= fontHeight;
                drawX = x;
                continue;
            }
            if (ch == '\r') {
                continue;
            }
            Glyph g = glyphs.get(ch);
            RenderMaster.drawRegion(new Rectangle(drawX, drawY, g.width, g.height), new Rectangle(g.x, g.y, g.width, g.height), new Dimension(g.imageWidth, g.imageHeight));
            //RenderMaster.drawRegion(drawX, drawY, g.x, g.y, g.width, g.height, g.width, g.height, g.imageWidth, g.imageHeight);
            drawX += g.width;
        }

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);
    }

    private static class Glyph {
        public final int width;
        public final int height;
        public final int x;
        public final int y;
        public final int imageHeight;
        public final int imageWidth;

        public Glyph(int width, int height, int x, int y, int imageWidth, int imageHeight) {
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.imageHeight = imageHeight;
            this.imageWidth = imageWidth;
        }
    }

}
