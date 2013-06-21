package com.game.source.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	private LinkedList<Enemy> e = new LinkedList<Enemy>();
	
	Bullet tempBullet;
	Enemy tempEnemy;
	
	Game game;
	Textures tex;
	
	public Controller(Game game, Textures tex){
		this.game = game;
		this.tex = tex;
		
		for(int x = 0; x < (Game.WIDTH *Game.SCALE); x+=64){
			addEnemy(new Enemy(x, 0, tex));
		}
	}
	
	public void tick(){
		for(int i =0; i< b.size(); i++){
			tempBullet = b.get(i);
			
			if(tempBullet.getY() < 0)
				removeBullet(tempBullet);
			
			
			tempBullet.tick();
		}
		for(int i =0; i< e.size(); i++){
			tempEnemy = e.get(i);
			
			tempEnemy.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i =0; i< b.size(); i++){
			tempBullet = b.get(i);
			
			tempBullet.render(g);
		}
		for(int i =0; i< e.size(); i++){
			tempEnemy = e.get(i);
			
			tempEnemy.render(g);
		}
	}
	
	public void addBullet(Bullet block){
		b.add(block);
	}
	
	public void removeBullet(Bullet block){
		b.remove(block);
	}
	
	public void addEnemy(Enemy block){
		e.add(block);
	}
	
	public void removeEnemy(Bullet block){
		e.remove(block);
	}
	
}
