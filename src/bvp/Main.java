package bvp;

import bvp.views.GameBoard;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		GameBoard gameBoard = new GameBoard();
		JFrame window = new JFrame();

		window.setTitle("Bee V Panda: Adventure in Jungle");
		window.setSize(1000, 700);

		window.add(gameBoard);
		window.setLocation(170, 15);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}
