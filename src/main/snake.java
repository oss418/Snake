package main;

public class snake {

}
public void moveUp(map screen, snake mySnake) {
	if (mySnake.getX() <= 0) {
		mySnake.setX(screen.getWidth() - 1);
		screen.setObjectOnSpot(mySnake, mySnake.getX(), mySnake.getY());
		screen.reset(0, mySnake.getY());
	} else {
		mySnake.setX(getX() - 1); // moving snake
		screen.setObjectOnSpot(mySnake, mySnake.getX(), mySnake.getY()); // putting the snake part on that box
		screen.reset(mySnake.getX() + 1, mySnake.getY()); // clear the box before the snake move
	}
}

public void moveDown(map screen, snake mySnake) {
	if (mySnake.getX() >= screen.getWidth() - 1) {
		mySnake.setX(0);
		screen.setObjectOnSpot(mySnake, mySnake.getX(), mySnake.getY());
		screen.reset(screen.getHeight() - 1, mySnake.getY());
	} else {
		mySnake.setX(getX() + 1);
		screen.setObjectOnSpot(mySnake, mySnake.getX(), mySnake.getY());
		screen.reset(mySnake.getX() - 1, mySnake.getY());
	}
}

public void moveLeft(map screen, snake mySnake) {
	if (mySnake.getY() <= 0) {
		mySnake.setY(screen.getHeight() - 1);
		screen.setObjectOnSpot(mySnake, mySnake.getX(), mySnake.getY());
		screen.reset(mySnake.getX(), 0);
	} else {
		mySnake.setY(getY() - 1);
		screen.setObjectOnSpot(mySnake, mySnake.getX(), mySnake.getY());
		screen.reset(mySnake.getX(), mySnake.getY() + 1);
	}
}

public void moveRight(map screen, snake mySnake) {
	if (mySnake.getY() >= screen.getHeight() - 1) {
		mySnake.setY(0);
		screen.setObjectOnSpot(mySnake, mySnake.getX(), mySnake.getY());
		screen.reset(mySnake.getX(), screen.getHeight() - 1);
	} else {
		mySnake.setY(getY() + 1);
		screen.setObjectOnSpot(mySnake, mySnake.getX(), mySnake.getY());
		screen.reset(mySnake.getX(), mySnake.getY() - 1);
	}
}