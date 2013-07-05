package com.game.source.main;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] missile = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	public BufferedImage[] explosion = new BufferedImage[3];
	
	private SpriteSheet ss;
	
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures(){
		player[0] = ss.grabImage(1, 1, 32, 32);
		player[1] = ss.grabImage(1, 2, 32, 32);
		player[2] = ss.grabImage(1, 3, 32, 32);
		missile[0] = ss.grabImage(2, 1, 32, 32);
		missile[1] = ss.grabImage(2, 2, 32, 32);
		missile[2] = ss.grabImage(2, 3, 32, 32);
		enemy[0] = ss.grabImage(3, 1, 32, 32);
		enemy[2] = ss.grabImage(3, 2, 32, 32);
		enemy[1] = ss.grabImage(3, 3, 32, 32);
		explosion[0] = ss.grabImage(4, 1, 32, 32);
		explosion[1] = ss.grabImage(4, 2, 32, 32);
		explosion[2] = ss.grabImage(4, 3, 32, 32);		
	}
}
