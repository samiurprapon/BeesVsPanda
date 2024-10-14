package bvp.views;

import bvp.controllers.BeeMover;
import bvp.controllers.BulletFiring;
import bvp.controllers.GameSound;
import bvp.models.Bee;
import bvp.models.Bullet;
import bvp.models.Shooter;
import bvp.views.GameBoard;
import bvp.views.OnStartBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Starting extends JPanel implements KeyListener, MouseListener {

    private Image backgroundImage;
    private JFrame window;
    public Starting(JFrame window) {
        super();
        this.window = window;
        super.addMouseListener(this);
        backgroundImage = new ImageIcon(getClass().getResource("/drawables/layouts/panda_vs_bee.png")).getImage();
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);


        Font f1 = new Font("Serif", Font.BOLD, 75);
        graphics.setColor(Color.RED);
        graphics.setFont(f1);
        graphics.drawString("BEERS VS PANDA", 170, 200);

        Font f2 = new Font("Serif", Font.BOLD, 55);
        graphics.setColor(Color.RED);
        graphics.setFont(f2);
        graphics.drawString("START!", 370, 350);

        Font f3 = new Font("Serif", Font.PLAIN, 25);
        graphics.setColor(Color.BLUE);
        graphics.setFont(f3);
        graphics.drawString("Click to start", 470, 369);
    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getX()>370&&e.getX()<565&&e.getY()>309&&e.getY()<375){
            window.dispose();

            GameBoard onStartBoard = new GameBoard();
            JFrame window = new JFrame();

            window.setTitle("Bee V Panda: Adventure in Jungle");
            window.setSize(1000, 700);

            window.add(onStartBoard);
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
