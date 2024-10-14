package bvp.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OnStartBoard extends JPanel implements KeyListener, MouseListener {

    ImageIcon background = new ImageIcon("src//resources//drawables/layouts//ic_layout_0.png");

    public OnStartBoard(JFrame window) {
        super();
        super.addKeyListener(this);
        super.setFocusable(true);
    }

    @Override
    public void paint(Graphics graphics) {
        graphics.drawImage(background.getImage(), 0, 0, null);
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
