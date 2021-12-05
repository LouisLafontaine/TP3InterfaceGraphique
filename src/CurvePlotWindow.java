import javax.swing.*;

public class CurvePlotWindow extends JFrame {

    public CurvePlotWindow(){

        // Main panel
        add(new CurvePlotPanel());

        // Window
        setLocation(750,200);
        setSize(1000, 610);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
