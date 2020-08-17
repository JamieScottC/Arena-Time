package dev.Lenox.tilegame.bullets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.gfx.Assets;

public class HoamingBullet1 extends Bullet {
	private Handler handler;
	private int targetX;
	private int targetY;
	private float speed;
	private float xMove, yMove;
	public static boolean over = false;
	public HoamingBullet1(Handler handler, float x, float y) {
		super(handler, x, y, 25, 15);
		this.handler = handler;
		speed = 1f;

	}

	public void tick() {
		bounds = new Rectangle((int) x, (int) y, width - 8, height);
		// Find person
		findTarget();
		// Moving
		Move();
		die();
	//	System.out.println((int) x + " " + (int) y + "     " + targetX + " " + targetY);
		System.out.println(over);
	}

	@Override
	public void render(Graphics g) {
		// Bullet
		g.drawImage(Assets.orb, (int) x, (int) y, width, height, null);
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void die() {
		if(bounds.intersects(handler.getState().getPlayer2().getBounds())){
		over = true;
		}
	}

	public void Move() {
		x += xMove;
		y += yMove;
	}

	private void findTarget() {
		
		//lmao horrible first try at path finding 
		//TODO: LEARN A* PATHFINDING 
		xMove = 0;
		yMove = 0;
		targetX = (int) handler.getState().getPlayer2().getX();
		targetY = (int) handler.getState().getPlayer2().getY();
		if ((int) y > (targetY - 5) && (int) y < (targetY + 5))
			y = targetY;
		if ((int) x > (targetX - 5) && (int) x < (targetX + 5))
			x = targetX;

		if ((int) y > targetY)
			yMove = -speed;

		else if ((int) y < targetY) {
			yMove = speed;
		} else if ((int) y == targetY) {
			yMove = 0;
		
		}

		if ((int) x > targetX) {
			xMove = -speed;
		}

		else if ((int) x < targetX) {
			xMove = speed;
		} else if ((int) x == targetX) {
			xMove = 0;
		}

	}

}