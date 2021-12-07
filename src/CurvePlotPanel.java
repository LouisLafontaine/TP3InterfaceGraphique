import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CurvePlotPanel extends JPanel {

    private final Image background;
    private Curve curve;

    public CurvePlotPanel(){
        background = importImage("Backgrounds/classicBackground.png");
    }

    public void paintComponent(Graphics g){
        g.drawImage(background,0,0,getWidth(), getHeight(),null );
        if(curve!=null){
            curve.draw(g);
        }
    }

    private Image importImage(String imagePath) {
        try{
            return ImageIO.read(getClass().getResourceAsStream(imagePath));
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void selectCurve(Curve c){
        this.curve = c;
        repaint();
    }
}
