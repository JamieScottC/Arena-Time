package dev.Lenox.tilegame.teleporters;

import java.awt.Graphics; 
import java.util.ArrayList;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.states.States;

public class TeleportManager {
	
	private Handler handler;
	private ArrayList<Teleport> teleporters;
	private Teleport t;
	
	public TeleportManager(Handler handler){
		this.handler = handler;
		teleporters = new ArrayList<Teleport>();
		
	}
	
	public void tick(){
		for(int i = 0;i < teleporters.size();i++){
			 t = teleporters.get(i);
			t.tick();
			
			}
		
		}
	public void remove(Teleport e){
		teleporters.remove(e);
	}
	
	public Teleport getE(){
		return t;
		
	}
	public void render(Graphics g){
		for(Teleport t : teleporters){
			t.render(g);
		}
		
	}

	public void addTeleport(Teleport e){
		teleporters.add(e);
	}
	public void clearTeleporter(){
		teleporters.clear();
	}
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	
	public ArrayList<Teleport> getEntities() {
		return teleporters;
	}

	public void setEntities(ArrayList<Teleport> teleporters) {
		this.teleporters = teleporters;
	}

}