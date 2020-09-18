package com.me.main;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {// classe Sound

	public static class Clips{// classe Clips
		//sim, � uma classe dentro de outra class.
	
			public Clip[] clips;
			private int p;
			private int count;
			
			public Clips(byte[] buffer, int count) throws LineUnavailableException, IOException, UnsupportedAudioFileException { //byte seria similar a um int, s� que menor
													//caso o construtor n�o d� certo, ir� relatar isso
				if(buffer == null)
					return;
				
				clips = new Clip[count];
				this.count = count;
			
				
				for(int i = 0; i < count; i++) {
					
					clips[i] = AudioSystem.getClip();
					clips[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
					
				}
			}
			
			public void play() {
			
				if(clips == null) return;
				
				clips[p].stop();
				clips[p].setFramePosition(0);
				clips[p].start();
				p++;
				
				if(p >= count) p = 0;
			}
			
			public void loop() {
				
				if(clips == null) return;
				
				clips[p].loop(300);
				
			}
		
	}
	
	public static Clips musicBackground = load("Vitality.wav", 1);
	public static Clips hitBullet = load("Bullet.wav", 1);
	public static Clips deathEnemy = load("Death.wav", 1);

	
	
	private static Clips load(String name, int count) {
		
		
	try {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		DataInputStream dis = new DataInputStream(Sound.class.getResourceAsStream("/"+name));
		
		byte[] buffer = new byte[1024];
		int read = 0;
		
		while((read = dis.read(buffer)) >= 0) {
		baos.write(buffer, 0, read);	
		}
		
		dis.close();
		byte[] data = baos.toByteArray();
		return new Clips(data, count);  
		
	}catch(Exception e) {
		
		try {
			return new Clips(null, 0);
		} catch(Exception ee) {
			return null;
		}
		
	}
	}
	
	
}
