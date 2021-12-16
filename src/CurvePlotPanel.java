import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CurvePlotPanel extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
    //==================================================================================================================
    // Attributes
    //==================================================================================================================
    private final BufferedImage background;
    private int bckgrdx = 1;
    private Curve curve;
    private final Point click;
    private boolean clickedOnCurve;
    Timer timer;

    //==================================================================================================================
    // Constructor
    //==================================================================================================================
    public CurvePlotPanel(){
        timer = new Timer(41,this);
        timer.start();

        click = new Point(0,0);
        clickedOnCurve = false;

        background = (BufferedImage) importImage("Backgrounds/classicBackground.png");

        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    //==================================================================================================================
    // Action Performed
    //==================================================================================================================
    public void actionPerformed(ActionEvent e) {
        if(curve!=null && !clickedOnCurve){
            curve.move(0,5);
        }
        int ground = 476;
        if(curve!=null && curve.barycenter().y+curve.minDistance > ground){ // TODO calculate barycenter only once and then save it as an attribute
            curve.move(0, ground -(int)(curve.barycenter().y+curve.minDistance));
        }
        repaint();
    }

    //==================================================================================================================
    // Paint
    //==================================================================================================================
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

    //==================================================================================================================
    // Key Listener interface
    //==================================================================================================================
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
        click.x = e.getX();
        click.y = e.getY();

        if(curve.isTouching(click)){
            clickedOnCurve = true;
            curve.color = Color.red;
            moveCurveToClick(click);
        }
    }
    public void mouseReleased(MouseEvent e) {
        clickedOnCurve = false;
        curve.color = curve.iniColor;
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }

    //--------------------------------------------------------------------------------------------------------------
    // Mouse Motion Listener
    //--------------------------------------------------------------------------------------------------------------
    public void mouseDragged(MouseEvent e) {
        click.x = e.getX();
        click.y = e.getY();

        if(clickedOnCurve){
            moveCurveToClick(click);
        }
    }
    public void mouseMoved(MouseEvent e) {
    }

    //==================================================================================================================
    // Methods
    //==================================================================================================================
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

    private void moveCurveToClick(Point p){
        int dx = (int)(p.x - curve.barycenter().x);
        int dy = (int)(p.y - curve.barycenter().y);
        curve.move(dx,dy);
    }


    // Getters
    public int getLastClickX(){
        return (int) click.x;
    }
    public int getLastClickY(){
        return (int) click.y;
    }
}
