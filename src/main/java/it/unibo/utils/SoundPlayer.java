package it.unibo.utils;

import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * The SoundPlayer provides 
 */
public class SoundPlayer {
    private Clip[] clips;
	URL soundURL[]=new URL[2];

    public static final int BACKGROUND_MUSIC=0;
    public static final int BALL_COLLISION=1;

	
	public SoundPlayer(ArrayList<String> soundsPath){
		for(int i=0;i<soundsPath.size();i++){
			soundURL[i]=getClass().getResource(soundsPath.get(i));
		}

		clips = new Clip[soundURL.length];
	}
	
	public void setFile(int i){
		try{
			AudioInputStream ais=AudioSystem.getAudioInputStream(soundURL[i]);
			clips[i]=AudioSystem.getClip();
			clips[i].open(ais);
		}catch(Exception e){}
	}

	public void playFromStart(int soundIndex){
		if (soundIndex >= 0 && soundIndex < clips.length) {
			    setFile(soundIndex);
				clips[soundIndex].setFramePosition(0); // Vai all'inizio
				clips[soundIndex].start(); // Avvia il suono dall'inizio
		}
	}

	public void play(int soundIndex){
		if (soundIndex >= 0 && soundIndex < clips.length) {
			setFile(soundIndex);
            clips[soundIndex].start();
        }
	}

	public void loop(int soundIndex){
		if (soundIndex >= 0 && soundIndex < clips.length) {
            clips[soundIndex].loop(Clip.LOOP_CONTINUOUSLY);
        }
	}

	public void pause(int soundIndex){
		if (soundIndex >= 0 && soundIndex < clips.length) {
			if (clips[soundIndex].isRunning()) {
				clips[soundIndex].stop(); // Metti in pausa il suono
			}
		}
	}

	public void stopAll(){
		for (Clip clip : clips) {
            if (clip != null && clip.isRunning()) {
                clip.stop();
                clip.setFramePosition(0); // Rewind to the beginning
            }
        }
	}

	
}
