package dev.Lenox.tilegame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.bullets.Bullet1;
import dev.Lenox.tilegame.entities.statics.Pole;
import dev.Lenox.tilegame.gfx.Animation;
import dev.Lenox.tilegame.gfx.Assets;
import dev.Lenox.tilegame.teleporters.Teleporter1;

public class Player1 extends Creature {
	
	// PLAYER 1
	private int bullet1Count = 9;
	private int reloadCounter1 = 1;
	private int ammoCapacity1 = 9;

	private boolean checkBullet1;
	private boolean reloading1 = false;
	
private Timer mytimer;
	
	private int counter1 = 0;
	private int count1 = 0;
	//REST
	private Handler handler;
	public static Animation animDown, animUp, animLeft, animRight, animInteract;
	float oldx, oldy;
	private Bullet1 bullet;
	public static BufferedImage last =Assets.player_right;
	public static int money = 0;
	public static int phealth1 = DEAFULT_HEALTH;
	public static boolean shotGun = false;
	private Timer myTimer;
	private int counter = 10;
	private int count = 0;
	private Teleporter1 teleporter;
	private boolean teleporterActive = false;
	public Player1(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEAFULT_CREATURE_WIDTH, Creature.DEAFULT_CREATURE_HEIGHT);
		this.handler = handler;
		
		
	}
	public void tick() {
		//HUD

		bounds = new Rectangle((int)x + 10,(int) y + 0, width - 25, height - 0);
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
		//FIRING AND RELOADING
		fire1();
		check1();
		reload1();
		System.out.println(teleporterActive);
		System.out.println(teleCooldown);

		// CHECKERS
	//	System.out.println(getMoney());
		// System.out.println(xMove);
		// System.out.println(speed);
		//System.out.println((int)x + " " +(int) y);
		//System.out.println(phealth);
		//check health
		if(phealth1 > maxHealth){
			phealth1 = maxHealth;
		}
	}


	@Override
	public void render(Graphics g) {
		//HUD
		//PLAYER
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		
	}

	
	
	//FIRING METHODS AND RELOADING 
	public void fire1() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_F) && bullet1Count > 0 && !reloading1) {
			checkBullet1 = false;
			if(!shotGun){
			handler.getState().getBulletManager().addBullet(new Bullet1(handler, x + 30, y + 15));
			bullet1Count--;
			}else if(shotGun){
				handler.getState().getBulletManager().addBullet(new Bullet1(handler, x + 30, y - 30));
				handler.getState().getBulletManager().addBullet(new Bullet1(handler, x + 30, y + 15));
				handler.getState().getBulletManager().addBullet(new Bullet1(handler, x + 30, y + 60));
				handler.getState().getBulletManager().addBullet(new Bullet1(handler, x + 30, y + 105));
				bullet1Count-=4;
			}
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_R) && checkBullet1) {
			if (reloadCounter1 == 5) {
				reloading1 = true;
				reloadCounter1 = 0;
			}
			bullet1Count = ammoCapacity1;
			checkBullet1 = false;
			reloadCounter1++;
		}
	}

	public void check1() {
		if(bullet1Count < 0)
			bullet1Count = 0;
		if (bullet1Count < ammoCapacity1) {
			checkBullet1 = true;
		}
		
	}

	public void reload1() {
		if (reloading1) {

			if (count1 == 0) {
				mytimer = new Timer();
				mytimer.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						counter1++;
					}
				}, 1000, 1000);
				count1 = 1;
			}

			if (counter1 >= 5) {
				reloading1 = false;
				counter1 = 0;
				mytimer.cancel();
				mytimer.purge();
				count1 = 0;
			}
		}
	}
	

	public Rectangle getBounds() {
		return bounds;
	}
	@Override
	public void die() {
		System.out.println("Not even possible what");
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
		
		if (handler.getKeyManager().up)
			yMove = -speed;

		if (handler.getKeyManager().down)
			yMove = speed;

		if (handler.getKeyManager().left)
			xMove = -speed;

		if (handler.getKeyManager().right)
			xMove = speed;
		
		 
	}


	public BufferedImage getCurrentAnimationFrame() {

		if (xMove < 0 && x != oldx) {
			return Assets.player_left;
		}
		if (xMove > 0 && x != oldx) {
			return Assets.player_right;
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
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C) && teleCooldown){
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
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_1) && teleCooldown && teleporterActive){
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
	public boolean isTeleCooldown(){
		return teleCooldown;
	}
	public void setTeleCooldown(boolean teleCooldown){
		this.teleCooldown = teleCooldown;
	}
	public int getCounter(){
		return counter;
	}
	public boolean isReloading1() {
		return reloading1;
	}
	public void setReloading1(boolean reloading1) {
		this.reloading1 = reloading1;
	}
	public int getBullet1Count() {
		return bullet1Count;
	}

	public void setBullet1Count(int bullet1Count) {
		this.bullet1Count = bullet1Count;
	}
	public int getAmmoCapacity1() {
		return ammoCapacity1;
	}

	public void setAmmoCapacity1(int ammoCapacity1) {
		this.ammoCapacity1 = ammoCapacity1;
	}
}