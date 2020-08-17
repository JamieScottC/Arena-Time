package dev.Lenox.tilegame.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.Clip;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.bullets.Bullet;
import dev.Lenox.tilegame.bullets.Bullet1;
import dev.Lenox.tilegame.bullets.Bullet2;
import dev.Lenox.tilegame.bullets.BulletManager;
import dev.Lenox.tilegame.bullets.HoamingBullet1;
import dev.Lenox.tilegame.entities.Entity;
import dev.Lenox.tilegame.entities.EntityManager;
import dev.Lenox.tilegame.entities.Player1;
import dev.Lenox.tilegame.entities.Player2;
import dev.Lenox.tilegame.entities.statics.FaceRock;
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

public class Hedge extends States {
	// PLAYER 1
	private Player1 player1;
	private Player2 player2;
	// ENTITY
	private EntityManager entityManager;
	// BULLET
	private BulletManager bulletManager;
	// TELEPORTER
	private TeleportManager teleportManager;
	// ITEMS
	private ItemManager itemManager;
	
	// RectangleS
	private ArrayList<Rectangle> collision;
	// FONTS
	private Font font;

	// Sound
	SoundLoader musicManager = new SoundLoader();
	private Clip musicClip = musicManager.loadSound("/Sound/TheLoomingBattle.wav");
	private boolean startMusic = true;

	public Hedge(Handler handler, float x1, float y1, float x2, float y2) {
		super(handler);
		// entities
		entityManager = new EntityManager(handler);
		//bullets
		bulletManager = new BulletManager(handler);
		itemManager = new ItemManager(handler);
		teleportManager = new TeleportManager(handler);
		player1 = new Player1(handler, x1, y1);
		player2 = new Player2(handler, x2, y2);
		//entities
		//items
		itemManager.addItem(new Boots(handler, 820, new RandomNumber(31, 95).getNumber()));
		itemManager.addItem(new ExtraAmmo(handler, 736, 734));
		itemManager.addItem(new Range(handler, 1600/2, 361));
		itemManager.addItem(new Shotgun(handler, 884, 744));
		//hearts TODO make x and y values actually work so they dont appear on water
		spawnHeart(5, itemManager, 200, 1401, 200, 801, 5, 60);
		spawnHeart(5, itemManager, 200, 1401, 200, 801, 60, 100);
		// RectangleS
		collision = new ArrayList<Rectangle>();
		collision.add(new Rectangle (128, 32, 192, 160 - 32));
		collision.add(new Rectangle (128, 288 + 32, 192, 320 - 32) );
		collision.add(new Rectangle (128, 736 + 32, 96, 192 - 32));
		collision.add(new Rectangle (288, 704 + 32, 256, 128 - 32));
		collision.add(new Rectangle (448, 64 + 32, 416, 192 - 32));
		collision.add(new Rectangle (640, 384 + 32, 160, 288 - 32));
		collision.add(new Rectangle (640, 864 + 32, 832, 64 - 32));
		collision.add(new Rectangle (928, 608 + 32, 160, 160 - 32));
		collision.add(new Rectangle (960, 32, 480, 64 - 32));
		collision.add(new Rectangle (1056, 192 + 32, 192, 224 - 32));
		collision.add(new Rectangle (1216, 544 + 32, 256, 192 - 32));
		collision.add(new Rectangle (1376, 160 + 32, 96, 256 - 32));
		

		// fonts
		FontLoader fontLoader = new FontLoader();
		font = fontLoader.loadFont("/fonts/Pixeled.ttf", 10);

	}

	public void tick() {
		// CHECKS THAT THE GAME IS NOT OVER
		if (!Bullet1.over && !Bullet2.over) {
			// Start music
			if (startMusic) {
				musicManager.playClip(musicClip);
				startMusic = false;
			}
			// LOOPS MUSIC
			if (musicManager.getClip().isRunning() == false)
				musicManager.playClip(musicClip);
			// SEE EACH METHODS IF YOU WANT TO KNOW WHAT THEY DO
			// PLAYERS TICKING
			player1.tick();
			player2.tick();
			// ENTITY TICKING
			entityManager.tick();
			// BULLET TICKING
			bulletManager.tick();
			// teleport ticking
			teleportManager.tick();
			
			// ITEMS TICKING
			itemManager.tick();
			// COLLISION CALLING
			collisionX();
			collisionY();
			checkEntityCollisions();
			//FIND DISTANCE BETWEEN PLAYERS
			distanceBetweenPlayers(player1.x, player1.y, player2.x, player2.y);
			// CHECKERS
			// System.out.println(bullet1Count + " " + reloadCounter1 + " " +
			// counter + " " + bullet2Count + " "
			// + reloadCounter2);
		}

	}

	public void render(Graphics g) {
		if (!Bullet1.over && !Bullet2.over && !HoamingBullet1.over) {

			// FADES IN BG IMAGE
			fade(g, Assets.hedge);
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
			// teleport rendering
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
		} else if (player1.getX() >= 1515) {
			player1.setX(1515);
		}
		if (player2.getX() <= 20) {
			player2.setX(20);
		} else if (player2.getX() >= 1515) {
			player2.setX(1515);
		}

	}

	public void collisionY() {
		if (player1.getY() <= 5) {
			player1.setY(5);
		} else if (player1.getY() >= 870) {
			player1.setY(870);
		}
		if (player2.getY() <= 5) {
			player2.setY(5);
		} else if (player2.getY() >= 870) {
			player2.setY(870);
		}
	}

	// CHECK IF PLAYER IS COLLIDING WITH ANY ENTITIES OR RectangleS
	public void checkEntityCollisions() {
		Rectangle r1 = new Rectangle((int) player1.x + 10, (int) player1.y, player1.getWidth() - 20,
				player1.getHeight());
		Rectangle r2 = new Rectangle((int) player2.x + 10, (int) player2.y, player2.getWidth() - 20,
				player2.getHeight());
		for (int i = 0; i < collision.size(); i++) {
			Rectangle r = collision.get(i);
			if (r1.intersects(r)) {
				player1.x = player1.getOldx();
				player1.y = player1.getOldy();
			}
			if (r2.intersects(r)) {
				player2.x = player2.getOldx();
				player2.y = player2.getOldy();
			}
		}
		for (Entity e : handler.getState().getEntityManager().getEntities()) {
			if (e.getBounds().intersects(r1)) {
				player1.x = player1.getOldx();
				player1.y = player1.getOldy();

			}

			if (e.getBounds().intersects(r2)) {
				player2.x = player2.getOldx();
				player2.y = player2.getOldy();

			}
		}
			
		}

	// GETTERS AND SETTERS

	public ArrayList<Rectangle> getCollision() {
		return collision;
	}

	public void setCollision(ArrayList<Rectangle> collision) {
		this.collision = collision;
	}

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

	public TeleportManager getTeleportManager() {
		return teleportManager;

	}

	@Override
	public ItemManager getItemManager() {
		// TODO Auto-generated method stub
		return itemManager;
	}
}
