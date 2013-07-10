package com.game.source.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.source.main.classes.EntityA;
import com.game.source.main.libs.Animation;

public class Player extends GameObject implements EntityA{
	
	private double velX = 0;
	private double velY = 0;
	
	//private Textures tex;
	
	//recently added
	private Game game;
	private static int health = 100;
	
	Animation anim;
	
	public Player(double x, double y, Textures tex, Game game){	
		super(x, y);
		//this.tex = tex;
		this.game = game;
		
		anim = new Animation(5,tex.player[0],tex.player[1],tex.player[2]);
	}
	
	public void tick(){
		x+=velX;
		y+=velY;
		
		if(x<=0)
			x=0;
		if(x>=((Game.WIDTH * Game.SCALE)-32))
			x=(Game.WIDTH * Game.SCALE)-32;
		if(y<=0)
			y=0;
		if(y>=((Game.HEIGHT * Game.SCALE)-32))
			y=(Game.HEIGHT * Game.SCALE)-32;
		
		anim.runAnimation();
		
		
		//RECENTLY ADDED
		if(Physics.Collision(this, game.eb)){
			if(health >=0){
				health-=2;
			}
		}
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
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	//RECENTLY ADDED
	public int getHealth(){
		return health;
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
