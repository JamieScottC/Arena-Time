package dev.Lenox.tilegame.bullets;

import java.awt.Graphics; 
import java.util.ArrayList;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.states.States;

public class BulletManager {
	
	private Handler handler;
	private ArrayList<Bullet> bullets;
	private Bullet b;
	
	public BulletManager(Handler handler){
		this.handler = handler;
		bullets = new ArrayList<Bullet>();
		
	}
	
	public void tick(){
		for(int i = 0;i < bullets.size();i++){
			 b = bullets.get(i);
			b.tick();
			
			}
		
		
		}
	public void remove(Bullet e){
		bullets.remove(e);
	}
	
	public Bullet getE(){
		return b;
		
	}
	public void render(Graphics g){
		for(Bullet e : bullets){
			e.render(g);
		}
		
	}

	public void addBullet(Bullet e){
		bullets.add(e);
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	
	public ArrayList<Bullet> getEntities() {
		return bullets;
	}

	public void setEntities(ArrayList<Bullet> entities) {
		this.bullets = entities;
	}

	

}