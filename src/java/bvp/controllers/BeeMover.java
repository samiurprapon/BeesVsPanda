package bvp.controllers;

import bvp.views.GameBoard;
import bvp.models.Bee;
import bvp.models.Shooter;

import java.awt.Rectangle;

public class BeeMover extends Thread {

	private final Shooter shooter;
	private final Bee bee;
	private final GameBoard gameBoard;
	private final int beeDelay;

	public BeeMover(Bee bee, GameBoard gameBoard, Shooter shooter, int beeDelay){
		this.shooter = shooter;
		this.gameBoard = gameBoard;
		this.bee = bee;
		this.beeDelay = beeDelay;
	}

	@Override
	public void run(){

		while(gameBoard.isLevelActive() && bee.getX() > - 100){
			bee.setX(bee.getX() - 1);

			try {
				Thread.sleep(beeDelay);
			} catch (Exception e) {
				e.printStackTrace();
			}

			gameBoard.repaint();

			checkCollision();
		}
		bee.setX(1100);	//setting getX again to 1100
		//only keep cycling while the level is still active (boss phase stops the swarm)
		if (gameBoard.isLevelActive()) {
			run();	//starting from the beginning
		}
	}

	public void checkCollision(){
		Rectangle shooterRect = new Rectangle(shooter.getX(), shooter.getY(), 105, 120);
		Rectangle beeRect = new Rectangle(bee.getX(),bee.getY(),70,60); //105 because it'll be nearer when colliding

		boolean isHit = false;

		if(shooterRect.intersects(beeRect)){
			isHit = true;
			
			gameBoard.setLife(gameBoard.getLife()-1); //Life of Shooter Decreasing

			if (gameBoard.getLife() == 0) {
				shooter.setX(1500);
			}

			bee.setImagePath("/drawables/blasts/ic_blast_1.png"); //When bullet hits a bee, it shows a blast

			try {
				Thread.sleep(100);
			} catch(Exception e){
				e.printStackTrace();
			}

			bee.setImagePath("/drawables/bees/ic_bee_common.png"); //After Blast setting the image again

			bee.setX(1100); //Setting it again for coming continuously

		}

		if(isHit) {
			GameSound.Shooter_Bee_Collide_Sound();
		}
	}
}



