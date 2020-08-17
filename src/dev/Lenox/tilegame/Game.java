
package dev.Lenox.tilegame;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;

import dev.Lenox.tilegame.display.Display;
import dev.Lenox.tilegame.gfx.Assets;
import dev.Lenox.tilegame.input.KeyManager;
import dev.Lenox.tilegame.input.MouseManager;
import dev.Lenox.tilegame.states.GameState;
import dev.Lenox.tilegame.states.Hedge;
import dev.Lenox.tilegame.states.Island;
import dev.Lenox.tilegame.states.MenuState;
import dev.Lenox.tilegame.states.States;

public class Game implements Runnable {
	//DISPLAY
	private Display display;
	private int width, height;
	public String title;
	//THREADS
	private boolean running = false;
	private Thread thread;
	//BUFFERING AND HRAPHICS
	private BufferStrategy bs;
	private Graphics g;

	// Statess
	public States gameState, island, hedge;
	private States menuState;

	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	//UI

	// Handler
	private Handler handler;
	//TIMER
	Timer myTimer = new Timer();
	public static int seconds = 0;
	int count = 0;
	//PAUSE
	private boolean paused = false;
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	

	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		Assets.init();

		handler = new Handler(this);
		
		gameState = new GameState(handler, 9, 79, 1502, 79);
		island = new Island(handler, 20, 190, 1450, 190);
		hedge = new Hedge(handler, 9, 79, 1502, 79);
		menuState = new MenuState(handler);
		States.setState(menuState);
	
	}



	private void tick() {
		keyManager.tick();
		setPaused();
		if(!paused){
			States.getState().tick();
			if(count == 0){
			if(States.getState() != menuState){
				myTimer.scheduleAtFixedRate(new TimerTask() {
					
					@Override
					public void run() {
						seconds++;
					}
				}, 1000, 1000);
			count = 1;	
			}
			}
			//System.out.println(seconds);
		}
		render();
	}

	
	public void paused(Graphics g){
		g.drawString("Paused", 750, 200);
	}

	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, width, height);
		// Draw Here!
		//Rendering
		//State render
		Toolkit.getDefaultToolkit().sync();
			States.getState().render(g);
			//UI
			if(States.getState() != menuState){
				if(paused)
				paused(g);
			}
		// End Drawing!
			
		bs.show();
		g.dispose();
	}


	
	public void run() {

		init();

		int fps = 60;
		double timePerTick = 100000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		//int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();

				//ticks++;
				delta--;
			}

			if (timer >= 1000000000) {

				//ticks = 0;
				timer = 0;
			}
		}

		stop();

	}
	
	public States getGameState(){
		return gameState;
	}
	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	//TIMER


	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused() {
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_U))
			 paused = !paused;
	}
}
