package dev.Lenox.tilegame.items;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.bullets.Bullet1;
import dev.Lenox.tilegame.bullets.Bullet2;
import dev.Lenox.tilegame.gfx.Assets;

public class Range extends Item{
	private boolean hud1 = false;
	public Range(Handler handler, float x, float y) {
		super(handler, x, y, 30, 30);
		// TODO Auto-generated constructor stub
		bounds = new Rectangle((int)x, (int)y, width, height);
		
	}

	@Override
	public void tick() {
		if(handler.getGame().seconds >= 36)
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
		
		g.drawImage(Assets.ammoPwrUp,(int) x, (int)y, width, height, null);
		
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
			
			Bullet1.range =  20;
		}
		if(pickedUp2){
			
			Bullet2.range =  20;

		}
	}






	

}
