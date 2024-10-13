package bvp;

import bvp.views.GameBoard;
import bvp.views.OnStartBoard;
import bvp.views.Starting;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setTitle("Bee V Panda: Adventure in Jungle");
        window.setSize(1000, 700);

        Starting startBoard = new Starting(window);
        window.add(startBoard);

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }
}
