package org.qme.io;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl;

/**
 * An audio player for background music
 * @author jakeroggenbuck
 * @since 0.3.0
 */
public class AudioPlayer {

    public static String audioFile;

    // Store current position in audio
    private long currentPosition;
    private Clip clip;

    // The state of the player
    public AudioPlayerState audioPlayerState;

    private AudioInputStream audioInputStream;

    public static float volume;

    // Initialize streams and clip
    public AudioPlayer(String audioFile) {
        try {
            // Create AudioInputStream object
            audioInputStream = AudioSystem.getAudioInputStream(new File(String.valueOf(AudioFiles.class.getResource(audioFile))).getAbsoluteFile());

            // Create clip
            clip = AudioSystem.getClip();

            // Open audioInputStream to the clip
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            audioPlayerState = AudioPlayerState.PLAY;

            setVolume(-20.0f);

        }

        catch (Exception ex) {
            Logger.log("Error with playing sound.", Severity.ERROR);
            ex.printStackTrace();
        }
    }
    public void changeVolume(float change) {
        setVolume(volume + change);
    }

    public void setVolume(float set) {
        if (set > -80.0f && set < 6.0f) {
            if (audioPlayerState == AudioPlayerState.PLAY) {
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(set);
                clip.start();
            }
            volume = set;
        }
    }

    // Method to play the audio
    public void play() {
        // Start the clip
        clip.start();
        audioPlayerState = AudioPlayerState.PLAY;
        setVolume(volume);
        Logger.log("Audio started", Severity.DEBUG);
    }

    // Method to pause the audio
    public void pause() {
        if (audioPlayerState == AudioPlayerState.PAUSED) {
            Logger.log("Audio is already paused", Severity.DEBUG);
            return;
        }
        this.currentPosition = this.clip.getMicrosecondPosition();
        clip.stop();
        audioPlayerState = AudioPlayerState.PAUSED;
        Logger.log("Audio paused", Severity.DEBUG);
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (audioPlayerState == AudioPlayerState.PLAY) {
            Logger.log("Audio is already being played", Severity.DEBUG);
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentPosition);
        this.play();
        Logger.log("Audio resumed", Severity.DEBUG);
    }

    // Method to restart the audio
    public void restart() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentPosition = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
        Logger.log("Audio restarted", Severity.DEBUG);
    }

    // Method to stop the audio
    public void stop() {
        currentPosition = 0L;
        clip.stop();
        clip.close();
        Logger.log("Audio stopped", Severity.DEBUG);
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
            Logger.log("Audio location changed with jump()", Severity.DEBUG);
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File(audioFile).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        Logger.log("AudioStream restarted", Severity.DEBUG);
    }
}
