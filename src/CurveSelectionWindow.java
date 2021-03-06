import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;


/**
 * Main window is used to select curves to study
 */
 

public class CurveSelectionWindow extends JFrame implements ActionListener, MouseListener, KeyListener {
	//==================================================================================================================
	// Attributes
	//==================================================================================================================
	Curve[] curveArray;
	FancyButton display_button;
	FancyButton erase_button;
	FancyButton volume_button;
	JTextField numInput_textField;
	JTextArea curveInfo_textArea;
	ImageLabel enterNumber_Label;
	Sound music;
	CurvePlotWindow curvePlotWindow;

	//==================================================================================================================
	// Constructors
	//==================================================================================================================
	public CurveSelectionWindow(Curve[] aCurveArray){
		super("GUI Curves");

		this.curveArray = aCurveArray;

		curvePlotWindow = new CurvePlotWindow();


		//--------------------------------------------------------------------------------------------------------------
		// Top panel
		//--------------------------------------------------------------------------------------------------------------
		ImagePanel topPanel = new ImagePanel("PanelBackgrounds/topPanel.png");
		topPanel.setLayout(null);
		topPanel.setBounds(20,20,360,140);

		// Label texte enter a number
		enterNumber_Label = new ImageLabel("LabelBackgrounds/enterANumber.png");
		enterNumber_Label.setBounds(20,20,2);
		enterNumber_Label.addMouseListener(this);
		topPanel.add(enterNumber_Label);

		// Text area to input curve number
		numInput_textField = new JTextField("");
		numInput_textField.setBounds(160,70,40,40);
		numInput_textField.setFont(importFont("Fonts/pixelFont.ttf",35));
		numInput_textField.setHorizontalAlignment(JTextField.CENTER);
		numInput_textField.addKeyListener(this);
		topPanel.add(numInput_textField);

		// Button for volume on off
		volume_button = new FancyButton("Buttons/speakerOn.png", "Buttons/speakerOff.png", "Sounds/pressSound.wav", true);
		volume_button.setBounds(25,65,1.5);
		volume_button.addActionListener(this);
		topPanel.add(volume_button);

		// Music
		this.music = new Sound("Sounds/pokemon.wav");
//		music.clip.loop(Clip.LOOP_CONTINUOUSLY);

		//--------------------------------------------------------------------------------------------------------------
		// Bottom panel
		//--------------------------------------------------------------------------------------------------------------
		ImagePanel bottomPanel = new ImagePanel("PanelBackgrounds/bottomPanel.png");
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(20, 180, 360, 390);

		// display button
		//--------------------------------------------------------------------------------------------------------------
		display_button = new FancyButton("Buttons/displayButton.png", "Buttons/displayButtonPressed.png", "Sounds/pressSound.wav", false);
		display_button.setBounds(30, 10, 3);
		display_button.addActionListener(this);
		display_button.addMouseListener(this);
		bottomPanel.add(display_button);

		// Erase button
		erase_button = new FancyButton("Buttons/eraseButton.png", "Buttons/eraseButtonPressed.png", "Sounds/crunch.wav", false);
		erase_button.setBounds(210,10,3);
		erase_button.addActionListener(this);
		erase_button.addMouseListener(this);
		bottomPanel.add(erase_button);


		// Information text area
		curveInfo_textArea = new JTextArea("");
		curveInfo_textArea.setLineWrap(true);
		curveInfo_textArea.setFont(importFont("Fonts/pixelFont.ttf",15));

		// Information scroll pane
		JScrollPane curveInfo_scrollPane = new JScrollPane(curveInfo_textArea);
		curveInfo_scrollPane.setBounds(20,90,320,280);
		bottomPanel.add(curveInfo_scrollPane);

		//--------------------------------------------------------------------------------------------------------------
		// Main Panel
		//--------------------------------------------------------------------------------------------------------------
		ImagePanel mainPanel = new ImagePanel("Backgrounds/grass.png");
		mainPanel.setLayout(null);
		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);

		//--------------------------------------------------------------------------------------------------------------
		// Window
		//--------------------------------------------------------------------------------------------------------------
		add(mainPanel);
		setLocation(300, 200);
		setSize(400, 610);
		setResizable(false);
		setAlwaysOnTop(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//==================================================================================================================
	// Action Performed
	//==================================================================================================================
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == display_button){
			try{
				display_buttonActions();
			}catch (Exception exception){
				playErrorSound();
				showErrorWindow();
				numInput_textField.setText(null);
			}
		}
		if(e.getSource() == volume_button) {
			if(volume_button.isPressed){
				volume_button.setNotPressed();
				music.clip.loop(
			} else{
				volume_button.setPressed();
				music.clip.stop();
			}
		}
		if(e.getSource() == erase_button){
			curveInfo_textArea.setText(null);
			numInput_textField.setText(null);
			curvePlotWindow.curvePlotPanel.setCurveNull();
		}
	}

	//==================================================================================================================
	// Mouse Interface
	//==================================================================================================================
	public void mouseClicked(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
		if(e.getSource() == display_button) display_button.setPressed();
		if(e.getSource() == erase_button) erase_button.setPressed();
	}
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == display_button) display_button.setNotPressed();
		if(e.getSource() == erase_button) erase_button.setNotPressed();
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}

	//==================================================================================================================
	// Keyboard interface
	//==================================================================================================================
	public void keyTyped(KeyEvent e) { }
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			display_buttonActions();
		}
	}
	public void keyReleased(KeyEvent e) { }

	//==================================================================================================================
	// Methods
	//==================================================================================================================
	private Font importFont(String filename, int size) {
		Font maFont;
		try {
			InputStream fontstream = getClass().getResourceAsStream(filename);
			maFont  = Font.createFont(Font.TRUETYPE_FONT, fontstream);
			return maFont.deriveFont(Font.PLAIN,(float)size);
		} catch (Exception e) {
			System.out.println("probl??me Police PixelFont");
			e.printStackTrace();
			return new Font("Arial", Font.BOLD, size);
		}
	}

	private void playErrorSound() {
		Sound errorSound = new Sound("Sounds/crunch.wav");
		errorSound.clip.start();
	}

	private void showErrorWindow(){
		String message = """
				Enter a number between 1 and 5 !
				You filth ! Don't you know how to read ?!

				Oh you didn't mean to do it...my bad...Sorry for being a bit aggressive
				I couldn't take my cup of coffee this morning so I am a bit on edge.
				You know what, I will forgive you, so just click OK and get on with it.
				But don't I ever see you make this mistake again... !""";
		JOptionPane.showMessageDialog(this,message);
	}

	private void display_buttonActions() {
		int input = Integer.parseInt(numInput_textField.getText());
		curveInfo_textArea.append(input +" - "+ curveArray[input-1].toString() +"\n\n");
		numInput_textField.setText(null);
		curvePlotWindow.curvePlotPanel.selectCurve(curveArray[input-1]);
		if(!curvePlotWindow.isVisible()) {
			curvePlotWindow.elapsedTime = 0;
			curvePlotWindow.setVisible(true);
		}
	}
}