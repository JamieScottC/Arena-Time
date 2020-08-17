package dev.Lenox.tilegame.entities;

import dev.Lenox.tilegame.Handler;

public abstract class Creature extends Entity {
	
	public static final float DEAFULT_SPEED = 7f;
	public static final int DEAFULT_CREATURE_WIDTH = 55;
	public static final int DEAFULT_CREATURE_HEIGHT = 55;
	public static final int DEAFULT_DAMAGE = 1;
	protected int damage;
	protected float speed;
	public float xMove;
	public float yMove;
	protected boolean teleCooldown = true;
	public int maxHealth = 10;
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		speed = DEAFULT_SPEED;
		damage = DEAFULT_DAMAGE;
	}
	public void Move(){
		moveX();
		moveY();	
		
	}

	
	public void moveX(){
		x += xMove;
	}
	public void moveY(){
		y += yMove;
	}
	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}


	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
