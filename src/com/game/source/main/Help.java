package com.game.source.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Help {
	
	public Rectangle backButton = new Rectangle((Game.WIDTH >>1) + 120, 400, 100, 50);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Impact", Font.BOLD, 64);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("SPACE GAME", Game.WIDTH >>1, 95);
		
		Font fnt1 = new Font("Impact", Font.CENTER_BASELINE, 22);
		g.setFont(fnt1);
		
		g.drawString("Movements", (Game.WIDTH >>1) + 100, 150);
		g.drawString("Up: 		Up Arrow", (Game.WIDTH >>1) + 100, 200);
		g.drawString("Down: 	Down Arrow", (Game.WIDTH >>1) + 100, 250);
		g.drawString("Left: 	Left Arrow", (Game.WIDTH >>1) + 100, 300);
		g.drawString("Right: 	Right Arrow", (Game.WIDTH >>1) + 100, 350);
		
		
		fnt1 = new Font("Impact", Font.BOLD, 31);
		g.setFont(fnt1);
		
		g.setColor(Color.GRAY);
		g.fillRect((Game.WIDTH >>1) + 120, 400, 100, 50);
		g.setColor(Color.WHITE);
		g.drawString("Back", backButton.x + 15, backButton.y + 37);
		g2d.draw(backButton);
		
	}
	
}
