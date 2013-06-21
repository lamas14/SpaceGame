package com.game.source.main;


import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.source.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA{
	
	private Textures tex;
	private Game game;
	
	public Bullet(double x, double y, Textures tex, Game game){
		super(x, y);
		this.game = game;
		this.tex = tex;
	}
	
	public void tick(){
		y-=10;
		
		if(Physics.Collision(this, game.eb)){
			System.out.println("COLLISION DETECTED!");
		}
	}
	
	public void render(Graphics g){
		g.drawImage(tex.missile, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	/************************************
	 * Getters
	 */
	public double getX() {
		return x;
	}
	
	public double getY(){
		return y;
	}
	
}
