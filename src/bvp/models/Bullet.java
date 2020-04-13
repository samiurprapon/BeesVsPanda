package bvp.models;

import com.sun.istack.internal.NotNull;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Bullet{
	private int x;
	private int y;
	private String imagePath;
	
	public Bullet(int x, int y, String imagePath) {
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

	public void draw(@NotNull Graphics graphics){
		ImageIcon bullet = new ImageIcon(imagePath);
		graphics.drawImage(bullet.getImage(), x, y,null);
	}
	
}

