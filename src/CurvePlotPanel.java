import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CurvePlotPanel extends JPanel implements ActionListener {

    private final BufferedImage background;
    private int bckgrdx = 0;
    private Curve curve;
    Timer timer;

    public CurvePlotPanel(){
        timer = new Timer(41,this);
        timer.start();
        background = (BufferedImage) importImage("Backgrounds/classicBackground.png");
    }

    public void actionPerformed(ActionEvent e) {
        if(curve!=null){
            curve.displaceY(5);
            bckgrdx += 5;
            repaint();
        }
    }

    public void paintComponent(Graphics g){
        bckgrdx %= background.getWidth()-1; //-1 because 0 width is forbidden
        Image imgLeft = background.getSubimage(bckgrdx, 0, background.getWidth()- bckgrdx, background.getHeight());
        Image imgRight = background.getSubimage(0,0, bckgrdx,background.getHeight());
       g.drawImage(imgLeft, 0,0,getWidth()- bckgrdx, getHeight(),null);
       g.drawImage(imgRight,getWidth()- bckgrdx,0, bckgrdx, getHeight(),null);

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
