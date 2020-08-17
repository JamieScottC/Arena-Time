package dev.Lenox.tilegame.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class UIMap extends UIObject {

	private Image image;
	private ClickListener clicker;

	public UIMap(float x, float y, int width, int height, BufferedImage image, ClickListener clicker) {
		super(x, y, width, height, handler);
		this.image = image;
		this.clicker = clicker;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		
			g.drawImage(image, (int) x, (int) y, width, height, null);
		
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}