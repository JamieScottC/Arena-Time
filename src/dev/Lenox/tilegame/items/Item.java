package dev.Lenox.tilegame.items;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.Lenox.tilegame.Handler;

public abstract class Item {

	public float x;
	public float y;
	protected int width, height;
	protected Handler handler;
	protected Rectangle bounds;
	protected boolean active = false;
	protected boolean pickedUp1 = false;
	protected boolean pickedUp2 = false;
	public Item(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0, 0, width, height);
	}
	

	public abstract void player1PickUp();
	public abstract void player2PickUp();
	public abstract void ability();
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	
public Rectangle getBounds(){
	return bounds;
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
	
	
}
