package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class ScreenPanel extends JPanel implements ActionListener {

  private final int width = 500;
  private final int height = 500;
  private boolean inGame = true;
  private int snackX;
  private int snackY;
  private int parts;
  private Image snack_pic;
  private Image snake_pic;
  private Image body_pic;
  private Timer timer;
  private final int spaces = 100;
  private final int x[] = new int[spaces];
  private final int y[] = new int[spaces];
  private final int distance = 10;
  private final int randomness = 39;
  private final int delay = 160;

  private boolean up = false;
  private boolean down = false;
  private boolean right = true;
  private boolean left = false;

  // Constructor
  public ScreenPanel() {
    initScreenPanel();
  }

  // Methods
  @Override
  public void actionPerformed(ActionEvent event) {
    if (inGame) {
      findGrapeIcon();
      checkCrash();
      shift();
    }
    repaint();
  }

  private void loadIcon() {
    ImageIcon snakeBody = new ImageIcon("snake-body.png");
    Image resized1 = snakeBody.getImage();
    Image newimg1 = resized1.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
    snakeBody = new ImageIcon(newimg1);
    body_pic = snakeBody.getImage();

    ImageIcon grape = new ImageIcon("grape.png");
    Image resized2 = grape.getImage();
    Image newimg2 = resized2.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
    grape = new ImageIcon(newimg2);
    snack_pic = grape.getImage();

    ImageIcon snakeHead = new ImageIcon("snake-head.jpeg");
    Image resized3 = snakeHead.getImage();
    Image newimg3 = resized3.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
    snakeHead = new ImageIcon(newimg3);
    snake_pic = snakeHead.getImage();
  }

  private void initSnakeGame() {
    parts = 5;

    for (int z = 0; z < parts; z++) {
      x[z] = 50 - z * 10;
      y[z] = 50;
    }

    locateGrape();

    timer = new Timer(delay, this);
    timer.start();
  }

  private void locateGrape() {
    int r = (int) (Math.random() * randomness);
    snackX = ((r * distance));
    r = (int) (Math.random() * randomness);
    snackY = ((r * distance));
  }

  private void findGrapeIcon() {
    if ((x[0] == snackX) && (y[0] == snackY)) {
      parts++;
      locateGrape();
    }
  }

  private void doDrawing(Graphics g) {

    if (inGame) {
      g.drawImage(snack_pic, snackX, snackY, this);
      for (int z = 0; z < parts; z++) {
        if (z == 0) {
          g.drawImage(snake_pic, x[z], y[z], this);
        } else {
          g.drawImage(body_pic, x[z], y[z], this);
        }
      }
      Toolkit.getDefaultToolkit().sync();
    } else {
      GameOver(g);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    doDrawing(g);
  }

  private void GameOver(Graphics g) {
    String message = "LOL";
    Font small = new Font("Arial", Font.BOLD, 50);
    FontMetrics metr = getFontMetrics(small);
    g.setColor(Color.MAGENTA);
    g.setFont(small);
    g.drawString(message, (width - metr.stringWidth(message)) / 2, height / 2);
  }

  private void shift() {

    for (int z = parts; z > 0; z--) {
      x[z] = x[(z - 1)];
      y[z] = y[(z - 1)];
    }

    if (left) {
      x[0] -= distance;
    }

    if (right) {
      x[0] += distance;
    }

    if (up) {
      y[0] -= distance;
    }

    if (down) {
      y[0] += distance;
    }
  }

  private void initScreenPanel() {
    addKeyListener(new GameKeyAdapter());
    setBackground(Color.black);
    setFocusable(true);

    setPreferredSize(new Dimension(width, height));
    loadIcon();
    initSnakeGame();
  }

  private void checkCrash() {
    for (int i = parts; i > 0; i--) {
      if ((i > 6) && (x[0] == x[i]) && (y[0] == y[i])) {
        inGame = false;
      }
    }
    // Adding Walls for now (we can implement looping later if we wish)
    if (y[0] >= height) {
      inGame = false;
    }

    if (y[0] < 0) {
      inGame = false;
    }

    if (x[0] >= width) {
      inGame = false;
    }

    if (x[0] < 0) {
      inGame = false;
    }

    if (!inGame) {
      timer.stop();
    }
  }

  private class GameKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent event) {
      int key = event.getKeyCode();
      if ((key == KeyEvent.VK_UP) && !down) {
        up = true;
        right = false;
        left = false;
      }
      if ((key == KeyEvent.VK_DOWN) && !up) {
        down = true;
        right = false;
        left = false;
      }
      if ((key == KeyEvent.VK_RIGHT) && !left) {
        right = true;
        down = false;
        up = false;
      }
      if ((key == KeyEvent.VK_LEFT) && !right) {
        left = true;
        down = false;
        up = false;
      }
    }
  }
}
