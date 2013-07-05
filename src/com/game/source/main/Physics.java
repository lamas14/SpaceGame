package com.game.source.main;

import java.util.LinkedList;

import com.game.source.main.classes.EntityA;
import com.game.source.main.classes.EntityB;

public class Physics {
	
	//recently added
	private static int index = 0;
	
	public static boolean Collision(EntityA enta, LinkedList<EntityB> entb){
		
		for(int i = 0; i < entb.size(); i++){
			if(enta.getBounds().intersects(entb.get(i).getBounds())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean Collision(EntityB entb, LinkedList<EntityA> enta){	
		for(int i = 0; i < enta.size(); i++){
			if(entb.getBounds().intersects(enta.get(i).getBounds())){
				
				//recently added
				index = i;
				return true;
			}
		}
		return false;
	}
	
	
	//recently added getter
	public static int getIndex() {
		return index;
	}
	
}
