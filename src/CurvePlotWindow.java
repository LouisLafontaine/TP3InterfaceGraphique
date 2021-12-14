import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurvePlotWindow extends JFrame implements ActionListener {

    CurvePlotPanel curvePlotPanel;
    Timer timer;
    int elapsedTime = 0;

    public CurvePlotWindow(){

        timer = new Timer(1000,this);
        timer.start();

        // Main panel
        curvePlotPanel = new CurvePlotPanel();
        add(curvePlotPanel);



        // Window
        setLocation(750,200);
        setSize(1000, 610);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == timer){
            elapsedTime ++;
            int x = curvePlotPanel.getLastClickX();
            int y = curvePlotPanel.getLastClickY();
            setTitle("Time elapsed since start : " + elapsedTime + " seconds - last click : "+ x +", "+ y);
        }
    }
}
