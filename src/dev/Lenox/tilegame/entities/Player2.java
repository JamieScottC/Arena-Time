package dev.Lenox.tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.bullets.Bullet2;
import dev.Lenox.tilegame.gfx.Animation;
import dev.Lenox.tilegame.gfx.Assets;
import dev.Lenox.tilegame.teleporters.Teleport;
import dev.Lenox.tilegame.teleporters.Teleporter1;

public class Player2 extends Creature {
	
	// PLAYER 2
		private Player2 player2;
		private int bullet2Count = 9;
		private int reloadCounter2 = 1;
		private int ammoCapacity2 = 9;
		private boolean checkBullet2;
		private boolean reloading2 = false;
		
		private int count2 = 0;
		private Timer mytimer2;
		private int counter2 = 0;
	private Handler handler;
	public static Animation animDown, animUp, animLeft, animRight, animInteract;
	float oldx, oldy;
	public static BufferedImage last = Assets.player_left;
	public static int money = 0;
	public static int phealth2 = DEAFULT_HEALTH;
	public static boolean shotGun = false;
	private int count = 0;
	private int counter = 10;
	private Teleport teleporter;
	private boolean teleporterActive =false;
	Timer myTimer;
	public Player2(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEAFULT_CREATURE_WIDTH, Creature.DEAFULT_CREATURE_HEIGHT);
		this.handler = handler;

	}
	public void tick() {
		//HUD
		bounds = new Rectangle((int)x +10,(int) y + 0, width -25, height - 0);
		// Animations
		lastCurrentFrame();
	
		// Collision
		old();
		// Input
		getInput();
		// Moving
		Move();
		placeTele();
		teleCooldown();
		teleport();
		//BULLETS AND RELOAD
		fire2();
		check2();
		reload2();
		// CHECKERS
	//	System.out.println(getMoney());
		// System.out.println(xMove);
		// System.out.println(speed);
		//System.out.println((int)x + " " +(int) y);
		//System.out.println(phealth);
		//check health
		if(phealth2 > maxHealth){
			phealth2 = maxHealth;
		}
	}


	@Override
	public void render(Graphics g) {
		//HUD
		//PLAYER
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		//g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
		//Rectangle r2 = new Rectangle((int) getX() + 12, (int) getY() + 25, getWidth() - 25, getHeight() - 30);
		//g.setColor(Color.red);
		// g.fillRect(r2.x, r2.y, r2.width, r2.height);
		// g.fillRect(test.getCollisionBounds(0, 0).x,
		// test.getCollisionBounds(0, 0).y, test.getCollisionBounds(0, 0).width,
		// height * 2 );
		// g.fillRect((int) (x + bounds.x), (int) (y + bounds.y), bounds.width,
		// bounds.height);
	}

	//FIRING AND RELOADING CODE
	// BULLET2 PLAYER2
		public void fire2() {
			// TO FIRE, CHECKS IF YOU HAVE AMMO AND THAT YOU ARE NOT RELOADING
			if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_O) && getBullet2Count() > 0 && !isReloading2()) {
				//CHECKS IF HE HAS SHOTGUN
				if(!shotGun){
				// DECREASE PLAYER 2s BULLET COUNT BY ONE EVERYTIME HE FIRES
				setBullet2Count(getBullet2Count() - 1);
				// ADD THE BULLET THE THE GAME AT PLAYERS COORDS
				handler.getState().getBulletManager().addBullet(new Bullet2(handler, x + 30, y + 15));
				}else if(shotGun){
					//DECREASES BY 4 SINCE HE HAS SHOTGUN
					setBullet2Count(getBullet2Count() - 4);
					//ADDS 4 BULLETS
					handler.getState().getBulletManager().addBullet(new Bullet2(handler, x + 30, y - 30));
					handler.getState().getBulletManager().addBullet(new Bullet2(handler, x + 30, y + 15));
					handler.getState().getBulletManager().addBullet(new Bullet2(handler, x + 30, y + 60));
					handler.getState().getBulletManager().addBullet(new Bullet2(handler, x + 30, y + 105));
				}
			}
			// TO RELOAD, CHECKS THAT YOU ARE ALLOWED TO RELOAD - SEE METHOD
			if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P) && checkBullet2) {
				// CHECKS TO SEE IF IT IS 5TH TIME RELOADING, IF IT IS SET
				// RELOADING2 = TRUE - SEE METHOD reload2(){}
				if (reloadCounter2 == 5) {
					setReloading2(true);
					// SETS RELOADCOUNTER BACK TO 0
					reloadCounter2 = 0;
				}
				// SETS BULLET COUNT BACK TO AMMOCAPACIT AND INCREASES YOUR RELOAD
				// COUNTER - ALSO MAKES SURE YOU CANT RELOAD UNTIL YOU FIRE AGAIN
				setBullet2Count(getAmmoCapacity2());
				checkBullet2 = false;
				reloadCounter2++;
			}
		}

		// CHECK IF YOU CAN RELOAD
		public void check2() {
			//checks if bulletcount is a negative value and makes it 0
			if(getBullet2Count() < 0)
				setBullet2Count(0);
			if (getBullet2Count() < getAmmoCapacity2()) {
				checkBullet2 = true;
			}
		}

		// GUN JAMMING METHOD ON 5TH RELOAD
		public void reload2() {
			// IF ON FIFTH RELOAD RELOADING2 IS SET TO TRUE
			if (isReloading2()) {
				// SET A TIMER FOR 5 SECONDS
				if (count2 == 0) {
					mytimer2 = new Timer();
					mytimer2.scheduleAtFixedRate(new TimerTask() {

						@Override
						public void run() {
							counter2++;
						}
					}, 1000, 1000);
					count2 = 1;
				}
				// IF TIMER HITS 5
				if (counter2 >= 5) {
					// NO LONGER RELOADING
					setReloading2(false);
					// SET COUNTERS BACK TO 0 AND CANCEL TIMERS
					counter2 = 0;
					mytimer2.cancel();
					mytimer2.purge();
					count2 = 0;
				}
			}
		}

	public Rectangle getBounds() {
		return bounds;
	}
	@Override
	public void die() {

	}

	public void old() {
		oldx = x;
		oldy = y;
	}

	public void lastCurrentFrame() {
		last = getCurrentAnimationFrame();

		
		
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if (handler.getKeyManager().up2)
			yMove = -speed;

		if (handler.getKeyManager().down2)
			yMove = speed;

		if (handler.getKeyManager().left2)
			xMove = -speed;

		if (handler.getKeyManager().right2)
			xMove = speed;
		
		 
	}


	public BufferedImage getCurrentAnimationFrame() {

		if (xMove < 0 && x != oldx) {
			return Assets.player_left2;
		}
		if (xMove > 0 && x != oldx) {
			return Assets.player_right2;
		}
		if (yMove < 0 && y != oldy) {
			return last;
		}
		if (yMove > 0 && y != oldy) {
			return last;
		} 
	
		else {
			return last;
		}

	}
	
	public void placeTele(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_0) && teleCooldown){
			teleCooldown = false;
			handler.getState().getTeleportManager().remove(teleporter);
			teleporter = new Teleporter1(handler, x, y, 30, 30);
			handler.getState().getTeleportManager().addTeleport(teleporter);
			teleporterActive = true;
		}	
	}
	
	public void teleCooldown(){
		if(!teleCooldown){
			if(count == 0){
			
		myTimer = new Timer();
		myTimer.scheduleAtFixedRate(new TimerTask(){
			
			@Override
			public void run() {
				counter--;
				
			}
			
		}, 1000, 1000);
		}
			count = 1;
		}
		if(counter <= 0){
			teleCooldown = true;
			counter = 10;
			myTimer.cancel();
			myTimer.purge();
			count = 0;
		}
	}
	public void teleport(){
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_L) && teleCooldown && teleporterActive){
			System.out.println("Teleport");
			x = teleporter.x;
			y = teleporter.y;
			teleCooldown = false;
		}
	}
	

	public float getOldx() {
		return oldx;
	}

	public void setOldx(float oldx) {
		this.oldx = oldx;
	}

	public float getOldy() {
		return oldy;
	}

	public void setOldy(float oldy) {
		this.oldy = oldy;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}
	public int getDamage(){
		return damage;
	}
	public void setDamage(int damage){
		this.damage = damage;
	}
	public int getCounter() {
		return counter;
	}
	public boolean isReloading2() {
		return reloading2;
	}
	public void setReloading2(boolean reloading2) {
		this.reloading2 = reloading2;
	}
	public int getBullet2Count() {
		return bullet2Count;
	}
	public void setBullet2Count(int bullet2Count) {
		this.bullet2Count = bullet2Count;
	}
	public int getAmmoCapacity2() {
		return ammoCapacity2;
	}
	public void setAmmoCapacity2(int ammoCapacity2) {
		this.ammoCapacity2 = ammoCapacity2;
	}

}