import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CurvePlotWindow extends JFrame {

    private final Image background;

    public CurvePlotWindow(){

        background = importImage("Backgrounds/classicBackground.png");

        // Main panel
        JPanel mainPanel = new JPanel();
        add(mainPanel);

        // Window
        setLocation(750,200);
        setSize(1000, 610);
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void paint(Graphics g){
        g.drawImage(background,0,0,getWidth(), getHeight(),null );
        g.setColor(Color.black);
        g.fillRect(0,0,200,200);
    }

    private Image importImage(String imagePath) {
        try{
            return ImageIO.read(getClass().getResourceAsStream(imagePath));
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
