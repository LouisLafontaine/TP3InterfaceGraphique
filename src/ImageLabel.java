import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageLabel extends JLabel{
    ImageIcon icon;

    public ImageLabel(String imageName){
        try {
           icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(imageName)));
           setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBounds(int x, int y, int size){
        super.setBounds(x,y,size*icon.getIconWidth(), size*icon.getIconHeight());
        setImageSize();
        setIcon(icon);
    }

    private void setImageSize(){
        icon = new ImageIcon(icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH));
    }
}