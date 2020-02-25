package com.rollerderby.v3;

public class Tree extends Sprite {
	
	public Tree(int x, int y) {
		super(x, y);
		loadImage("tree.png");
		getImageDimensions();
	}
	
	public void move() {
		int r1 = (int)(Math.random() * 250 + 1);
		
		if (r1 >= 151)
			r1 += 450;
		
		int r2 = (int)(Math.random() * 500 + 1);
		
		if (y < bHeight)
			y += accel;
		
		while (y >= bHeight) {
			y = -r2;
			y += accel;
			x = r1;
		}
	}
}