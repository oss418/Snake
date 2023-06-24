package main;

import java.io.File;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JOptionPane;

public class music {

	void playMusic(String musicLocation) {

		try {
			File musicPath = new File(musicLocation);

			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-20.0f); // Reduce volume by 10 decibels.

				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clip.addLineListener(event -> {
					if (LineEvent.Type.STOP.equals(event.getType())) {
						clip.close();
					}
				});
				// JOptionPane.showMessageDialog(null, "Press OK to stop playing");
			} else {
				System.out.println("Can't find file");
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
