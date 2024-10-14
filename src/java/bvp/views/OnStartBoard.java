package bvp.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class OnStartBoard extends JPanel implements KeyListener {

    ImageIcon background = new ImageIcon(getClass().getResource("/drawables/layouts/ic_layout_0.png"));

    public OnStartBoard() {
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
}
