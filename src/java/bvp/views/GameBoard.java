package bvp.views;

import bvp.controllers.BeeMover;
import bvp.controllers.BossMover;
import bvp.controllers.BulletFiring;
import bvp.controllers.GameSound;
import bvp.models.Bee;
import bvp.models.Bullet;
import bvp.models.Level;
import bvp.models.Shooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements KeyListener, MouseListener {

    private Shooter background1;
    private Shooter background2;

    private final int level;
    private final Level config;
    private volatile boolean levelActive = true;

    private Bee boss = null;                 //null until the boss is summoned
    private volatile boolean bossSpawned = false;
    private volatile boolean levelComplete = false;
    private volatile boolean gameWon = false;
    private int killsThisLevel = 0;

    Shooter shooter;
    private int shooterCount = 1;
    boolean isCollision = false;

    Bee[] bee;
    Bullet[] bullets = new Bullet[100];
    int bulletCount = 0;

    public static int score = 0;
    private int life = 3;
    private JFrame window;

    public GameBoard(JFrame window, int level) {
        super();
        this.window = window;
        this.level = level;
        this.config = Level.getLevel(level);
        if (level == 1) {
            score = 0; //reset accumulated score on a brand-new game (level 1 / Play Again)
        }
        super.addKeyListener(this);
        super.setFocusable(true);
        super.addMouseListener(this);
        shooter = new Shooter(5, 255, null);

        background1 = new Shooter(0, 0, config.getBgPath());
        background2 = new Shooter(1000, 0, config.getBgPath());

        bee = new Bee[config.getBeeCount()];
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
            BeeMover bm = new BeeMover(value, this, shooter, config.getBeeDelayMs());
            bm.start();
        }

    }

    public int getLevel() {
        return level;
    }

    public Level getConfig() {
        return config;
    }

    public boolean isLevelActive() {
        return levelActive;
    }

    public Bee getBoss() {
        return boss;
    }

    public boolean isBossSpawned() {
        return bossSpawned;
    }

    public boolean isLevelComplete() {
        return levelComplete;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public int getKillsThisLevel() {
        return killsThisLevel;
    }

    public void incKill() {
        killsThisLevel++;
    }

    public boolean killsReached() {
        return killsThisLevel >= config.getKillsToSummon();
    }

    /** Stops the common-bee swarm and spawns the level boss off the right edge. */
    public void summonBoss() {
        if (bossSpawned) {
            return;
        }
        bossSpawned = true;
        levelActive = false; //halts the BeeMover swarm
        boss = new Bee(1100, 250, config.getBossImagePath(), true,
                config.getBossHp(), 220, 170, config.getBossName(), true);
        new BossMover(boss, this, shooter).start();
    }

    /** Called when the boss HP hits 0: award points, mark level done, win on the last level. */
    public void onBossDefeated() {
        if (levelComplete) {
            return;
        }
        score += config.getBossPoints();
        levelComplete = true;
        if (boss != null) {
            boss.setAlive(false);
            boss.setX(2000); //move the corpse off-screen
        }
        if (level >= Level.maxLevel()) {
            gameWon = true;
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

        if (boss != null && boss.isAlive()) {
            boss.draw(graphics);
        }

        if (bossSpawned && !levelComplete && life != 0 && boss != null) {
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Serif", Font.BOLD, 30));
            graphics.drawString("BOSS: " + config.getBossName() + "   HP " + boss.getHealth(), 230, 55);
        }

        bullets[bulletCount].draw(graphics);

        for (Bullet bullet : bullets) {
            bullet.draw(graphics);
        }

        if (!isCollision && life != 0) {
            Font f = new Font("Serif", Font.BOLD, 24);
            graphics.setColor(Color.BLACK);
            graphics.setFont(f);
            graphics.drawString("LEVEL " + level + "   SCORE : " + score, 315, 600);
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

        if (levelComplete) {
            graphics.setColor(new Color(0, 0, 0, 150));
            graphics.fillRect(0, 0, 1000, 700);

            if (gameWon) {
                graphics.setColor(Color.GREEN);
                graphics.setFont(new Font("Serif", Font.BOLD, 60));
                graphics.drawString("YOU WIN!", 340, 290);
                graphics.setColor(Color.YELLOW);
                graphics.setFont(new Font("Serif", Font.BOLD, 26));
                graphics.drawString("TOTAL SCORE : " + score, 360, 345);
                graphics.drawString("Click to play again", 350, 395);
            } else {
                graphics.setColor(Color.GREEN);
                graphics.setFont(new Font("Serif", Font.BOLD, 50));
                graphics.drawString("LEVEL " + level + " COMPLETE!", 240, 290);
                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("Serif", Font.BOLD, 26));
                graphics.drawString("Boss " + config.getBossName() + " defeated", 300, 345);
                graphics.drawString("Click to continue", 350, 395);
            }
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

            BulletFiring fire = new BulletFiring(this, bee, bullets[bulletCount], config.getBulletDelayMs());

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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (life == 0) {
            if (e.getX() > 365 && e.getX() < 635 && e.getY() > 310 && e.getY() < 353) {
                openLevel(1); //Play Again -> fresh game (resets score)
            }
        } else if (levelComplete) {
            openLevel(gameWon ? 1 : level + 1); //advance to next level, or restart after winning
        }
    }

    /** Tear down this window and start a fresh one at the given level. */
    private void openLevel(int nextLevel) {
        window.dispose();

        JFrame window = new JFrame();
        window.setTitle("Bee V Panda: Adventure in Jungle");
        window.setSize(1000, 700);

        GameBoard next = new GameBoard(window, nextLevel);
        window.add(next);

        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setVisible(true);
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


