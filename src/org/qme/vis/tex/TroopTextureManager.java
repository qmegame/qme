package org.qme.vis.tex;

import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.qme.troops.UnitType;

import static org.qme.main.Main.displayError;

/**
 * This class gives some texture objects
 * and strings for accessing textures for
 * troops. Main should call the load method
 * to load all of the textures.
 * @author adamhutchings
 * @since pre4
 */
public final class TroopTextureManager {
	
	// ----- TEXTURES ----- //
	public static TexturePaint ARCHER_TEX;
	public static TexturePaint CATAPULT_TEX;
	public static TexturePaint CHARIOT_TEX;
	public static TexturePaint HORSE_ARCHER_TEX;
	public static TexturePaint LEGIONNAIRE_TEX;
	public static TexturePaint LIGHT_CAVALRY_TEX;
	public static TexturePaint SCOUT_TEX;
	public static TexturePaint SHIELD_BEARER_TEX;
	public static TexturePaint SPEARMAN_TEX;
	public static TexturePaint SWORDSMAN_TEX;
	
	// ----- TEXTURE PATHS ----- //
	public static String ARCHER_TEX_PATH        = "res/troops/archer.png";
	public static String CATAPULT_TEX_PATH      = "res/troops/catapult.png";
	public static String CHARIOT_TEX_PATH       = "res/troops/chariot.png";
	public static String HORSE_ARCHER_TEX_PATH  = "res/troops/horse_archer.png";
	public static String LEGIONNAIRE_TEX_PATH   = "res/troops/legionnaire.png";
	public static String LIGHT_CAVALRY_TEX_PATH = "res/troops/light_cavalry.png";
	public static String SCOUT_TEX_PATH         = "res/troops/scout.png";
	public static String SHIELD_BEARER_TEX_PATH = "res/troops/shield_bearer.png";
	public static String SPEARMAN_TEX_PATH      = "res/troops/spearman.png";
	public static String SWORDSMAN_TEX_PATH     = "res/troops/swordsman.png";
	
	/**
	 * This loads the static TexturePaint members
	 * with textures loaded from the files.
	 * This method should be called BEFORE all other
	 * initialization.
	 * @author adamhutchings
	 * @since pre4
	 */
	public static void loadTroopTextures() {
		
		try {
			
			BufferedImage image; // Internal use
		
			image = ImageIO.read(new File(ARCHER_TEX_PATH));
			ARCHER_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(CATAPULT_TEX_PATH));
			CATAPULT_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(CHARIOT_TEX_PATH));
			CHARIOT_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(HORSE_ARCHER_TEX_PATH));
			HORSE_ARCHER_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(LEGIONNAIRE_TEX_PATH));
			LEGIONNAIRE_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(LIGHT_CAVALRY_TEX_PATH));
			LIGHT_CAVALRY_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(SCOUT_TEX_PATH));
			SCOUT_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(SHIELD_BEARER_TEX_PATH));
			SHIELD_BEARER_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(SPEARMAN_TEX_PATH));
			SPEARMAN_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
		
			image = ImageIO.read(new File(SWORDSMAN_TEX_PATH));
			SWORDSMAN_TEX = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
			
		} catch (IOException e) {
			displayError("Error loading texture from file.", true);
		}
		
	}
	
	/**
	 * Get the right texture for a given troop.
	 * @author adamhutchings
	 * @since pre4
	 */
	public static TexturePaint getTexture(UnitType type) {
		
		switch (type) {
		case ARCHER:
			return ARCHER_TEX;
		case CATAPULT:
			return CATAPULT_TEX;
		case CHARIOTS:
			return CHARIOT_TEX;
		case HORSE_ARCHER:
			return HORSE_ARCHER_TEX;
		case LEGIONNAIRE:
			return LEGIONNAIRE_TEX;
		case LIGHT_CAVALRY:
			return LIGHT_CAVALRY_TEX;
		case SCOUT:
			return SCOUT_TEX;
		case SHIELD_BEARER:
			return SHIELD_BEARER_TEX;
		case SPEARMAN:
			return SPEARMAN_TEX;
		case SWORDSMAN:
			return SWORDSMAN_TEX;
		}
		
		return null;
		
	}

}
