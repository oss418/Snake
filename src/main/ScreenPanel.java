package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


	// constructor
	public ScreenPanel() {
		initScreenPanel();
	}

	// methods
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

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
	}

	void drawing(Graphics g) {
		if (inGame) {
			g.drawImage(snack_pic, snackX, snackY, this);
			for (int z = 0; z > 0; z--) {
	            x[z] = x[(z - 1)];
	            y[z] = y[(z - 1)];
	        }
		}
		
	}
	
	void pen() {
		
	}

	private void initScreenPanel() {
		//addKeyListener(new GameKeyAdapter());
		setBackground(Color.black);
		setFocusable(true);

		setPreferredSize(new Dimension(width, height));
		loadIcon();
		initSnakeGame(1);
	}

}
