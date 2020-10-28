package org.qme.troops;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.lang.Math;

import org.qme.main.GlobalState;
import org.qme.main.QApplication;
import org.qme.main.QObject;
import org.qme.player.PoliticalEntity;
import org.qme.util.QDimension;
import org.qme.vis.Perspective;
import org.qme.vis.QLayer;
import org.qme.vis.QRenderable;
import org.qme.vis.tex.TroopTextureManager;
import org.qme.vis.ui.UIComponent;
import org.qme.world.Tile;

/**
 * The Unit class is for all units, land or sea.
 * @author santiago
 * @since pre2
 */
public abstract class Unit extends QObject implements QRenderable, UIComponent {
	private static final float ABOVE_MORALE = 1.1f;
	private static final float BELOW_MORALE = 1.15f;
	
	public Unit(QApplication app, Tile tile,
			int a, int d, int h, int m, int attacks, UnitType type) {	// Again, idk and this kills errors
		super(app);
		
		tileOn = tile;
		
		attack = a;
		defense = d;
		health = h;
		movement = m;
		this.attacks = attacks;
		
		this.currentAttack = this.attack;
		this.currentDefense = this.defense;
		this.currentHealth = this.health;
		this.currentMovement = this.movement;
		this.currentAttacks = this.attacks;
		
		this.type = type;
		
		texture = TroopTextureManager.getTexture(type);
		
	}
	
	protected BufferedImage texture;
	
	private boolean actionable = true;	// Whether or not a unit can do stuff (aka it's dead)
	
	public Tile tileOn;
	public int morale = 0;
	public UnitType type;
	public PoliticalEntity owner;
	
	private double attack;
	private double defense;
	private double health;
	private double movement;
	private int attacks;
	
	public double getAttack() { return this.attack; }
	public double getDefense() { return this.defense; }
	public double getHealth() { return this.health; }
	public double getMovement() { return this.movement; }
	public int getAttacks() { return this.attacks; }
	
	public double currentAttack;
	public double currentDefense;
	public double currentHealth;
	public double currentMovement;
	public int currentAttacks;
	
	public double currentAttack() { return this.currentAttack; }
	public double currentDefense() { return this.currentDefense; }
	public double currentHealth() { return this.currentHealth; }
	public double currentMovement() { return this.currentMovement; }
	public int currentAttacks() { return this.currentAttacks; }
	
	/**
	 * Call this to check if this can do things
	 * @return Whether or not the unit can act
	 */
	public boolean actionable() { return this.actionable; }
	
	public boolean canMove(Tile target) {
		if(this.actionable()) {
			if(this.movementCalculate(target) > this.currentMovement()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Call this when morale changes (only deals with movement by 1)
	 * @param up
	 */
	protected void moraleEffects(boolean up) {
		if(up && this.morale >= 0) {
			this.health *= ABOVE_MORALE;
			this.defense *= ABOVE_MORALE;
			this.attack *= ABOVE_MORALE;
		} else if(up && this.morale < 0) {
			this.health *= BELOW_MORALE;
			this.defense *= BELOW_MORALE;
			this.attack *= BELOW_MORALE;
		} else if(!up && this.morale > 0) {
			this.health /= ABOVE_MORALE;
			this.defense /= ABOVE_MORALE;
			this.attack /= ABOVE_MORALE;
		} else {
			this.health /= BELOW_MORALE;
			this.defense /= BELOW_MORALE;
			this.attack /= BELOW_MORALE;
		}
		if(up) {
			this.morale++;
		} else {
			this.morale--;
		}
	}
	
	public double attack(Unit defender) {
		if(this.actionable) { 
			double returN = ((2.5 * this.currentAttack() * (this.currentHealth() / this.getHealth())) / defender.currentDefense());
			return returN;
		}
		return 0;
	}
	
	public void takeDamage(int damage) {
		this.currentHealth -= damage;
	}
	
	public void remove() {
		this.actionable = false;
	}
	
	protected double movementCalculate(Tile target) {
		int xDistance = Math.abs(this.tileOn.x - target.x);
		int yDistance = Math.abs(this.tileOn.x - target.x);
		
		if(xDistance == yDistance) {
			return 1.5 * xDistance;
		} else if(xDistance > yDistance) {
			return 1.5 * yDistance + xDistance - yDistance;
		} else {
			return 1.5 * xDistance + yDistance - xDistance;
		}
	}
	
	public void voluntaryMove(Tile target) {
		if(this.actionable && this.movementCalculate(target) <= this.currentMovement()) {
			this.tileOn.occupier = null;
			this.tileOn = target;
			this.tileOn.occupier = this;
		}
	}
	
	public void involuntaryMove(Tile target) {
		this.tileOn.occupier = null;
		this.tileOn = target;
		this.tileOn.occupier = this;
	}

	@Override
	public void mouseClickOn() {
	    // TODO Auto-generated method stub
	
	}
	
	@Override
	public void mouseClickOff() {
	    // TODO Auto-generated method stub
	
	}
	
	@Override
	public void mouseHoverOn() {
	    // TODO Auto-generated method stub
	
	}
	
	@Override
	public void mouseHoverOff() {
	    // TODO Auto-generated method stub
	
	}
    @Override
    /**
     * Paint the texture of the unit
     * on the screen.
     * @author adamhutchings
     * @since pre4
     */
    public void render(Graphics g) {
    	
    	// Get the central dimensions on which to render it
    	QDimension<Float> center = Perspective.worldToScreen(new QDimension<Float>((float) tileOn.x, (float) tileOn.y));
    	
    	center.x -= application.qiscreen.xOffset;
    	center.y -= application.qiscreen.yOffset;
    	
    	// Move the troop up a little
    	center.y -= 25;
    	
    	// Dimensions of the texture
    	double width  = texture.getWidth();
    	double height = texture.getHeight();
    	
    	// Rectangle for later
    	Rectangle texRect = new Rectangle(
    			(int)(center.x - (width / 2)), (int)(center.y - (height / 2)), (int)width, (int)height
    	);
    	
    	// Render it
    	Graphics2D g2d = (Graphics2D) g.create();
    	g2d.setPaint(new TexturePaint(texture, texRect));
    	g2d.fillRect(texRect.x, texRect.y, texRect.width, texRect.height);
    	g2d.dispose();
    	
    }

    @Override
    public QLayer getLayer() {
        // TODO Auto-generated method stub
    	return QLayer.TROOP_LAYER;
    }


    @Override
    public GlobalState getActiveState() {
        // TODO Auto-generated method stub
    	return GlobalState.MAIN_GAME;
    }

    @Override
    public boolean clickIsIn(int x, int y) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void update(QApplication app) {
        // nothing
    }
    
}
