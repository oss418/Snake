package main;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class snakeGame extends JFrame{
	
	public snakeGame() {
		startScreen();
	}
	
	private void startScreen() {
		add(new ScreenPanel());
		setResizable(false);
		pack();//It sizes the frame so that all its contents are at or above their preferred sizes.
		setTitle("Snake Game");
		setLocationRelativeTo(null);//tells the game window where to spawn relative to the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(()-> {
			JFrame frame = new snakeGame();
			frame.setVisible(true);
		});
	}
}
