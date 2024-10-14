package bvp.views;

import bvp.controllers.BeeMover;
import bvp.controllers.BulletFiring;
import bvp.controllers.GameSound;
import bvp.models.Bee;
import bvp.models.Bullet;
import bvp.models.Shooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements KeyListener {

    private final Shooter background1 = new Shooter(0, 0, "/drawables/layouts/ic_layout_1.png");
    private final Shooter background2 = new Shooter(1000, 0, "/drawables/layouts/ic_layout_1.png");

    Shooter shooter;
    private int shooterCount = 1;
    boolean isCollision = false;

    Bee[] bee = new Bee[6];
    Bullet[] bullets = new Bullet[100];
    int bulletCount = 0;

    public static int score = 0;
    private int life = 3;


    public GameBoard() {
        super();
        super.addKeyListener(this);
        super.setFocusable(true);
        shooter = new Shooter(5, 255, null);

        int xBee = 1050;
        int yBee = 23;

        for (int i = 0; i < bee.length; i++) {
            bee[i] = new Bee(xBee, yBee, "/drawables/bees/ic_bee_common.png", true);

            yBee += 80;
        }

        for (int i = 0; i < bullets.length; i++) {
            bullets[i] = new Bullet(-100, -316, "/drawables/bullets/ic_bullet_1.png");
        }

        for (Bee value : bee) {
            BeeMover bm = new BeeMover(value, this, shooter);
            bm.start();
        }

    }

    //for life of Shooter
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public void paint(Graphics graphics) {

        background1.draw(graphics);
        background2.draw(graphics);
        this.scrollBackground(); //for infinite BackGround

        //shooter.draw(g); //Non-animated Shooter

        if (!isCollision) {
            shooter.setImagePath("/drawables/characters/" + shooterCount + ".png");
            shooterCount++;
        }
        try {
            Thread.sleep(10); //speed is 10
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.repaint();


        shooter.draw(graphics);

        if (shooterCount == 7) {
            shooterCount = 1;
        }

        for (Bee value : bee) {
            if (value.isAlive()) {
                value.draw(graphics);
            }
        }

        bullets[bulletCount].draw(graphics);

        for (Bullet bullet : bullets) {
            bullet.draw(graphics);
        }

        if (!isCollision && life != 0) {
            Font f = new Font("Serif", Font.BOLD, 24);
            graphics.setColor(Color.BLACK);
            graphics.setFont(f);
            graphics.drawString("SCORE : " + score, 315, 600);
        }

        if (life != 0) {
            Font f = new Font("Serif", Font.BOLD, 24);

            if (life == 3) {
                graphics.setColor(Color.GREEN);
                graphics.setFont(f);
                graphics.drawString("Lives Remaining : " + life, 485, 600);
            } else if (life == 2) {
                graphics.setColor(Color.YELLOW);
                graphics.setFont(f);
                graphics.drawString("Lives Remaining : " + life, 485, 600);
            } else if (life == 1) {
                graphics.setColor(Color.RED);
                graphics.setFont(f);
                graphics.drawString("Life Remaining : " + life, 485, 600);
            }
        }

        if (life == 0) {
            Font f1 = new Font("Serif", Font.BOLD, 55);
            graphics.setColor(Color.RED);
            graphics.setFont(f1);
            graphics.drawString("Game Over!", 360, 300);
            graphics.drawString("Play Again", 370, 350);

            Font f2 = new Font("Serif", Font.BOLD, 24);
            graphics.setColor(Color.BLUE);
            graphics.setFont(f2);
            graphics.drawString("TOTAL SCORE : " + score, 390, 400);

        }

    }


    public void scrollBackground() {
        if (background1.getX() > -1000) {
            background1.setX(background1.getX() - 2);
        } else {
            background1.setX(1000);
        }

        if (background2.getX() > -1000) {
            background2.setX(background2.getX() - 2);
        } else {
            background2.setX(1000);
        }

        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (shooter.getX() > 0) {
                shooter.setX(shooter.getX() - 10);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (shooter.getX() < 760) {
                shooter.setX(shooter.getX() + 10);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (shooter.getY() > 7)
                shooter.setY(shooter.getY() - 10);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (shooter.getY() < 440)
                shooter.setY(shooter.getY() + 10);
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets[bulletCount].setX(shooter.getX() + 100);
            bullets[bulletCount].setY(shooter.getY() + 63);

            GameSound.bulletFiredSound();

            BulletFiring fire = new BulletFiring(this, bee, bullets[bulletCount]);

            fire.start();
            bulletCount++;

            if (bulletCount == 100) {
                bulletCount = 0;
            }

        }

        super.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}


