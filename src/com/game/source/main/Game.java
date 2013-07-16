package com.game.source.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.game.source.main.classes.EntityA;
import com.game.source.main.classes.EntityB;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Space Game";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;

	private boolean is_shooting = false;

	private int enemy_count = 5;
	private int enemy_killed = 0;

	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;

	public enum STATE {
		MENU, GAME
	};

	private STATE state = STATE.MENU;

	// RECENTLY ADDED
	private static int level = 1;

	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {

			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		addKeyListener(new KeyInput(this));

		MouseInput mouse = new MouseInput();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		// tex has to come before player and controller
		// because player and controller use graphics from texture
		tex = new Textures(this);

		// RECENTLY EDITED
		p = new Player(WIDTH * SCALE >> 1, HEIGHT * SCALE - 64, tex, this);

		c = new Controller(tex, this);

		menu = new Menu();

		ea = c.getEntityA();
		eb = c.getEntityB();

		c.createEnemy(enemy_count);
	}

	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			// this is the game loop
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		if (state == STATE.GAME) {
			p.tick();
			c.tick();
		}

		if (enemy_killed >= enemy_count) {
			level++;
			enemy_count += 2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
		
		if(state == STATE.MENU){
			
			if(MouseInput.getButton()==1 && 
					MouseInput.getX() > menu.playButton.x && 
					MouseInput.getX() < menu.playButton.x + menu.playButton.width &&
					MouseInput.getY() > menu.playButton.y && 
					MouseInput.getY() < menu.playButton.y + menu.playButton.height){
				
				p.setHealth(100);
				state = STATE.GAME;
			}
			
			if(MouseInput.getButton()==1 && 
					MouseInput.getX() > menu.helpButton.x && 
					MouseInput.getX() < menu.helpButton.x + menu.helpButton.width &&
					MouseInput.getY() > menu.helpButton.y && 
					MouseInput.getY() < menu.helpButton.y + menu.helpButton.height){
				
			}
			
			if(MouseInput.getButton()==1 && 
					MouseInput.getX() > menu.exitButton.x && 
					MouseInput.getX() < menu.exitButton.x + menu.exitButton.width &&
					MouseInput.getY() > menu.exitButton.y && 
					MouseInput.getY() < menu.exitButton.y + menu.exitButton.height){
				System.exit(1);
			}
		}

	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// /////////////////////////////////////

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		if (state == STATE.GAME) {
			g.drawImage(background, 0, 0, null);

			// RECENTLY ADDED
			Font font = new Font("Impact", Font.BOLD, 16);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString("Level: " + level, 10, 20);
			g.drawString("Health: " + p.getHealth() + "%", WIDTH * SCALE - 96,
					20);

			p.render(g);
			c.render(g);
		} else if (state == STATE.MENU) {
			menu.render(g);
		}

		// RECENTLY ADDED
		if (p.getHealth() <= 0) {
			JOptionPane.showMessageDialog(null , "GAME OVER!");
			p.setHealth(100);
			state = STATE.MENU;
		}
		/*
		 * if (p.getHealth() <= 0) { g.setColor(Color.RED); g.setFont(new
		 * Font("Impact", Font.BOLD, 42)); FontMetrics fm = g.getFontMetrics();
		 * 
		 * g.drawString("GAME OVER!", (WIDTH * SCALE -
		 * fm.stringWidth("GAME OVER!")) >> 1, 140); }
		 */

		// ////////////////////////////////////
		g.dispose();
		bs.show();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (state == STATE.GAME) {
			if (key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
			} else if (key == KeyEvent.VK_UP) {
				p.setVelY(-5);
			} else if (key == KeyEvent.VK_SPACE && !is_shooting) {
				is_shooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;
		}
	}
	
	
	

	public static void main(String args[]) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	/***********************************
	 * Getters and Setters
	 */
	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	
	public void setState(STATE state){
		this.state = state;
	}

}
