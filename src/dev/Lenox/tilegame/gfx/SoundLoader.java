package dev.Lenox.tilegame.gfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class SoundLoader{
private Clip clip;
 public Clip loadSound(String url) {
 Clip clip = null;
 try{
	 AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(url));
	 clip = AudioSystem.getClip();
	 clip.open(audioIn);
	 
 }catch(Exception e){
	 e.printStackTrace();
 }
  
	  return clip;
	  
  }
  
  public void playClip(Clip clip){
	  stopClip(clip);
	  clip.start();
	  this.clip = clip;
  }
  public void stopClip(Clip clip){
	  if(clip.isRunning())
		  clip.stop();
	  
	  clip.setFramePosition(0);
  }
  public Clip getClip(){
	  return clip;
  }
  
  }