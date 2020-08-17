package dev.Lenox.tilegame.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import dev.Lenox.tilegame.Game;
import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.bullets.BulletManager;
import dev.Lenox.tilegame.entities.EntityManager;
import dev.Lenox.tilegame.entities.Player1;
import dev.Lenox.tilegame.entities.Player2;
import dev.Lenox.tilegame.items.Health;
import dev.Lenox.tilegame.items.ItemManager;
import dev.Lenox.tilegame.random.RandomNumber;
import dev.Lenox.tilegame.teleporters.TeleportManager;

public abstract class States {
	protected double distanceBetweenPlayers;
	protected static States currentState = null;
	private float alpha = 0f;

	public static void setState(States state) {
		currentState = state;

	}

	public static States getState() {
		return currentState;
	}

	

	// CLASS
	public static Handler handler;

	public States(Handler handler) {
		this.handler = handler;
	}
	//USE THESE METHODS IN CLASSES
	protected void fade(Graphics g, Image img) {
		// FADE
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		Graphics2D g2d = (Graphics2D) g;
		// set the opacity
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		alpha += 0.02f;
		if (alpha >= 1.0f) {
			alpha = 1.0f;
		}
		g.drawImage(img, 0, 0, null);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
	//SPAWNS HEARTS
	protected void spawnHeart(int heartsToSpawn, ItemManager itemManager, int minX, int maxX, int minY, int maxY, int minTime, int maxTime){
		int x;
		int y;
		int time;
		for(int i = 0; i < heartsToSpawn; i ++){
			x = new RandomNumber(minX, maxX).getNumber();
			y = new RandomNumber(minY, maxY).getNumber();
			time = new RandomNumber(minTime, maxTime).getNumber();
			itemManager.addItem(new Health(handler, x, y, time));
		}
	}
	//distance between players using pythagorean theorem
	protected void distanceBetweenPlayers(float player1x, float player1y, float player2x, float player2y){
		float a;
		float b;
		a = player2y - player1y;
		b = player2x - player1x;
		a = a * a;
		b = b * b;
		distanceBetweenPlayers = a + b;
		distanceBetweenPlayers = Math.sqrt(distanceBetweenPlayers);
		 
	}
	//checks if game is gone on for long and enters sudden death
	protected void suddenDeath(){
		if(Game.seconds > 100){
			handler.getState().getItemManager().getItems().clear();
			handler.getState().getPlayer1().maxHealth = 1;
			handler.getState().getPlayer2().maxHealth = 1;
		}
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void collisionX();

	public abstract void collisionY();

	public abstract float getAlpha();

	
	
	
	public abstract ArrayList<Rectangle> getCollision();
	public abstract Player1 getPlayer1();

	public abstract Player2 getPlayer2();

	public abstract EntityManager getEntityManager();

	public abstract BulletManager getBulletManager();
	public abstract ItemManager getItemManager();
	public abstract TeleportManager getTeleportManager();

}
