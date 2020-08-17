package dev.Lenox.tilegame;

import dev.Lenox.tilegame.input.KeyManager;
import dev.Lenox.tilegame.input.MouseManager;
import dev.Lenox.tilegame.states.States;

public class Handler {
	private Game game;
	public Handler(Game game){
		this.game = game;
	}

	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	public int getWidth(){
		return game.getWidth();
	}
	public int getHeight(){
		return game.getHeight();
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	
	public States getState(){
		return States.getState();
	}
	public States getGameState(){
		return game.getGameState();
	}
	

	
}
