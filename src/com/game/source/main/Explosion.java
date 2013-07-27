package com.game.source.main;

import java.awt.Graphics;

import com.game.source.main.libs.Animation;

public class Explosion {
	//private Textures tex;
	
	private double x;
	private double y;
	Animation anim;
	
	public Explosion(Textures tex){
		//this.tex = tex;
		anim = new Animation(5, tex.explosion[0], tex.explosion[1], tex.explosion[2]);
	}
	
	public void tick(){
		anim.runAnimation();
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
}
