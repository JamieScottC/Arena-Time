package dev.Lenox.tilegame.bullets;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.sound.sampled.Clip;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.entities.Entity;
import dev.Lenox.tilegame.gfx.SoundLoader;

public abstract class Bullet {

	public float x;
	public float y;
	protected int width, height;
	protected Handler handler;
	public int health;
	protected Rectangle bounds;
	public static final int DEAFULT_HEALTH = 30;
	protected boolean active = true;
	protected SoundLoader shootSoundManager =  new SoundLoader();
	protected Clip shootSoundClip = shootSoundManager.loadSound("/Sound/selectNoise.wav");
	protected float bulletVelocity = 37f;


	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
public Rectangle getBounds(){
	return bounds;
}
	public Bullet(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEAFULT_HEALTH;
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}
	
	public Rectangle getCollisionBounds(){
		return new Rectangle( bounds.x, bounds.y, bounds.width, bounds.height);
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public abstract void tick();

	public abstract void render(Graphics g);
	public abstract void die();
}
