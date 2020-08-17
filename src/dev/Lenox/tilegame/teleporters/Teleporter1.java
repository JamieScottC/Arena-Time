package dev.Lenox.tilegame.teleporters;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.gfx.Assets;

public class Teleporter1 extends Teleport{

	public Teleporter1(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.teleporter, (int) x, (int) y, width, height, null);
		
	}

	

}
