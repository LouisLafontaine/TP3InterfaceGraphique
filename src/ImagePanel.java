import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
* Panel with a background image
* */

public class ImagePanel extends JPanel {
    //==================================================================================================================
    // Attributes
    //==================================================================================================================
    BufferedImage background;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================
    public ImagePanel(String backgroundName){
        try{
            background = ImageIO.read(getClass().getResourceAsStream(backgroundName));
        }catch (IOException e){
            e.printStackTrace();
        }
        setOpaque(false);
    }

    //==================================================================================================================
    // Methods
    //==================================================================================================================
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, this.getWidth(), this.getHeight(), null);
    }
}