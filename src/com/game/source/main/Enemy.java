package com.game.source.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.source.main.classes.EntityB;
import com.game.source.main.libs.Animation;

public class Enemy extends GameObject implements EntityB{
	
	Random r = new Random();
	
	//private Textures tex;
	
	Animation anim;
	private Game game;
	private Controller c;
	private int speed = r.nextInt(5) +5;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game){
		super(x, y);
		//this.tex = tex;
		this.c = c;
		this.game = game;
		anim = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[2]);		
	}
	
	public void tick(){
		y+= speed;
		
		if(y>Game.HEIGHT * Game.SCALE){
			speed = r.nextInt(5) + 5;
			y=0;
			x = r.nextInt((Game.WIDTH * Game.SCALE)-32);
		}
		
		if(Physics.Collision(this, game.ea)){
			game.setScore(game.getScore() + 100);
			c.removeEntity(this);
			c.ex.setX(x);
			c.ex.setY(y);
			c.hit = true;
			//recently added
			//removes bullet
			c.removeEntity(game.ea.get(Physics.getIndex()));
			game.setEnemy_killed(game.getEnemy_killed() +1);
		}
		
		anim.runAnimation();
	}
	
	public void render(Graphics g){
		anim.drawAnimation(g, x, y, 0);
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 23, 23);
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
