package bvp.controllers;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GameSound {

    public static void bulletFiredSound() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(GameSound.class.getResource("/sounds/bullet_whizzing.wav"));

            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void bulletHitSound() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(GameSound.class.getResource("/sounds/silencer_shot.wav"));

            Clip clip = AudioSystem.getClip();

            clip.open(ais);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Shooter_Bee_Collide_Sound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(GameSound.class.getResource("/sounds/blast.wav"));

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
