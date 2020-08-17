package dev.Lenox.tilegame.bullets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.entities.Entity;
import dev.Lenox.tilegame.entities.Player1;
import dev.Lenox.tilegame.entities.Player2;
import dev.Lenox.tilegame.gfx.Assets;
import dev.Lenox.tilegame.states.States;

public class Bullet2 extends Bullet{
	public float moveX, moveY;
	public static boolean over = false;
	private int counter = 0;
	private int count = 0;
	private Timer myTimer;
	public static int range = 1;
	private int countB = 0;
	public Bullet2(Handler handler, float x, float y) {
		super(handler, x, y, 15, 10);
		myTimer = new Timer();
	}


	@Override
	public void tick() {
		if(count == 0 && handler.getState().getPlayer2().getCurrentAnimationFrame() == Assets.player_left2 || count ==0 && handler.getState().getPlayer2().last == Assets.player_right2){
			myTimer.scheduleAtFixedRate(new TimerTask() {
				
				@Override
				public void run() {
					counter++;
						
				}
		}, 600, 600);
			count = 1;
		}
		
		moveBullet();
		moveX();
		moveY();
		bounds = new Rectangle((int) x, (int) y, width - 8, height);
		takeHit();
		die();
		collisionWithEntityOrRectangle();
		removeBullet();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.orb, (int)x,(int) y, width, height, null);
		
	}
	public void collisionWithEntityOrRectangle(){			
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
	@Override
	public void die() {
		if(Player1.phealth1 <= 0){
			over = true;
		}
	}
	
	public void takeHit(){
		if(bounds.intersects(handler.getState().getPlayer1().getBounds())){
			handler.getState().getBulletManager().getEntities().remove(this);
			shootSoundManager.playClip(shootSoundClip);
			Player1.phealth1-=handler.getState().getPlayer2().getDamage();
		}
	}
	public void moveBullet(){
		if(countB == 0){
			if(handler.getState().getPlayer2().getCurrentAnimationFrame() == Assets.player_right2 || handler.getState().getPlayer2().last == Assets.player_right2){
			moveX=bulletVelocity;
			countB = 1;
			}else if(handler.getState().getPlayer2().getCurrentAnimationFrame() == Assets.player_left2 || handler.getState().getPlayer2().last == Assets.player_left2){
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
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

}
