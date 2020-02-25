package com.rollerderby.v3;

import java.awt.*;
import javax.swing.*;

public class Sprite {
	
	protected int x, y, width, height;
	protected boolean vis;
	protected Image image;
	protected Rectangle rect;
	public static int accel = 30;
	public int bWidth = MainGame.bWidth, bHeight = MainGame.bHeight;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		vis = true;
	}
	
	protected void getImageDimensions() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}
	
	protected void loadImage(String imageName) {
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();
	}
	
	public Image getImage() {
		return image;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean isVisible() {
		return vis;
	}
	
	public void setVisible(Boolean visible) {
		vis = visible;
	}
	
	public Rectangle getBounds(){
		rect = new Rectangle(x, y, width, height);
		return rect;
	}
}