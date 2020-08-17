package dev.Lenox.tilegame.items;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.entities.Player1;
import dev.Lenox.tilegame.entities.Player2;
import dev.Lenox.tilegame.gfx.Assets;

public class Health extends Item{
	private int time;
	private boolean hud1 = false;
	public Health(Handler handler, float x, float y, int time) {
		super(handler, x, y, 30, 30);
		// TODO Auto-generated constructor stub
		bounds = new Rectangle((int)x, (int)y, width, height);
		this.time = time;
	}

	@Override
	public void tick() {
		if(handler.getGame().seconds >= time)
		active = true;
		if(!active)
			return;
		player1PickUp();
		player2PickUp();
		ability();
		
	}

	@Override
	public void render(Graphics g) {
		if(!active)
			return;
		
		g.drawImage(Assets.heart,(int) x, (int)y, width, height, null);
		
	}

	@Override
	public void player1PickUp() {
		
		if(handler.getState().getPlayer1().getBounds().intersects(bounds)){
			pickedUp1 = true;
			
		}
		
	}

	@Override
	public void player2PickUp() {
		if(handler.getState().getPlayer2().getBounds().intersects(bounds)){
			pickedUp2 = true;

		}
		
	}
	public void ability(){
		if(pickedUp1){
			Player1.phealth1 += 2;
		}
		if(pickedUp2){
			Player2.phealth2 +=2;

		}
	}






	

}
