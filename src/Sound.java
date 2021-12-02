import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sound {
    // Attributs
    //======================================================================
    Clip clip;
    AudioInputStream audioInputStream;

    // Constructeur
    //======================================================================
    public Sound(String fileName){
        try {
            InputStream audioSrc = getClass().getResourceAsStream(fileName);
            BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc);
            audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}