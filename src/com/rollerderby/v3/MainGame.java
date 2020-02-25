package com.rollerderby.v3;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class InitGame extends JPanel implements Runnable, ActionListener {
	
	private static final long serialVersionUID = 1L;
	private boolean running;
	private Thread thread;
	private Timer timer;
	public Lines lines;
	public Car car;
	public ArrayList<Tree> trees = new ArrayList<>();
	public int delay = 100, width = MainGame.bWidth, height = MainGame.bHeight;
	public static int score;
	
	public InitGame() {
		thread = new Thread(this);
		timer = new Timer(delay, this);
		lines = new Lines(0, 0);
		car = new Car(360, 450);
		addKeyListener(new carControl());
		setFocusable(true);
		setSize(width, height);
		setBackground(new Color(27, 100, 32));
		setVisible(true);
		start();
	}
	
	private synchronized void start() {
		running = true;
		thread.start();
		timer.start();
	}
	
	public synchronized void stop() {
		running = false;
		
		try {
			thread.join();
		} catch(InterruptedException e) {}
		
		timer.stop();
	}
	
	public static int getScore() {
		return score;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (running)
			drawObjects(g);
		
		else
			drawGameOver(g);
	}
	
	public void drawObjects(Graphics g) {
		g.fillRect(250, 0, 300, 600);
		
		if (lines.isVisible())
			g.drawImage(lines.getImage(), lines.getX(), lines.getY(), this);
		
		if (car.isVisible())
			g.drawImage(car.getImage(), car.getX(), car.getY(), this);
		
		for (Tree t : trees) {
			
			if (t.isVisible())
				g.drawImage(t.getImage(), t.getX(), t.getY(), this);
		}
		
		String msg = "Score: " + score;
		g.setColor(Color.white);
		g.drawString(msg, 10, 20);
	}
	
	public void drawGameOver(Graphics g) {
		lines.setVisible(false);
		car.setVisible(false);
		
		String msg = "Game Over";
		String scr = "Score: " + Integer.toString(score);
		Font f = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(f);
		g.setColor(Color.white);
		g.setFont(f);
		g.drawString(msg, (width - fm.stringWidth(msg))/2, height/2);
		g.drawString(scr, (width - fm.stringWidth(scr))/2, height/2 + 30);
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0/60.0;
		double delta = 0;
		requestFocus();
		long timer = System.currentTimeMillis();
		
		while (running) {
			long now = System.nanoTime();
			delta = delta + ((now - lastTime)/ns);
			lastTime = now;
			
			while (delta >= 1) {
				delta--;
			}
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		running();
		checkTrees();
		updateGame();
		//collisions();
		repaint();
	}
	
	public void running() {
		if (!running)
			stop();
		score++;
	}
	
	public void initTrees() {
		int random = (int)(Math.random() * 250 + 1);
		
		if (random >= 151)
			random += 450;
		
		trees.add(new Tree(random, -100));
	}
	
	public void checkTrees() {
		for (int i = 0; i < trees.size(); i++) {
			
			if (trees.get(i).getY() > 550) {
				trees.remove(i);
			}
		}
	}
	
	public void updateGame() {
		if (lines.isVisible()) {
			lines.move();
		}
		
		initTrees();
		for (int treeObj = 0; treeObj < trees.size(); treeObj++) {
			Tree t = trees.get(treeObj);
			
			if (t.isVisible()) {
				t.move();
			}
			
			else {
				trees.remove(treeObj);
			}
		}
	}
	
	//public void collisions() {}
	
	private class carControl extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			car.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			car.keyReleased(e);
		}
	}
}

public class MainGame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static InitGame ig;
	public static int bWidth = 800, bHeight = 600;
	
	public MainGame() {
		ig = new InitGame();
		add(ig);
		setIconImage(StartMenu.img.getImage());
		setSize(bWidth, bHeight);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}