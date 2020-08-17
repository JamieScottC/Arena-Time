package dev.Lenox.tilegame.entities.statics;

import dev.Lenox.tilegame.Handler;
import dev.Lenox.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {
	
	public StaticEntity(Handler handler, float x, float y, int width, int height){
		super(handler, x, y, width, height);
	}

}