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
    ImageIcon notPressedIcon;
    ImageIcon pressedIcon;
    boolean isPressed; // true if pressed
    Sound sound;
    Boolean soundBothState;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================
    public FancyButton(String notPressedImgPath, String pressedImgPath, String soundPath, boolean soundBothState){
        this.notPressedIcon = new ImageIcon(Objects.requireNonNull(importImage(notPressedImgPath)));
        this.pressedIcon = new ImageIcon(Objects.requireNonNull(importImage(pressedImgPath)));
        this.isPressed = false;
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
        super.setBounds(x,y,(int)(size* pressedIcon.getIconWidth()), (int)(size* pressedIcon.getIconHeight()));
        setImageSize();
        setIcon(notPressedIcon);
    }

    private void setImageSize(){
        Image resizedPressed = pressedIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        Image resizedNotPressed = notPressedIcon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        pressedIcon = new ImageIcon(resizedPressed);
        notPressedIcon = new ImageIcon(resizedNotPressed);
    }

    public void setPressed(){
        setIcon(pressedIcon);
        isPressed = true;
        playSound();
    }
    public void setNotPressed(){
        setIcon(notPressedIcon);
        isPressed = false;
        if(soundBothState) playSound();
    }

    private void playSound(){
        sound.clip.setMicrosecondPosition(0);
        sound.clip.start();
    }

}