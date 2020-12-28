package bvp.controllers;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameSound {

	public static void bulletFiredSound(){
		try {
			File sound = new File("src//resources//sounds//bullet_whizzing.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(sound);

			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		} catch(Exception e){
			e.printStackTrace();
		}
	}


	public static void bulletHitSound(){
		try {
			File soundHit = new File("src//resources//sounds//silencer_shot.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundHit);

			Clip clip = AudioSystem.getClip();

			clip.open(ais);
			clip.start();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void Shooter_Bee_Collide_Sound(){
		try {
			File sound = new File("src//resources//sounds//blast.wav");
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(sound);

			Clip clip = AudioSystem.getClip();

			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
