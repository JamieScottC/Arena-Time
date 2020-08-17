package dev.Lenox.tilegame.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.gfx.Assets;

public class Pole extends StaticEntity {
	public Pole(Handler handler, float x, float y) {
		super(handler, x, y, 32, 96);
		bounds = new Rectangle((int)x, (int)y, width, height);

	}

	@Override
	public void tick() {
	
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.pole, (int) x, (int) y, width, height, null);
		//g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	@Override
	public void die() {
		
	}

}
