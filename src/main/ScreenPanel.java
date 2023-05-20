package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ScreenPanel extends JPanel implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void loadIcon() {
		ImageIcon myImageYAY = new ImageIcon("src/resources/IMAGENAME.png");
        Image snake_pic = myImageYAY.getImage();
	}

}
