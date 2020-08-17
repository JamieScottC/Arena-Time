package dev.Lenox.tilegame.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.gfx.Assets;

public class FaceRock extends StaticEntity {
	public FaceRock(Handler handler, float x, float y) {
		super(handler, x, y, 64, 32 * 3);
		bounds = new Rectangle((int)x, (int)y + 5, width, height - 10);

	}

	@Override
	public void tick() {
	
		
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.faceRock, (int) x, (int) y, width, height, null);
		
	}

	@Override
	public void die() {
		
	}

}
