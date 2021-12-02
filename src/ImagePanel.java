import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImagePanel extends JPanel {
    BufferedImage background;

    public ImagePanel(String backgroundName){
        try{
            background = ImageIO.read(getClass().getResourceAsStream(backgroundName));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0,0, this.getWidth(), this.getHeight(), null);
    }
}