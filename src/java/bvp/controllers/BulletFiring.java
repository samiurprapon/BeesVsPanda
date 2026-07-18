package bvp.controllers;

import bvp.views.GameBoard;
import bvp.models.Bee;
import bvp.models.Bullet;

import java.awt.*;

public class BulletFiring extends Thread {

	GameBoard gameBoard;
	Bee[] bee;
	private final Bullet bullet;
	private final int bulletDelay;


	public BulletFiring(GameBoard gb, Bee[] bee, Bullet bullet, int bulletDelay){
		this.gameBoard = gb;
		this.bee = bee;
		this.bullet = bullet;
		this.bulletDelay = bulletDelay;
	}

	@Override
	public void run(){

		while(bullet.getX() < 975){ //Bullet hitting after crossing screen bug fixed

			bullet.setX(bullet.getX() + 1);

			checkCollision();

			try{
				Thread.sleep(bulletDelay);
			} catch(InterruptedException  ex){
				ex.printStackTrace();
			}
			gameBoard.repaint();
		}
		bullet.setX(-50); //Bullet hitting after crossing screen bug fixed
	}


	public void checkCollision(){

		Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(),20,13);

		boolean isHit = false;

		for (Bee value : bee) {

			Rectangle beeRect = new Rectangle(value.getX(), value.getY(), 80, 60);
			if (bulletRect.intersects(beeRect)) {
				GameBoard.score++;
				GameBoard.score = 9 + GameBoard.score; //for getting 10 points after shooting a bee

				isHit = true;

				value.setImagePath("/drawables/blasts/ic_blast_1.png"); //When bullet hits a bee, it shows a blast
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					//exception handling
				}

				value.setImagePath("/drawables/bees/ic_bee_common.png"); //After Blast setting the image again

				value.setX(1100);    //Bee outside of Screen Bug fixer
				bullet.setX(2000);    //Bullet outside of Screen Bug fixer

				gameBoard.incKill(); //count common-bee kills toward summoning the boss
				if (gameBoard.killsReached() && !gameBoard.isBossSpawned()) {
					gameBoard.summonBoss();
				}

				break;
			}

		}

		//boss collision: each hit removes 1 HP, defeating the boss at 0
		Bee boss = gameBoard.getBoss();
		if (boss != null && boss.isAlive()) {
			Rectangle bossRect = new Rectangle(boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight());
			if (bulletRect.intersects(bossRect)) {
				boss.setHealth(boss.getHealth() - 1);
				isHit = true;
				bullet.setX(2000);
				if (boss.getHealth() <= 0) {
					gameBoard.onBossDefeated();
				}
			}
		}

		if(isHit) {
			GameSound.bulletHitSound();
		}
	}
}
