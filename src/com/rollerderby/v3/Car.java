package com.rollerderby.v3;

import java.awt.event.*;

public class Car extends Sprite {
	
	public int xPos;
	
	public Car(int x, int y) {
		super(x, y);
		loadImage("car.png");
		getImageDimensions();
		//left lane: 250, middle lane: 350, right lane: 450
	}
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(), left = KeyEvent.VK_LEFT, right = KeyEvent.VK_RIGHT;
		
		if ((code == left && x == 360) || (code == left && x == 460))
			x -= 100;
		
		if ((code == right && x == 260) || (code == right && x == 360))
			x += 100;
	}
	
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode(), left = KeyEvent.VK_LEFT, right = KeyEvent.VK_RIGHT;
		
		if (code == left)
			xPos = 0;
		
		if (code == right)
			xPos = 0;
	}
}