import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CurvePlotPanel extends JPanel implements ActionListener {

    private final Image background;
    private Curve curve;
    Timer timer;
    private int dy; // Y displacement of a curve

    public CurvePlotPanel(){
        timer = new Timer(41,this);
        timer.start();
        background = importImage("Backgrounds/classicBackground.png");
    }

    public void actionPerformed(ActionEvent e) {
        if(curve!=null){
            curve.displaceY(5);
            repaint();
        }
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

    public void setCurveNull(){
        curve = null;
        repaint();
    }
}
