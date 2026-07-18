package bvp.controllers;

import bvp.models.Bee;
import bvp.models.Shooter;
import bvp.views.GameBoard;

import java.awt.Rectangle;

/**
 * Drives the level boss: enters from the right edge to a hover column, then
 * slowly oscillates vertically until defeated (or the panda runs out of lives).
 * Touching the panda costs a life, with a short damage cooldown (i-frames).
 */
public class BossMover extends Thread {

	private final Bee boss;
	private final GameBoard gameBoard;
	private final Shooter shooter;
	private final int hoverX = 760;
	private final int baseY;
	private int tick = 0;
	private int lastDamageTick = -100;

	public BossMover(Bee boss, GameBoard gameBoard, Shooter shooter){
		this.boss = boss;
		this.gameBoard = gameBoard;
		this.shooter = shooter;
		this.baseY = boss.getY();
	}

	@Override
	public void run(){
		//entry: glide in from off-screen right to the hover column
		while(boss.getX() > hoverX && !gameBoard.isLevelComplete() && gameBoard.getLife() > 0){
			boss.setX(boss.getX() - 2);
			checkCollision();
			gameBoard.repaint();
			sleep(16);
		}

		//hover: oscillate vertically until the boss is defeated
		while(!gameBoard.isLevelComplete() && gameBoard.getLife() > 0){
			tick++;
			boss.setY(baseY + (int)(Math.sin(tick * 0.05) * 90));
			checkCollision();
			gameBoard.repaint();
			sleep(16);
		}
	}

	private void checkCollision(){
		if(gameBoard.getLife() <= 0){
			return;
		}
		Rectangle shooterRect = new Rectangle(shooter.getX(), shooter.getY(), 105, 120);
		Rectangle bossRect = new Rectangle(boss.getX(), boss.getY(), boss.getWidth(), boss.getHeight());
		if(bossRect.intersects(shooterRect) && tick - lastDamageTick > 30){
			lastDamageTick = tick;
			gameBoard.setLife(gameBoard.getLife() - 1);
			GameSound.Shooter_Bee_Collide_Sound();
			if(gameBoard.getLife() == 0){
				shooter.setX(1500);
			}
		}
	}

	private void sleep(int ms){
		try {
			Thread.sleep(ms);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
