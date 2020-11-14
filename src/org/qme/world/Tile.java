package org.qme.world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
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
import org.qme.vis.ui.BottomViewBar;
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
	public String resource = "";
	
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
				this.resource = "fish";
			} else if (r < 80) {
				this.resource = "seaweed";
			} else {
				this.resource = "salt";
			}
		} else if(this.type == TileType.SEA) {
			int r = rand.nextInt(100);
			if(r < 30) {
				this.resource = "fish";
			} else if(r < 55) {
				this.resource = "seaweed";
			} else {
				this.resource = "salt";
			}
		} else if(this.type == TileType.PLAINS) {
			int r = rand.nextInt(100);
			if(r < 70 && r >= 30) {
				this.resource = "grass";
				int s = rand.nextInt(100);
				if(s < 65) {
					this.resource = "grain";
				}
			}
			else if(r < 30) {
				this.resource = "small game";
			}
		} else if(this.type == TileType.FERTILE_PLAINS) {
			int r = rand.nextInt(100);
			if(r < 50) {
				this.resource = "tall grass";
				int s = rand.nextInt(100);
				if(s < 85) {
					this.resource = "grain";
				}
			}
			else if(r < 75) {
				this.resource = "medium game";
			}
			else if(r < 90) {
				this.resource = "small game";
			}
			else {
				this.resource = "grapes";
			}
		} else if(this.type == TileType.FOREST) {
			int r = rand.nextInt(100);
			if(r < 65) {
				this.resource = "lumber";
				int s = rand.nextInt(100);
				if(s >= 15) {
					this.resource = "leaves";
					int t = rand.nextInt(100);
					if(t < 20) {
						this.resource = "fruit";
					}
				}
				if(s < 15) {
					this.resource = "sap";
				}
			}
			else {
				this.resource = "large game";
			}
		} else if(this.type == TileType.HIGH_MOUNTAIN) {
			this.resource = "snow";
			int r = rand.nextInt(100);
			if(r < 35) {
				this.resource = "rock";
				int s = rand.nextInt(100);
				if(s < 20) {
					this.resource = "gold ore";
				}
				else {
					this.resource = "iron ore";
				}
			}
		} else if(this.type == TileType.MOUNTAIN) {
			int r = rand.nextInt(100);
			if(r < 80) {
				this.resource = "rock";
				int s = rand.nextInt(100);
				if(s < 50) {
					this.resource = "coal";
				}
				else if(s < 90) {
					this.resource = "iron ore";
				}
				else {
					this.resource = "gold ore";
				}
			}
			else {
				this.resource = "snow";
			}
		} else if(this.type == TileType.DESERT) {
			this.resource = "sand";
			int r = rand.nextInt(100);
			if(r < 50) {
				this.resource = "cacti";
			}
		}
	}
	
	public void update(QApplication app) {}
	
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
			
			BottomViewBar.viewBar.color = BottomViewBar.TILE_COLOR;
			BottomViewBar.viewBar.leftString = getName();
			if (occupier != null) {
				BottomViewBar.viewBar.middleString = "Press G to see occupier";
			} else {
				BottomViewBar.viewBar.middleString = "No unit on this tile";
			}
			BottomViewBar.viewBar.rightString = "";
			
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
	
	public String getName() {
		switch (type) {
		case DESERT:
			return "Desert";
		case FERTILE_PLAINS:
			return "Fertile Plains";
		case FOREST:
			return "Forest";
		case HIGH_MOUNTAIN:
			return "High Mountain";
		case MOUNTAIN:
			return "Mountain";
		case OCEAN:
			return "Ocean";
		case PLAINS:
			return "Plains";
		case SEA:
			return "Sea";
		case UNGENERATED:
			return "!!INVALID!!";
		default:
			return "!!INVALID!!";
		
		}
	}

	@Override
	public GlobalState getActiveState() {
		return GlobalState.MAIN_GAME;
	}

}
