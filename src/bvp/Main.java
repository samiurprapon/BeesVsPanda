package bvp;

import bvp.views.GameBoard;
import bvp.views.OnStartBoard;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        GameBoard onStartBoard = new GameBoard();

        JFrame window = new JFrame();

        window.setTitle("Bee V Panda: Adventure in Jungle");
        window.setSize(1000, 700);

        window.add(onStartBoard);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }
}
