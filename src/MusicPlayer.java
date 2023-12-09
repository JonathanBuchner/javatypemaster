
/**
 * MusicPlayer.java
 * 
 * This class will manage the music.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {
    private final String intro = "../assets/intro.wav";

    /**
     * Default constructor.
     */
    public MusicPlayer() {}

    /**
     * Play background music.
     */
    public void playIntro() {
        playSound(intro);
    }

    /**
     * Play a sound.
     * 
     * @param soundFileName The name of the sound file.
     * 
     * Note: I looked this up as it pretty straight forward.  The sound is from
     * https://www.playonloop.com/freebies-download/ and is called "Ninja Panda."
     */
    private void playSound(String soundFileName) {
        try {
            // Open an audio input stream.
            File soundFile = FileHelper.getFile(soundFileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        }
        catch (Exception e) {
                System.out.println("Failed to play sound file: " + soundFileName);
                System.out.println(e.getMessage());
        }
    }
}
