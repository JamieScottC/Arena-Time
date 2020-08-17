package dev.Lenox.tilegame.entities;

import java.awt.Graphics; 
import java.util.ArrayList;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.states.States;

public class EntityManager {
	
	private Handler handler;
	private ArrayList<Entity> entities;
	private Entity e;
	
	public EntityManager(Handler handler){
		this.handler = handler;
		entities = new ArrayList<Entity>();
		
	}
	
	public void tick(){
		for(int i = 0;i < entities.size();i++){
			 e = entities.get(i);
			e.tick();
			
			}
		
		}
	public void remove(Entity e){
		entities.remove(e);
	}
	
	public Entity getE(){
		return e;
		
	}
	public void render(Graphics g){
		for(Entity e : entities){
			e.render(g);
		}
		
	}

	public void addEntity(Entity e){
		entities.add(e);
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

}