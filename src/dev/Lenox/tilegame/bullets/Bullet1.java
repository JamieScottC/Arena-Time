package dev.Lenox.tilegame.bullets;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.entities.Entity;
import dev.Lenox.tilegame.entities.Player2;
import dev.Lenox.tilegame.gfx.Assets;
import dev.Lenox.tilegame.states.States;

public class Bullet1 extends Bullet{
	public float moveX, moveY;
	private States gameOver;
	private Rectangle bounds;
	public static boolean over = false;
	private Timer myTimer;
	private int count = 0;
	private float counter = 0f;
	public static float range = 1f;
	private int countB = 0;
	public Bullet1(Handler handler, float x, float y) {
		super(handler, x, y, 15	, 10);
		myTimer = new Timer();
	}

	

	@Override
	public void tick() {
		
		if(count == 0){
			myTimer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					counter++;
						
				}
			}, 600, 600);
			count = 1;
		}
		removeBullet();
		//System.out.println(range);
		//System.out.println(counter);
		moveX();
		moveY();
		bounds = new Rectangle((int) x, (int) y, width - 8, height);
		takeHit();
		die();
		moveBullet();
		col();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.orb, (int)x,(int) y, width, height, null);
		
	}

	@Override
	public void die() {
		if(Player2.phealth2 <= 0){
		over = true;
		}
	}
	
	public void takeHit(){
		if(bounds.intersects(handler.getState().getPlayer2().getBounds())){
			handler.getState().getBulletManager().getEntities().remove(this);
			shootSoundManager.playClip(shootSoundClip);
			Player2.phealth2-=handler.getState().getPlayer1().getDamage();
		}
	}
	public void col(){			
		for (Entity e : handler.getState().getEntityManager().getEntities()) {
			if(bounds.intersects(e.getBounds())){
				handler.getState().getBulletManager().getEntities().remove(this);
			}
			}
		if(States.getState() == handler.getGame().hedge){
		for(int i = 0;i < handler.getState().getCollision().size(); i ++){
			if(bounds.intersects(handler.getState().getCollision().get(i))){
				handler.getState().getBulletManager().getEntities().remove(this);
			}
		}
		}
		
}
public void removeBullet(){
	if(counter == range){
		handler.getState().getBulletManager().getEntities().remove(this);
	}
}
	public void moveBullet(){
		
		if(countB == 0){
		if(handler.getState().getPlayer1().getCurrentAnimationFrame() == Assets.player_right || handler.getState().getPlayer1().last == Assets.player_right){
		moveX=bulletVelocity;
		countB = 1;
		}else if(handler.getState().getPlayer1().getCurrentAnimationFrame() == Assets.player_left || handler.getState().getPlayer1().last == Assets.player_left){
			moveX =-bulletVelocity;
			countB = 1;
		}
		}
	}
	public void moveX(){
		x += moveX;
	}
	public void moveY(){
		x += moveY;
	}
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}
}
