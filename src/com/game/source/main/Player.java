package com.game.source.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.source.main.classes.EntityA;

public class Player extends GameObject implements EntityA{
	
	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	
	public Player(double x, double y, Textures tex){	
		super(x, y);
		this.tex = tex;
	}
	
	public void tick(){
		x+=velX;
		y+=velY;
		
		if(x<=0)
			x=0;
		if(x>=640-32)
			x=640-32;
		if(y<=0)
			y=0;
		if(y>=480-32)
			y=480-32;
	}
	
	public void render(Graphics g){
		g.drawImage(tex.player, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	/************************************
	 * Getters
	 */
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	/************************************
	 * Setters
	 */
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public void setVelX(double velX){
		this.velX = velX;
	}
	
	public void setVelY(double velY){
		this.velY = velY;
	}
}
