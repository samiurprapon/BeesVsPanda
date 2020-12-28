package bvp.controllers;

import bvp.views.GameBoard;
import bvp.models.Bee;
import bvp.models.Shooter;

import java.awt.Rectangle;

public class BeeMover extends Thread {

	private final Shooter shooter;
	private final Bee bee;
	private final GameBoard gameBoard;

	public BeeMover(Bee bee, GameBoard gameBoard, Shooter shooter){
		this.shooter = shooter;
		this.gameBoard = gameBoard;
		this.bee = bee;
	}

	@Override
	public void run(){
		
		while(bee.getX() > - 100){
			bee.setX(bee.getX() - 1);

			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}

			gameBoard.repaint();

			checkCollision();
		}
		bee.setX(1100);	//setting getX again to 1100
		run();	//starting from the beginning
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

			bee.setImagePath("src//resources//images//BeeBlast//ic_blast_1.png"); //When bullet hits a bee, it shows a blast

			try {
				Thread.sleep(100);
			} catch(Exception e){
				e.printStackTrace();
			}

			bee.setImagePath("src//resources//images//Bee//ic_bee_common.png"); //After Blast setting the image again

			bee.setX(1100); //Setting it again for coming continuously

		}

		if(isHit) {
			GameSound.Shooter_Bee_Collide_Sound();
		}
	}
}



