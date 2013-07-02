package com.game.source.main;


import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.source.main.classes.EntityA;
import com.game.source.main.libs.Animation;

public class Bullet extends GameObject implements EntityA{
	
	private Textures tex;
	private Game game;
	Animation anim;
	
	
	public Bullet(double x, double y, Textures tex, Game game){
		super(x, y);
		this.game = game;
		this.tex = tex;
		
		anim = new Animation(5, tex.missile[0], tex.missile[1], tex.missile[2]);
	}
	
	public void tick(){
		y-=10;
		
		anim.runAnimation();
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
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
