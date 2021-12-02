import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/*
* Button with an press animation and a sound
* */

public class FancyButton extends JButton {
    //==================================================================================================================
    // Attributes
    //==================================================================================================================
    ImageIcon notPressed;
    ImageIcon pressed;
    boolean state; // true if pressed
    Sound sound;
    Boolean soundBothState;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================
    public FancyButton(String notPressedImgPath, String pressedImgPath, String soundPath, boolean soundBothState){
        this.notPressed = new ImageIcon(Objects.requireNonNull(importImage(notPressedImgPath)));
        this.pressed = new ImageIcon(Objects.requireNonNull(importImage(pressedImgPath)));
        this.state = false;
        this.sound = new Sound(soundPath);
        this.soundBothState = soundBothState;
        setOpaque(false);
        setBorderPainted(false);
    }

    //==================================================================================================================
    // Methods
    //==================================================================================================================
    private Image importImage(String imageName){
        try {
            return ImageIO.read(getClass().getResourceAsStream(imageName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setBounds(int x, int y, double size){
        super.setBounds(x,y,(int)(size*pressed.getIconWidth()), (int)(size*pressed.getIconHeight()));
        setImageSize();
        setIcon(notPressed);
    }

    private void setImageSize(){
        Image resizedPressed = pressed.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        Image resizedNotPressed = notPressed.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        pressed = new ImageIcon(resizedPressed);
        notPressed = new ImageIcon(resizedNotPressed);
    }

    public void setPressed(){
        setIcon(pressed);
        state = true;
        playSound();
    }
    public void setNotPressed(){
        setIcon(notPressed);
        state = false;
        if(soundBothState) playSound();
    }

    private void playSound(){
        sound.clip.setMicrosecondPosition(0);
        sound.clip.start();
    }

}