package com.game.source.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.source.main.classes.EntityA;
import com.game.source.main.classes.EntityB;

public class Controller {
	
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	private LinkedList<EntityB> eb = new LinkedList<EntityB>();
	
	Random r = new Random();
	EntityA enta;
	EntityB entb;
	//Game game;
	Textures tex;
	private Game game;
	
	public Controller(Textures tex, Game game){
		//this.game = game;
		this.tex = tex;
		this.game = game;
	}
	
	public void createEnemy(int enemy_count){
		//Creates enemy 	
		for(int i = 0; i < enemy_count; i++){
			addEntity(new Enemy(r.nextInt((Game.WIDTH * Game.SCALE)-32),-32,tex, this, game));
		}
	}
	
	public void clearEnemy(){
		eb.removeAll(eb);
	}
	
	public void tick(){
		//A class
		for(int i = 0; i < ea.size(); i++){
			enta = ea.get(i);
			
			enta.tick();
			
			//RECENTLY ADDED
			if(enta.getY()<0){
				removeEntity(enta);
			}
		}
		
		//B class
		for(int i = 0; i < eb.size(); i++){
			entb = eb.get(i);
			
			entb.tick();
		}
	}
	
	public void render(Graphics g){
		//A Class
		for(int i = 0; i < ea.size(); i++){
			enta = ea.get(i);
			
			enta.render(g);
			
		}
		
		//B Class
		for(int i = 0; i < eb.size(); i++){
			entb = eb.get(i);
			
			entb.render(g);
		}
	}
	
	public void addEntity(EntityA block){
		ea.add(block);
	}
	
	public void removeEntity(EntityA block){
		ea.remove(block);
	}
	
	public void addEntity(EntityB block){
		eb.add(block);
	}
	
	public void removeEntity(EntityB block){
		eb.remove(block);
	}
	
	public LinkedList<EntityA> getEntityA(){
		return ea;
	}
	
	public LinkedList<EntityB> getEntityB(){
		return eb;
	}
	
}
