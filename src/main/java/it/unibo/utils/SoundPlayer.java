package it.unibo.utils;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer {
    Clip clip;
	URL soundURL[]=new URL[2];

    public final int BACKGROUND_MUSIC=0;
    public final int CANNON_SHOOT=1;

	public SoundPlayer(){
		soundURL[0]=getClass().getResource("/sounds/Background.wav");
        soundURL[1]=getClass().getResource("/sounds/Background.wav");
	}
	
	public void setFile(int i){
		try{
			AudioInputStream ais=AudioSystem.getAudioInputStream(soundURL[i]);
			clip=AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e){}
	}

	public void play(){
		clip.start();
	}

	public void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop(){
		clip.stop();
	}
}
