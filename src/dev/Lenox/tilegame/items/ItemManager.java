package dev.Lenox.tilegame.items;

import java.awt.Graphics; 
import java.util.ArrayList;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.states.States;

public class ItemManager {
	
	private Handler handler;
	private ArrayList<Item> items;
	private Item i;
	
	public ItemManager(Handler handler){
		this.handler = handler;
		items = new ArrayList<Item>();
		
	}
	
	public void tick(){
		for(int c = 0;c < items.size();c++){
			 i = items.get(c);
			i.tick();
			if(i.pickedUp1 || i.pickedUp2)
			items.remove(i);
			}
		
		}
	public void remove(Item i){
		items.remove(i);
	}
	
	public Item getI(){
		return i;
		
	}
	public void render(Graphics g){
		for(Item e : items){
			e.render(g);
		}
		
	}

	public void addItem(Item e){
		items.add(e);
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	
	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	

}