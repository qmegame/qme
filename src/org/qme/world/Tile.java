package org.qme.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Random;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import static org.qme.util.GlobalConstants.SQUASH_FACTOR;
import static org.qme.util.GlobalConstants.TILE_SIZE;
import static org.qme.util.GlobalConstants.TOOLTIPS;
import org.qme.util.QDimension;
import org.qme.vis.Perspective;
import org.qme.vis.QLayer;
import org.qme.vis.QRenderable;
import org.qme.vis.ui.Tooltip;
import org.qme.vis.ui.UIComponent;
import org.qme.troops.Unit;

/**
 * A tile in the world. For now, just has a type
 * and a location. Nothing big.
 * @author adamhutchings
 * @since pre0
 * @see org.qme.world.TileType
 */
public class Tile extends QObject implements QRenderable, UIComponent {
	
	// Location
	public int x, y;
	
	// Type
	private TileType type;
	
	//
	public Unit occupier;
	
	// Resources fun time
	public int fish, salt, seaweed = 0;
	public int grain, grass, smallGame = 0;
	public int tallGrass, mediumGame, grapes = 0;
	public int lumber, leaves, fruit, sap, largeGame = 0;
	public int rock, snow, coal, ironOre, goldOre = 0;
	public int sand, cacti = 0;
	
	/**
	 * Simple generation with coordinates.
	 * @param x x-coordinate of tile.
	 * @param y y-coordinate of tile.
	 */
	public Tile(QApplication app, int x, int y) {
		super(app);
		this.x = x;
		this.y = y;
	}
	
	@Override
	public QLayer getLayer() {
		return QLayer.TILE_LAYER;
	}

	/**
	 * Auto-generated method stub.
	 * @return tile type
	 */
	public TileType getType() {
		return type;
	}

	/**
	 * Auto-generated method stub.
	 * @param type new tile type
	 */
	public void setType(TileType type) {
		this.type = type;
	}
	
	/**
	 * @author S-Mackenzie1678
	 * @since pre4
	 */
	void resourceRegen() {
		Random rand = new Random();
		if(this.type == TileType.OCEAN) {
			int r = rand.nextInt(100);
			if(r < 50) {
				this.fish++;
			} else if (r < 80) {
				this.seaweed++;
			} else {
				this.salt++;
			}
		} else if(this.type == TileType.SEA) {
			int r = rand.nextInt(100);
			if(r < 30) {
				this.fish++;
			} else if(r < 55) {
				this.seaweed++;
			} else {
				this.salt++;
			}
		} else if(this.type == TileType.PLAINS) {
			int r = rand.nextInt(100);
			if(r < 70) {
				this.grass++;
				int s = rand.nextInt(100);
				if(s < 65) {
					this.grain++;
				}
			}
			if(r < 30) {
				this.smallGame++;
			}
		} else if(this.type == TileType.FERTILE_PLAINS) {
			int r = rand.nextInt(100);
			if(r < 50) {
				this.tallGrass++;
				int s = rand.nextInt(100);
				if(s < 85) {
					this.grain++;
				}
			}
			if(r < 25) {
				this.mediumGame++;
			}
			if(r < 15) {
				this.smallGame++;
			}
			if(r < 10) {
				this.grapes++;
			}
		} else if(this.type == TileType.FOREST) {
			int r = rand.nextInt(100);
			if(r < 65) {
				this.lumber++;
				int s = rand.nextInt(100);
				if(s < 80) {
					this.leaves++;
					int t = rand.nextInt(100);
					if(t < 20) {
						this.fruit++;
					}
				}
				if(s < 15) {
					this.sap++;
				}
			}
			if(r < 35) {
				this.largeGame++;
			}
		} else if(this.type == TileType.HIGH_MOUNTAIN) {
			this.snow++;
			int r = rand.nextInt(100);
			if(r < 35) {
				this.rock++;
				int s = rand.nextInt(100);
				if(s < 20) {
					this.coal++;
					this.goldOre++;
				}
				if(s < 40) {
					this.ironOre++;
				}
			}
		} else if(this.type == TileType.MOUNTAIN) {
			int r = rand.nextInt(100);
			if(r < 80) {
				this.rock++;
				int s = rand.nextInt(100);
				if(s < 50) {
					this.coal++;
				}
				if(s < 40) {
					this.ironOre++;
				}
				if(s < 10) {
					this.goldOre++;
				}
			}
			if(r < 20) {
				this.snow++;
			}
		} else if(this.type == TileType.DESERT) {
			this.sand++;
			int r = rand.nextInt(100);
			if(r < 50) {
				this.cacti++;
			}
		}
	}
	
	void update(QApplication app) {}
	
	@Override
	/**
	 * Draws a square representing the tile.
	 */
	public void render(Graphics g) {
		
		// Getting the right color (this is just a placeholder, we'll have real textures later)
		switch (type) {
		
		case UNGENERATED:
			g.setColor(TileColors.UNGENERATED_COLOR);
			break;
			
		case OCEAN:
			g.setColor(TileColors.OCEAN_COLOR);
			break;
			
		case SEA:
			g.setColor(TileColors.SEA_COLOR);
			break;
			
		case PLAINS:
			g.setColor(TileColors.PLAINS_COLOR);
			break;
			
		case DESERT:
			g.setColor(TileColors.DESERT_COLOR);
			break;
			
		case FOREST:
			g.setColor(TileColors.FOREST_COLOR);
			break;
			
		case MOUNTAIN:
			g.setColor(TileColors.MOUNTAIN_COLOR);
			break;
			
		case HIGH_MOUNTAIN:
			g.setColor(TileColors.HIGH_MOUNTAIN_COLOR);
			break;
			
		case FERTILE_PLAINS:
			g.setColor(TileColors.FERTILE_PLAINS_COLOR);
			break;
		
		}
		
		Polygon poly = getPolygon();
		
		g.fillPolygon(poly);
		
		// (Maybe) draw the outline
		if (hoveredOver) {
			g.setColor(Color.BLACK);
			g.drawPolygon(poly);
		}
		
	}
	
	@Override
	/**
	 * Does nothing
	 */
	public void mouseClickOff() {
		
	}
	
	@Override
	/**
	 * Makes a tooltip
	 * @author adamhutchings
	 */
	public void mouseClickOn() {
		
		if ((!tooltip) && TOOLTIPS) {
			
			@SuppressWarnings("serial")
			ArrayList<String> info = new ArrayList<String>() {{
				add(
						(type == TileType.UNGENERATED)    ? "ungenerated"    :
						(type == TileType.OCEAN)          ? "ocean"          :
						(type == TileType.SEA)            ? "sea"            :
						(type == TileType.PLAINS)         ? "plains"         :
						(type == TileType.DESERT)         ? "desert"         :
						(type == TileType.FOREST)         ? "forest"         :
						(type == TileType.MOUNTAIN)       ? "mountain"       :
						(type == TileType.HIGH_MOUNTAIN)  ? "high mountain"  :
						(type == TileType.FERTILE_PLAINS) ? "fertile plains" :
						"error"
				);
				add(x + ", " + y);
			}};
			
			new Tooltip(this,
				application.qrscreen.getMousePosition().x,
				application.qrscreen.getMousePosition().y,
				application, info
			);
			
		}
		
	}
	
	@Override
	/**
	 * Does nothing
	 */
	public void mouseHoverOn() {
		
	}
	
	@Override
	/**
	 * Does nothing
	 */
	public void mouseHoverOff() {
		
	}
	
	@Override
	/**
	 * Returns whether said click is inside the tile.
	 */
	public boolean clickIsIn(int x, int y) {
		
		return getPolygon().contains(x, y);
		
	}
	
	/**
	 * Gives the bounding polygon.
	 * @author adamhutchings
	 * @since pre2
	 */
	public Polygon getPolygon() {
		
		QDimension<Float> screenCoords = Perspective.worldToScreen(new QDimension<Float>((float) x, (float) y));
		
		int renderX = Math.round(screenCoords.x) - application.qiscreen.xOffset;
		int renderY = Math.round(screenCoords.y) - application.qiscreen.yOffset;
		
		// Left, top, right, bottom
		int[] xPoints = {
				(int) (renderX - (TILE_SIZE / Perspective.TILE_GAP_FACTOR)), renderX,
				(int) (renderX + (TILE_SIZE / Perspective.TILE_GAP_FACTOR)), renderX
		};
		int[] yPoints = {
				renderY, (int) (renderY - (TILE_SIZE / (Perspective.TILE_GAP_FACTOR * SQUASH_FACTOR))),
				renderY, (int) (renderY + (TILE_SIZE / (Perspective.TILE_GAP_FACTOR * SQUASH_FACTOR)))
		};
		
		return new Polygon(xPoints, yPoints, 4);
		
	}

	@Override
	public GlobalState getActiveState() {
		return GlobalState.MAIN_GAME;
	}

}
