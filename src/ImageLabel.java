import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
* Label with an image
* the label keeps the proportions of the image
* */


public class ImageLabel extends JLabel{
    //==================================================================================================================
    // Attributes
    //==================================================================================================================
    ImageIcon icon;

    //==================================================================================================================
    // Constructors
    //==================================================================================================================
    public ImageLabel(String imageName){
        try {
           icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(imageName)));
           setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOpaque(false);
    }

    //==================================================================================================================
    // Methods
    //==================================================================================================================
    public void setBounds(int x, int y, int size){
        super.setBounds(x,y,size*icon.getIconWidth(), size*icon.getIconHeight());
        setImageSize();
        setIcon(icon);
    }

    private void setImageSize(){
        icon = new ImageIcon(icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH));
    }
}