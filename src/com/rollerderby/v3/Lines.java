package com.rollerderby.v3;

public class Lines extends Sprite {
	
	public Lines(int x, int y) {
		super(x, y);
		loadImage("lines.png");
		getImageDimensions();
	}
	
	public void move() {
		int move = 20;
		boolean down = true;
		
		if (down) {
			
			if (y <= 0) {
				
				for (int i = 0; i < 4; i++) {
					y += move;
				}
				
				down = false;
			}
			
			else {
				
				for (int i = 0; i < 4; i++) {
					y -= move;
				}
			}
		}
		
		else {
			
			if (y >= 100) {
				
				for (int i = 0; i < 4; i++) {
					y -= move;
				}
				
				down = true;
			}
			
			else {
				
				for (int i = 0; i < 4; i++) {
					y += move;
				}
			}
		}
	}
}