package org.qme.io;
// Java program to play an Audio
// file using Clip Object

import org.qme.io.PlayerState;
import org.qme.io.Logger;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * An audio player for background music
 * @author jakeroggenbuck
 * @since 0.3.0
 */
public class AudioPlayer {

    private static String filePath = "/sounds/music/QME5_Menu.ogg";

    // Store current position in audio
    private long currentPosition;
    private Clip clip;

    // The state of the player
    private PlayerState playerState;

    private AudioInputStream audioInputStream;

    // Initialize streams and clip
    public AudioPlayer() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            // Create AudioInputStream object
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            // Create clip
            clip = AudioSystem.getClip();

            // Open audioInputStream to the clip
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        catch (Exception ex) {
            Logger.log("Error with playing sound.", Severity.ERROR);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        try {
            AudioPlayer audioPlayer = new AudioPlayer();
            audioPlayer.play();
        }

        catch (Exception ex) {
            Logger.log("Error with playing sound.", Severity.ERROR);
            ex.printStackTrace();
        }
    }

    // Method to play the audio
    public void play()
    {
        // Start the clip
        clip.start();
        playerState = PlayerState.PLAY;
    }

    // Method to pause the audio
    public void pause() {
        if (playerState == PlayerState.PAUSED) {
            Logger.log("Audio is already paused", Severity.DEBUG);
            return;
        }
        this.currentPosition = this.clip.getMicrosecondPosition();
        clip.stop();
        playerState = PlayerState.PAUSED;
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (playerState == PlayerState.PLAY) {
            Logger.log("Audio is already being played", Severity.DEBUG);
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentPosition);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentPosition = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        currentPosition = 0L;
        clip.stop();
        clip.close();
    }

    // Method to jump over a specific part
    public void jump(long location) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (location > 0 && location < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentPosition = location;
            clip.setMicrosecondPosition(location);
            this.play();
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
