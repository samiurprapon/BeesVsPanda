package bvp.models;

import javax.swing.*;
import java.awt.*;

public class Bee {

	private int x,y;
	private String imagePath;
	private boolean isAlive;
	private int health = 1;
	private boolean boss = false;
	private int width = -1;	 // -1 => draw at native image size (common bees)
	private int height = -1;
	private String name;

	public Bee(int x, int y, String imagePath, boolean isAlive) {
		this.x = x;
		this.y = y;
		this.imagePath = imagePath;
		this.isAlive = isAlive;
	}

	//Full constructor used for boss bees (explicit size + health + name).
	public Bee(int x, int y, String imagePath, boolean isAlive,
			   int health, int width, int height, String name, boolean boss) {
		this(x, y, imagePath, isAlive);
		this.health = health;
		this.width = width;
		this.height = height;
		this.name = name;
		this.boss = boss;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBoss() {
		return boss;
	}

	public void setBoss(boolean boss) {
		this.boss = boss;
	}

	public void draw(Graphics graphics){
		ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
		if (width > 0 && height > 0) {
			graphics.drawImage(icon.getImage(), x, y, width, height, null);
		} else {
			graphics.drawImage(icon.getImage(), x, y, null);
		}
	}


}
