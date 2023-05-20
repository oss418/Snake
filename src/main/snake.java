package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class snake extends KeyAdapter {
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
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

	public void move() {
		
	}
}