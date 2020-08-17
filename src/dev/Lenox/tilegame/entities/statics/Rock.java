package dev.Lenox.tilegame.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.gfx.Assets;

public class Rock extends StaticEntity {
	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, 32, 64);
		bounds = new Rectangle((int)x, (int)y + 5, width, height - 10);

	}

	@Override
	public void tick() {
	
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int) x, (int) y, width, height, null);
		//g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
	}

	@Override
	public void die() {
		
	}

}
