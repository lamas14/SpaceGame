package com.game.source.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Menu {
	
	public Rectangle playButton = new Rectangle((Game.WIDTH >>1) + 120, 150, 100, 50);
	public Rectangle helpButton = new Rectangle((Game.WIDTH >>1) + 120, 250, 100, 50);
	public Rectangle exitButton = new Rectangle((Game.WIDTH >>1) + 120, 350, 100, 50);
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Impact", Font.BOLD, 64);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("SPACE GAME", Game.WIDTH >>1, 95);
		
		Font fnt1 = new Font("Impact", Font.BOLD, 31);
		g.setFont(fnt1);
		
		g.setColor(Color.GRAY);
		g.fillRect((Game.WIDTH >>1) + 120, 150, 100, 50);
		g.setColor(Color.WHITE);
		g.drawString("Play", playButton.x + 19, playButton.y + 35);
		g2d.draw(playButton);
		
		g.setColor(Color.GRAY);
		g.fillRect((Game.WIDTH >>1) + 120, 250, 100, 50);
		g.setColor(Color.WHITE);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
		g2d.draw(helpButton);
		
		g.setColor(Color.GRAY);
		g.fillRect((Game.WIDTH >>1) + 120, 350, 100, 50);
		g.setColor(Color.WHITE);
		g.drawString("Exit", exitButton.x + 24, exitButton.y + 35);
		g2d.draw(exitButton);
		
	}
	
}
