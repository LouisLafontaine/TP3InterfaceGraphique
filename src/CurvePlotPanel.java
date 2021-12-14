import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CurvePlotPanel extends JPanel implements ActionListener, KeyListener, MouseListener {

    private final BufferedImage background;
    private int bckgrdx = 1;
    private Curve curve;
    private final Point lastClick;
    Timer timer;

    public CurvePlotPanel(){
        timer = new Timer(41,this);
        timer.start();

        lastClick = new Point(0,0);

        background = (BufferedImage) importImage("Backgrounds/classicBackground.png");

        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(curve!=null){
            curve.move(0,2);
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
        c.reset();
        repaint();
    }

    public void setCurveNull(){
        curve = null;
        repaint();
    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            bckgrdx += 1;
            repaint();
        }

        if(e.getKeyCode() == KeyEvent.VK_B) curve.color = Color.blue;
        if(e.getKeyCode() == KeyEvent.VK_R) curve.color = Color.red;

    }

    public void keyReleased(KeyEvent e) {

    }

    //==================================================================================================================
    // Mouse Listener interface
    //==================================================================================================================
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
        lastClick.x = e.getX();
        lastClick.y = e.getY();
        curve.color = Color.red;

        int dx = (int)(lastClick.x - curve.barycenter().x);
        int dy = (int)(lastClick.y - curve.barycenter().y);
        curve.move(dx,dy);

    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }


    // Getters

    public int getLastClickX(){
        return (int)lastClick.x;
    }
    public int getLastClickY(){
        return (int)lastClick.y;
    }
}
