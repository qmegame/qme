package org.qme.vis;

/**
 * Represents the order in which
 * objects get rendered (lowest to
 * highest) and which ones get click
 * signals sent (reversed).
 * @author adamhutchings
 * @since pre2
 *
 */
public enum QLayer {
	
	NON_RENDER_LAYER,
	BACKGROUND_EFFECT,
	TILE_LAYER,
	CITY_LAYER,
	TROOP_LAYER,
	UI_LAYER,
	EFFECT_LAYER
	
}
