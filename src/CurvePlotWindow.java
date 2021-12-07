import javax.swing.*;

public class CurvePlotWindow extends JFrame {

    CurvePlotPanel curvePlotPanel;

    public CurvePlotWindow(){

        // Main panel
        curvePlotPanel = new CurvePlotPanel();
        add(curvePlotPanel);

        // Window
        setLocation(750,200);
        setSize(1000, 610);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
