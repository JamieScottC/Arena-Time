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

public class Island extends States {
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

	public Island(Handler handler, float x1, float y1, float x2, float y2) {
		super(handler);
		// entities
		entityManager = new EntityManager(handler);
		entityManager.addEntity(new FaceRock(handler, 864, 576));
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
		collision.add(new Rectangle(0, 96, 128, 32));
		collision.add(new Rectangle(0, 128, 32, 32));
		collision.add(new Rectangle(0, 320, 32, 128));
		collision.add(new Rectangle(0, 608, 32 * 2, 32));
		collision.add(new Rectangle(0, 640, 32, 128));
		collision.add(new Rectangle(0, 640, 128, 32));
		collision.add(new Rectangle(96, 672, 64, 64));
		collision.add(new Rectangle(96, 736, 32, 128));
		collision.add(new Rectangle(96, 832, 128, 32));
		collision.add(new Rectangle(224, 864, 128, 32));
		collision.add(new Rectangle(352, 832, 128, 32));
		collision.add(new Rectangle(480, 800, 288, 32));
		collision.add(new Rectangle(768, 832, 64, 32));
		collision.add(new Rectangle(832, 864, 288, 32));
		collision.add(new Rectangle(1120, 704, 128, 160));
		collision.add(new Rectangle(1248, 736, 32, 32));
		collision.add(new Rectangle(1280, 768, 32, 32));
		collision.add(new Rectangle(1312, 800, 32, 32));
		collision.add(new Rectangle(1344, 832, 96, 32));
		collision.add(new Rectangle(1440, 800, 32, 32));
		collision.add(new Rectangle(1472, 736, 96, 64));
		collision.add(new Rectangle(1536, 704, 64, 32));
		collision.add(new Rectangle(1568, 544, 32, 160));
		collision.add(new Rectangle(1536, 128, 32, 416));
		collision.add(new Rectangle(1440, 416, 96, 64));
		collision.add(new Rectangle(1504, 352, 32, 64));
		collision.add(new Rectangle(1504, 320, 32, 32));
		collision.add(new Rectangle(1440, 64, 64, 32));
		collision.add(new Rectangle(1408, 32, 32, 32));
		collision.add(new Rectangle(1280, 0, 128, 32));
		collision.add(new Rectangle(960, 32, 320, 32));
		collision.add(new Rectangle(1152, 64, 32, 32));
		collision.add(new Rectangle(864, 0, 96, 32));
		collision.add(new Rectangle(544, 0, 128, 32));
		collision.add(new Rectangle(416, 32, 128, 32));
		collision.add(new Rectangle(128, 64, 288, 32));
		collision.add(new Rectangle(256, 96, 32, 32));
		collision.add(new Rectangle(1216, 224, 32, 32));
		collision.add(new Rectangle(480, 224, 736, 96));
		collision.add(new Rectangle(512, 224, 576, 32));
		collision.add(new Rectangle(608, 192, 288, 32));
		collision.add(new Rectangle(800, 160, 32, 32));
		collision.add(new Rectangle(448, 256, 32, 32));
		collision.add(new Rectangle(448, 416, 800, 128));
		collision.add(new Rectangle(384, 448, 64, 32));
		collision.add(new Rectangle(160, 480, 256, 32));
		collision.add(new Rectangle(224, 512, 160, 32));
		collision.add(new Rectangle(352, 544, 64, 32));
		collision.add(new Rectangle(416 + 32*2, 576, 256, 128));
		collision.add(new Rectangle(640, 512, 32, 64));
		collision.add(new Rectangle(1248, 448, 32, 128));
		collision.add(new Rectangle(1056, 544, 192, 64));


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
			fade(g, Assets.island);
			g.setColor(Color.GRAY);
			/*for (int i = 0; i < collision.size(); i++) {
				g.drawRect(collision.get(i).x, collision.get(i).y, collision.get(i).width, collision.get(i).height);
			}*/
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

	@Override
	public ArrayList<Rectangle> getCollision() {
		// TODO Auto-generated method stub
		return collision;
	}
}
