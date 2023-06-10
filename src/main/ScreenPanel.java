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
	private final int distance = 5;
	private final int randomness = 14;
	private final int delay = 60;
	snake temp = new snake();

	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;

	// constructor
	public ScreenPanel() {
		initScreenPanel();
	}

	// methods
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (inGame) {
			checkCrash();
			paintComponent();
			
		}
	}

	private void loadIcon() {
		ImageIcon myImageY = new ImageIcon("src/snake-head.jpeg");
		this.snake_pic = myImageY.getImage();
		ImageIcon myImageYA = new ImageIcon("src/grape.jpeg");
		this.snack_pic = myImageYA.getImage();
		ImageIcon myImageYAY = new ImageIcon("src/grape.jpeg");
		this.body_pic = myImageYAY.getImage();
	}

	private void initSnakeGame(int length) {
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
		// TODO Auto-generated method stub
		int r = (int) Math.random() * randomness;
		snackX = r * distance;
		r = (int) Math.random() * randomness;
		snackY = r * distance;
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
			for (int z = 0; z > 0; z--) {
				x[z] = x[(z - 1)];
				y[z] = y[(z - 1)];
			}

			if (temp.left) {
				x[0] -= distance;
			}

			if (temp.right) {
				x[0] += distance;
			}

			if (temp.up) {
				y[0] -= distance;
			}

			if (temp.down) {
				y[0] += distance;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	private void initScreenPanel() {
		// addKeyListener(new GameKeyAdapter());
		setBackground(Color.black);
		setFocusable(true);

		setPreferredSize(new Dimension(width, height));
		loadIcon();
		initSnakeGame(1);
	}
	
	private void checkCrash() {
		for (int i = parts; i > 0; i--) {
			//TODO: fix i > 6
			if ((i > 6) && (x[0] == x[i]) && (y[0] == y[i])) {
                inGame = false;
            }
		}
		if (!inGame) {
			timer.stop();
		}
	}
	
	private void GameOver(Graphics g) {
		String message = "LOL";
		Font small = new Font("Arial", Font.BOLD, 50);
		FontMetrics metr = getFontMetrics(small);
		g.setColor(Color.MAGENTA);
		g.setFont(small);
		g.drawString(message, (width - metr.stringWidth(message))/2, height/2);
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
