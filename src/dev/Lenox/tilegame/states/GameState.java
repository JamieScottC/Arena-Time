package dev.Lenox.tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.bullets.Bullet1;
import dev.Lenox.tilegame.bullets.Bullet2;
import dev.Lenox.tilegame.bullets.BulletManager;
import dev.Lenox.tilegame.bullets.HoamingBullet1;
import dev.Lenox.tilegame.entities.Entity;
import dev.Lenox.tilegame.entities.EntityManager;
import dev.Lenox.tilegame.entities.Player1;
import dev.Lenox.tilegame.entities.Player2;
import dev.Lenox.tilegame.entities.statics.Pole;
import dev.Lenox.tilegame.entities.statics.Rock;
import dev.Lenox.tilegame.gfx.Assets;
import dev.Lenox.tilegame.gfx.FontLoader;
import dev.Lenox.tilegame.gfx.SoundLoader;
import dev.Lenox.tilegame.items.Boots;
import dev.Lenox.tilegame.items.ExtraAmmo;
import dev.Lenox.tilegame.items.ItemManager;
import dev.Lenox.tilegame.items.Range;
import dev.Lenox.tilegame.items.Shotgun;
import dev.Lenox.tilegame.random.RandomNumber;
import dev.Lenox.tilegame.teleporters.TeleportManager;

public class GameState extends States {
	private Player1 player1;
	private Player2 player2;
	// ENTITY
	private EntityManager entityManager;
	// BULLET
	private BulletManager bulletManager;
	//TELEPORTER
	private TeleportManager teleportManager;
	// ITEMS
	private ItemManager itemManager;
	// TIMERS
	
	

	// RECTANGLES
	private Rectangle lava ;
	
	// FONTS
	private Font font;	
	// Sound
	SoundLoader musicManager = new SoundLoader();
	private Clip musicClip = musicManager.loadSound("/Sound/TheLoomingBattle.wav");
	private boolean startMusic = true;
	public GameState(Handler handler, float x1, float y1, float x2, float y2) {
		super(handler);
		
		// entities
		entityManager = new EntityManager(handler);
		bulletManager = new BulletManager(handler);
		itemManager = new ItemManager(handler);
		teleportManager = new TeleportManager(handler);
		player1 = new Player1(handler, x1, y1);
		player2 = new Player2(handler, x2, y2);
		entityManager.addEntity(new Pole(handler, 500, 500));
		entityManager.addEntity(new Pole(handler, 900, 500));
		entityManager.addEntity(new Pole(handler, 758, 678));
		entityManager.addEntity(new Pole(handler, 445, 278));
		entityManager.addEntity(new Rock(handler, 1325, 236));
		//bulletManager.addBullet(new HoamingBullet1(handler, 500, 500));
		itemManager.addItem(new Boots(handler, 820, new RandomNumber(100, 500).getNumber()));
		itemManager.addItem(new ExtraAmmo(handler, 820, new RandomNumber(100, 500).getNumber()));
		itemManager.addItem(new Range(handler, 620, new RandomNumber(149, 230).getNumber()));
		itemManager.addItem(new Shotgun(handler, 720, new RandomNumber(100, 230).getNumber()));
		//heart
		spawnHeart(5, itemManager, 200, 1401, 200, 800, 5, 60);
		spawnHeart(5, itemManager, 200, 1401, 200, 800, 60, 100);
		
		// RECTANGLES
		lava = new Rectangle(560, 300, 260, 230);
		// fonts
		FontLoader fontLoader = new FontLoader();
		font = fontLoader.loadFont("/fonts/Pixeled.ttf", 10);
		
	}

	public void tick() {
		// CHECKS THAT THE GAME IS NOT OVER
		if (!Bullet1.over && !Bullet2.over) {
			//Start music
			if(startMusic){
				musicManager.playClip(musicClip);
				startMusic = false;
			}
					
			//LOOPS MUSIC
			if(musicManager.getClip().isRunning() == false)
			musicManager.playClip(musicClip);
			// SEE EACH METHODS IF YOU WANT TO KNOW WHAT THEY DO
			// PLAYERS TICKING
			player1.tick();
			player2.tick();
			// ENTITY TICKING
			entityManager.tick();
			// BULLET TICKING
			bulletManager.tick();
			//teleport ticking
			teleportManager.tick();
			// BULLET 1 METHODS
			
			// BULLET 2 METHODS
			
			// ITEMS TICKING
			itemManager.tick();
			// COLLISION CALLING
			collisionX();
			collisionY();
			checkEntityCollisions();
			//FIND DISTANCE BETWEEN PLAYERS
			distanceBetweenPlayers(player1.x, player1.y, player2.x, player2.y);
			//sudden death
			suddenDeath();
			//System.out.println(distanceB);
			// CHECKERS
			// System.out.println(bullet1Count + " " + reloadCounter1 + " " +
			// counter + " " + bullet2Count + " "
			// + reloadCounter2);
		}

	}

	public void render(Graphics g) {
		if (!Bullet1.over && !Bullet2.over && !HoamingBullet1.over) {
			// FADES IN BG IMAGE
			fade(g, Assets.lava);
			g.setColor(Color.GRAY);
			// PLAYERS RENDERING
			player1.render(g);
			player2.render(g);
			// ENTITIES RENDERING - SEE CONSTRUCTOR TO SEE WHAT ENTITES ARE IN
			// GAME
			entityManager.render(g);
			// BULLETS RENDERING
			bulletManager.render(g);
			// ITEMS RENDERING
			itemManager.render(g);
			//teleport rendering
			teleportManager.render(g);
			// HUD
			g.setColor(Color.RED);
			g.setFont(font);
			// Player1
			g.drawString("Ammo: " + player1.getBullet1Count(), 10, 15);
			g.drawString("Health: " + Player1.phealth1, 10, 45);
			g.drawString("Teleporter:" + player1.getCounter(), 10, 85);
			// player2
			g.drawString("Ammo: " + player2.getBullet2Count(), 1530, 15);
			g.drawString("Health: " + Player2.phealth2, 1520, 45);
			g.drawString("Teleporter:" + player2.getCounter(), 1480, 85);
			// CHECKS IF GUNS ARE JAMMED
			if (player1.isReloading1()) {
				g.drawString("Gun Jammed!", 10, 65);
			}
			if (player2.isReloading2()) {
				g.drawString("Gun Jammed!", 1490, 65);
			}
			// GAME OVER STATE, CHECKS WHICH PLAYER WON
			// TODO: MAKE GAME OVER STATE MORE REWARDING
		} else if (Bullet1.over || HoamingBullet1.over) {
			musicManager.stopClip(musicClip);
			GameOver1 gameOver1 = new GameOver1(handler);
			gameOver1.render(g);
		} else if (Bullet2.over) {
			musicManager.stopClip(musicClip);
			GameOver2 gameOver2 = new GameOver2(handler);
			gameOver2.render(g);

		}
	}

	
	

	

	// COLLISION WITH EDGE OF MAP
	public void collisionX() {
		if (player1.getX() <= 20) {
			player1.setX(20);
		} else if (player1.getX() >= 1248) {
			player1.setX(1248);
		}
		if (player2.getX() <= 191) {
			player2.setX(191);
		} else if (player2.getX() >= 1515) {
			player2.setX(1515);
		}

	}

	public void collisionY() {
		if (player1.getY() <= 79) {
			player1.setY(79);
		} else if (player1.getY() >= 845) {
			player1.setY(845);
		}
		if (player2.getY() <= 79) {
			player2.setY(79);
		} else if (player2.getY() >= 845) {
			player2.setY(845);
		}
	}

	// CHECK IF PLAYER IS COLLIDING WITH ANY ENTITIES OR RECTANGLES
	public void checkEntityCollisions() {
		Rectangle r1 = new Rectangle((int) player1.x + 10, (int) player1.y, player1.getWidth() - 20,
				player1.getHeight());
		Rectangle r2 = new Rectangle((int) player2.x + 10, (int) player2.y, player2.getWidth() - 20,
				player2.getHeight());

		for (Entity e : handler.getState().getEntityManager().getEntities()) {
			if (e.getBounds().intersects(r1) || r1.intersects(lava)) {
				player1.x = player1.getOldx();
				player1.y = player1.getOldy();

			}
			if (e.getBounds().intersects(r2) || r2.intersects(lava)) {
				player2.x = player2.getOldx();
				player2.y = player2.getOldy();

			}
		}
	}

	// GETTERS AND SETTERS

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Player1 getPlayer1() {
		// TODO Auto-generated method stub
		return player1;
	}

	@Override
	public float getAlpha() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Player2 getPlayer2() {
		// TODO Auto-generated method stub
		return player2;
	}

	@Override
	public BulletManager getBulletManager() {
		// TODO Auto-generated method stub
		return bulletManager;
	}

	

	
	public TeleportManager getTeleportManager(){
		return teleportManager;
		
	}
	public ItemManager getItemManager() {
		// TODO Auto-generated method stub
		return itemManager;
	}

	@Override
	public ArrayList<Rectangle> getCollision() {
		// TODO Auto-generated method stub
		return null;
	}

}
