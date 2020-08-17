package dev.Lenox.tilegame.states;

import java.awt.Graphics;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.gfx.Assets;

public class GameOver2 {
	private Handler handler;
	public GameOver2(Handler handler){
		this.handler = handler;
	}
	public void tick(){
		
	}
public void render(Graphics g){
	g.drawImage(Assets.player2Victor, 0, 0, null);
}
}
