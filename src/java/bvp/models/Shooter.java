package bvp.models;

import javax.swing.*;
import java.awt.*;

public class Shooter {
	private int x;
	private int y;
	private String imagePath;

	public Shooter(int x,int y,String imagePath){
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void draw(Graphics graphics){
		ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
		graphics.drawImage(icon.getImage(), x, y, null);
	}
}
