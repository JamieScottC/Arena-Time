package dev.Lenox.tilegame.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.sound.sampled.Clip;


import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.UI.ClickListener;
import dev.Lenox.tilegame.UI.UIImageButton;
import dev.Lenox.tilegame.UI.UIManager;
import dev.Lenox.tilegame.UI.UIMap;
import dev.Lenox.tilegame.bullets.BulletManager;
import dev.Lenox.tilegame.entities.EntityManager;
import dev.Lenox.tilegame.entities.Player1;
import dev.Lenox.tilegame.entities.Player2;
import dev.Lenox.tilegame.gfx.Assets;
import dev.Lenox.tilegame.gfx.SoundLoader;
import dev.Lenox.tilegame.items.ItemManager;
import dev.Lenox.tilegame.teleporters.TeleportManager;

public class MenuState extends States {

	private UIManager uiManager;
	private float alpha = 0f;
	private int ammoCapacity1;
	private int ammoCapacity2;
	private States statePicked;
	private Rectangle highlightMap;
	// sound
	SoundLoader musicManager = new SoundLoader();
	private Clip musicClip = musicManager.loadSound("/Sound/Battle_in_the_winter.wav");
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		statePicked = handler.getGame().getGameState();
		//HIGHLIGHT MAP
		highlightMap = new Rectangle(142, 595, 280, 165);
		//START BUTTON
		uiManager.addObject(new UIImageButton(200, 300, 295, 97, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				
				handler.getMouseManager().setUIManager(null);
				musicManager.stopClip(musicClip);
				States.setState(statePicked);
			
			}
		}));
		//EXIT BUTTON
		uiManager.addObject(new UIImageButton(161, 395, 295, 97, Assets.btn_exit, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				System.exit(0);
			}
		}));
		//PICKING MAPS
		//FIRST MAP
		uiManager.addObject(new UIMap(150, 600, 1600/6, 928/6, Assets.lava, new ClickListener(){

			@Override
			public void onClick() {
				statePicked = handler.getGame().getGameState();
				highlightMap = new Rectangle(142, 595, 280, 165);

			}
			
			
			
		}));
		//ISLAND MAP
		uiManager.addObject(new UIMap(150 * 3, 600, 1600/6, 928/6, Assets.island, new ClickListener(){

			@Override
			public void onClick() {
				statePicked = handler.getGame().island;
				highlightMap = new Rectangle((150 * 3) - 8, 595, 280, 165);
			}
			
			
			
		}));
		//HEDGE MAP
		uiManager.addObject(new UIMap(150 * 5, 600, 1600/6, 928/6, Assets.hedge, new ClickListener(){

			@Override
			public void onClick() {
				statePicked = handler.getGame().hedge;
				highlightMap = new Rectangle((150 * 5) - 8, 595, 280, 165);

				
			}
			
		}));
		
		//PLAY MUSIC
		musicManager.playClip(musicClip);
	}

	@Override
	public void tick() {
		uiManager.tick();

	}

	@Override
	public void render(Graphics g) {
		fade(g, Assets.menu1);
		uiManager.render(g);
		g.setColor(Color.BLUE);
		g.drawRect(highlightMap.x, highlightMap.y, highlightMap.width, highlightMap.height);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//REQUIRED STATE METHODS 
	//TODO MAKE IT EXTEND A MENU MANAGER

	@Override
	public void collisionX() {
		// TODO Auto-generated method stub

	}

	@Override
	public void collisionY() {
		// TODO Auto-generated method stub

	}

	@Override
	public float getAlpha() {
		return alpha;

	}

	@Override
	public Player1 getPlayer1() {
		// TODO Auto-generated method stub
		return null;
	}

	public Player2 getPlayer2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BulletManager getBulletManager() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public TeleportManager getTeleportManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemManager getItemManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Rectangle> getCollision() {
		// TODO Auto-generated method stub
		return null;
	}

}
