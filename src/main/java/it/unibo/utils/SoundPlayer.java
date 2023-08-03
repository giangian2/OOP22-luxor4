package it.unibo.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The SoundPlayer provides methods for playing different sounds.
 */
public class SoundPlayer {
    private Clip[] clips;
    private URL[] soundURL = new URL[2];

    /**
     * Representes indexes of background music.
     */
    public static final int BACKGROUND_MUSIC = 0;

    /**
     * Representes indexes of ball collision sound.
     */
    public static final int BALL_COLLISION = 1;

    /**
     * Construct a new SoundPlayer.
     * @param soundsPath list of paths to sounds
     */
    public SoundPlayer(final ArrayList<String> soundsPath) {
        for (int i = 0; i < soundsPath.size(); i++) {
            soundURL[i] = getClass().getResource(soundsPath.get(i));
        }

        clips = new Clip[soundURL.length];
    }

    /**
     * Sets the audio file for the specified sound index to be played.
     *
     * @param soundIndex The index of the sound for which to set the audio file.
     *                   Must be one of the constants defined in the SoundPlayer class.
     */
    private void setFile(final int soundIndex) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[soundIndex]);
            clips[soundIndex] = AudioSystem.getClip();
            clips[soundIndex].open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the sound associated with the specified sound index from the beginning.
     * If the sound is already playing, it will restart from the beginning.
     *
     * @param soundIndex The index of the sound to be played from the start.
     *                   Must be one of the constants defined in the SoundPlayer class.
     */
    public void playFromStart(final int soundIndex) {
        if (soundIndex >= 0 && soundIndex < clips.length) {
                setFile(soundIndex);
                clips[soundIndex].setFramePosition(0); // Vai all'inizio
                clips[soundIndex].start(); // Avvia il suono dall'inizio
        }
    }

    /**
     * Plays the sound associated with the specified sound index.
     * If the sound is already playing, it will continue playing from its current position.
     *
     * @param soundIndex The index of the sound to be played.
     *                   Must be one of the constants defined in the SoundPlayer class.
     */
    public void play(final int soundIndex) {
        if (soundIndex >= 0 && soundIndex < clips.length) {
            setFile(soundIndex);
            clips[soundIndex].start();
        }
    }

    /**
     * Plays the sound associated with the specified sound index repeatedly in a loop.
     * The sound will keep playing until explicitly stopped.
     *
     * @param soundIndex The index of the sound to be played in a loop.
     *                   Must be one of the constants defined in the SoundPlayer class.
     */
    public void loop(final int soundIndex) {
        if (soundIndex >= 0 && soundIndex < clips.length) {
            clips[soundIndex].loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Pauses the sound associated with the specified sound index.
     * If the sound is not currently playing, this method has no effect.
     *
     * @param soundIndex The index of the sound to be paused.
     *                   Must be one of the constants defined in the SoundPlayer class.
     */
    public void pause(final int soundIndex) {
        if (soundIndex >= 0 && soundIndex < clips.length) {
            if (clips[soundIndex].isRunning()) {
                clips[soundIndex].stop(); // Metti in pausa il suono
            }
        }
    }

    /**
     * Stops all currently playing sounds and rewinds them to the beginning.
     * Any sounds that are paused will also be stopped and reset.
     */
    public void stopAll() {
        for (Clip clip : clips) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.setFramePosition(0); // Rewind to the beginning
            }
        }
    }


}
