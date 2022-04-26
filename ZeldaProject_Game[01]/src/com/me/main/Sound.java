package com.me.main;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {// classe Sound

	public static class Clips {// classe Clips
		// sim, � uma classe dentro de outra class.

		public Clip[] clips;
		public FloatControl[] floatControls;
		private int p;
		private int count;
		private SoundType soundType = SoundType.MUSIC;
		private float currentVolume = -15.0f;

		public Clips(byte[] buffer, int count, SoundType st)
				throws LineUnavailableException, IOException, UnsupportedAudioFileException { // byte seria similar a um
																								// int, s� que menor
			// caso o construtor n�o d� certo, ir� relatar isso
			if (buffer == null)
				return;

			clips = new Clip[count];
			this.count = count;

			if (st != null) {
				this.soundType = st;
			}

			for (int i = 0; i < count; i++) {

				clips[i] = AudioSystem.getClip();
				clips[i].open(AudioSystem.getAudioInputStream(new ByteArrayInputStream(buffer)));
				//floatControls[i] = (FloatControl) clips[i].getControl(FloatControl.Type.MASTER_GAIN);
			}
		}

		public void getVolumeSize(int newVolume) {
			switch (this.soundType) {
			case MUSIC:
				break;

			case SFX:
				break;

			}
		}

		public void play() {

			if (clips == null) return;
			clips[p].stop();
			
			FloatControl fc = (FloatControl) clips[p].getControl(FloatControl.Type.MASTER_GAIN);
			fc.setValue(currentVolume);
			
			clips[p].setFramePosition(0);
			clips[p].start();
			p++;

			if (p >= count)
				p = 0;
		}

		public void loop() {

			if (clips == null)
				return;

			FloatControl fc = (FloatControl) clips[p].getControl(FloatControl.Type.MASTER_GAIN);
			fc.setValue(currentVolume);
			
			clips[p].loop(300);

		}

	}

	public static Clips musicBackground1 = load("Vitality.wav", 1, SoundType.MUSIC);
	public static Clips musicBackgroundAlchemy = load("Alchemy.wav", 1, SoundType.MUSIC);
	public static Clips hitBullet = load("Bullet.wav", 1, SoundType.SFX);
	public static Clips emptyGun = load("Emptygun.wav", 1, SoundType.SFX);
	public static Clips deathEnemy = load("Death.wav", 1, SoundType.SFX);

	public void changeVolume() {

	}

	private static Clips load(String name, int count, SoundType st) {

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			DataInputStream dis = new DataInputStream(Sound.class.getResourceAsStream("/" + name));

			byte[] buffer = new byte[1024];
			int read = 0;

			while ((read = dis.read(buffer)) >= 0) {
				baos.write(buffer, 0, read);
			}

			dis.close();
			byte[] data = baos.toByteArray();
			return new Clips(data, count, st);

		} catch (Exception e) {

			try {
				return new Clips(null, 0, st);
			} catch (Exception ee) {
				return null;
			}

		}
	}

}
