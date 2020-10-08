package org.qme.audio;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException;

import org.qme.main.Main;

/**
 * This class wraps all the java hell to make things easier
 * @author santiago
 * @since pre4
 */
public class AudioWrapper {
	/**
	 * The sound that will play, derived from a file
	 */
	AudioInputStream stream;
	/**
	 * Gonna be honest, no idea, but it needs it to work
	 */
	Clip clip;
	
	/**
	 * Sets up the hell
	 * @param audio
	 * @throws IOException
	 * @throws LineUnavailableException
	 * @throws UnsupportedAudioFileException
	 * @since pre4
	 */
	public AudioWrapper(File audio) {
		try {
			this.stream = AudioSystem.getAudioInputStream(audio);
			this.clip = AudioSystem.getClip();
			this.clip.open(this.stream);
		} catch(IOException e) {
			Main.displayError("Something has gone wrong importing the audio.", true);
		} catch(LineUnavailableException f) {
			Main.displayError("The audio file seems to have corrupted somehow.", true);
		} catch(UnsupportedAudioFileException g) {
			Main.displayError("The audio file's format is unsupported, that's weird.", false);
		}
	}
	
	public void editMusic(File audio) {
		try {
			this.stream = AudioSystem.getAudioInputStream(audio);
			this.clip = AudioSystem.getClip();
			this.clip.open(this.stream);
		} catch(IOException e) {
			Main.displayError("Something has gone wrong importing the audio.", true);
		} catch(LineUnavailableException f) {
			Main.displayError("The audio file seems to have corrupted somehow.", true);
		} catch(UnsupportedAudioFileException g) {
			Main.displayError("The audio file's format is unsupported, that's weird.", false);
		}
	}
	
	public void loopForever() {
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stopLooping() {
		this.clip.stop();
	}
	
	public void playOnce() {
		this.clip.loop(0);
	}
}