package dev.Lenox.tilegame.items;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.bullets.Bullet1;
import dev.Lenox.tilegame.bullets.Bullet2;
import dev.Lenox.tilegame.entities.Player1;
import dev.Lenox.tilegame.entities.Player2;
import dev.Lenox.tilegame.gfx.Assets;

public class Shotgun extends Item{
	private boolean hud1 = false;
	public Shotgun(Handler handler, float x, float y) {
		super(handler, x, y, 30, 30);
		// TODO Auto-generated constructor stub
		bounds = new Rectangle((int)x, (int)y, width, height);
		
	}

	@Override
	public void tick() {
		if(handler.getGame().seconds >= 40)
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
			if(handler.getState().getPlayer1().getAmmoCapacity1() < 16){
			handler.getState().getPlayer1().setAmmoCapacity1(12);
			handler.getState().getPlayer1().setBullet1Count(12);
			}
			
			Player1.shotGun = true;
		}
		if(pickedUp2){
			if(handler.getState().getPlayer2().getAmmoCapacity2() < 16){
			handler.getState().getPlayer2().setAmmoCapacity2(12);
			handler.getState().getPlayer2().setBullet2Count(12);
			}
			Player2.shotGun = true;


		}
	}






	

}
