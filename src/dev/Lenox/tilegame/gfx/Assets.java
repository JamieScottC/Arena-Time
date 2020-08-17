package dev.Lenox.tilegame.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Assets {

	private static final int width = 32, height = 32;
	private static final int widthp = 32, heightp = 32;
	// UI
	// Player
	public static BufferedImage player_down, player_up, player_right, player_left, player_still, player_interact;
	public static BufferedImage player_down2, player_up2, player_right2, player_left2, player_still2;

	// ITEMS
	public static BufferedImage boots, orb, ammoPwrUp, teleporter, heart;
	// bgs
	public static BufferedImage lava, island, hedge;
	public static BufferedImage menu1, menu2, menu3, player1Victor, player2Victor;

	// Statics
	public static BufferedImage pole, rock, faceRock, bush;
	public static BufferedImage[] btn_start;
	public static BufferedImage[] btn_exit;



	public static void init() {
		SpriteSheet player1 = new SpriteSheet(ImageLoader.loadImage("/textures/Grue.png"));
		// Menu
		btn_start = new BufferedImage[2];
		btn_start[0] = ImageLoader.loadImage("/textures/Start1.png");
		btn_start[1] = ImageLoader.loadImage("/textures/start2.png");
		btn_exit = new BufferedImage[2];
		btn_exit[0] = ImageLoader.loadImage("/textures/exit1.png");
		btn_exit[1] = ImageLoader.loadImage("/textures/exit2.png");
	

		menu1 = ImageLoader.loadImage("/textures/menu.png");
		menu2 = ImageLoader.loadImage("/textures/menu2.png");
		menu3 = ImageLoader.loadImage("/textures/Menu3.png");
		// player anims

		// DOWN
		player_down = player1.crop(32, 32, widthp, heightp);
		// UP
		player_up = player1.crop(32, 0, widthp, heightp);

		// RIGHT
		player_right = player1.crop(0, 0, widthp, heightp);

		// LEFT
		player_left = player1.crop(0, 32, widthp, heightp);

		// player2
		// DOWN
		player_down2 = player1.crop(32, 32, widthp, heightp);
		// UP
		player_up2 = player1.crop(32, 0, widthp, heightp);

		// RIGHT
		player_right2 = player1.crop(0, 0, widthp, heightp);

		// LEFT
		player_left2 = player1.crop(0, 32, widthp, heightp);

		// INTERACT
		// player_interact = play.crop(0, heightp * 12, widthp, heightp);

		// static entities
		pole = ImageLoader.loadImage("/textures/pole.png");
		rock = ImageLoader.loadImage("/textures/Rock.png");
		faceRock = ImageLoader.loadImage("/textures/FaceRock.png");

		// backgrounds
		lava = ImageLoader.loadImage("/textures/lava.png");
		island = ImageLoader.loadImage("/textures/Island.png");
		hedge = ImageLoader.loadImage("/textures/Hedge.png");

		// ITEMS
		boots = ImageLoader.loadImage("/textures/Boots.png");
		orb = ImageLoader.loadImage("/textures/blue orb.png");
		ammoPwrUp = ImageLoader.loadImage("/textures/ammo.png");
		teleporter = ImageLoader.loadImage("/textures/portal.png");
		heart = ImageLoader.loadImage("/textures/heart.png");
		player1Victor = ImageLoader.loadImage("/textures/player1victor.png");
		player2Victor = ImageLoader.loadImage("/textures/player2victor.png");
	}

}