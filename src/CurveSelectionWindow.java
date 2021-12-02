import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;

/**
 * La fenêtre principale pour sélectionner les courbes à étudier
 */
 

public class CurveSelectionWindow extends JFrame implements ActionListener, MouseListener {
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

	//==================================================================================================================
	// Constructors
	//==================================================================================================================
	public CurveSelectionWindow(Curve[] aCurveArray){
		super("GUI Curves");

		this.curveArray = aCurveArray;
		this.music = new Sound("pokemon.wav");
		music.clip.loop(Clip.LOOP_CONTINUOUSLY);

		//--------------------------------------------------------------------------------------------------------------
		// Top panel
		//--------------------------------------------------------------------------------------------------------------
		ImagePanel topPanel = new ImagePanel("topPanel.png");
		topPanel.setLayout(null);
		topPanel.setBounds(20,20,360,140);
		topPanel.setOpaque(false);

		// Label texte enter a number
		enterNumber_Label = new ImageLabel("enterANumber.png");
		enterNumber_Label.setBounds(20,20,2);
		enterNumber_Label.setOpaque(false);
		enterNumber_Label.addMouseListener(this);
		topPanel.add(enterNumber_Label);

		// Text area to input curve number
		numInput_textField = new JTextField("");
		numInput_textField.setBounds(160,70,40,40);
		numInput_textField.setFont(importFont("pixelFont.ttf",35));
		numInput_textField.setHorizontalAlignment(JTextField.CENTER);
		topPanel.add(numInput_textField);

		// Button for volume on off
		volume_button = new FancyButton("speakerOn.png", "speakerOff.png", "pressSound.wav", true);
		volume_button.setBounds(25,65,1.5);
		volume_button.setOpaque(false);
		volume_button.setBorderPainted(false);
		volume_button.addActionListener(this);
		topPanel.add(volume_button);

		//--------------------------------------------------------------------------------------------------------------
		// Bottom panel
		//--------------------------------------------------------------------------------------------------------------
		ImagePanel bottomPanel = new ImagePanel("bottomPanel.png");
		bottomPanel.setLayout(null);
		bottomPanel.setBounds(20, 180, 360, 390);
		bottomPanel.setOpaque(false);

		// display button
		//--------------------------------------------------------------------------------------------------------------
		display_button = new FancyButton("displayButton.png", "displayButtonPressed.png", "pressSound.wav", false);
		display_button.setBounds(30, 10, 3);
		display_button.setBorderPainted(false);
		display_button.setOpaque(false);
		display_button.addActionListener(this);
		display_button.addMouseListener(this);
		bottomPanel.add(display_button);

		// Erase button
		erase_button = new FancyButton("eraseButton.png", "eraseButtonPressed.png", "crunch.wav", false);
		erase_button.setBounds(210,10,3);
		erase_button.setBorderPainted(false);
		erase_button.setOpaque(false);
		erase_button.addActionListener(this);
		erase_button.addMouseListener(this);
		bottomPanel.add(erase_button);


		// Information text area
		curveInfo_textArea = new JTextArea("");
		curveInfo_textArea.setLineWrap(true);
		curveInfo_textArea.setFont(importFont("pixelFont.ttf",15));

		// Information scroll pane
		JScrollPane curveInfo_scrollPane = new JScrollPane(curveInfo_textArea);
		curveInfo_scrollPane.setBounds(20,90,320,280);
		bottomPanel.add(curveInfo_scrollPane);

		//--------------------------------------------------------------------------------------------------------------
		// Main Panel
		//--------------------------------------------------------------------------------------------------------------
		ImagePanel mainPanel = new ImagePanel("grass.png");
		mainPanel.setLayout(null);
		mainPanel.setOpaque(false);
		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);

		//--------------------------------------------------------------------------------------------------------------
		// Window
		//--------------------------------------------------------------------------------------------------------------
		add(mainPanel);
		setLocation(300, 200);
		setSize(400, 610);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//==================================================================================================================
	// Drawing
	//==================================================================================================================
	public void paint(Graphics g){
		super.paint(g);
	}

	//==================================================================================================================
	// Interaction
	//==================================================================================================================
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == display_button){
			try{
				int input = Integer.parseInt(numInput_textField.getText());
				curveInfo_textArea.append(input +" - "+ curveArray[input-1].toString() +"\n\n");
				numInput_textField.setText(null);
			}catch (Exception exception){
				Sound errorSound = new Sound("crunch.wav");
				errorSound.clip.start();
				String message = "Enter a number between 1 and 5 !\nYou filth ! Don't you know how to read ?!";
				JOptionPane.showMessageDialog(this,message);
				numInput_textField.setText(null);
			}
		}
		if(e.getSource() == volume_button) {
			if(volume_button.state){
				volume_button.setNotPressed();
				music.clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else{
				volume_button.setPressed();
				music.clip.stop();
			}
		}
		if(e.getSource() == erase_button){
			curveInfo_textArea.setText(null);
			numInput_textField.setText(null);
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
	// Methods
	//==================================================================================================================
	private Font importFont(String filename, int size) {
		Font maFont;
		try {
			InputStream fontstream = getClass().getResourceAsStream(filename);
			maFont  = Font.createFont(Font.TRUETYPE_FONT, fontstream);
			return maFont.deriveFont(Font.PLAIN,(float)size);
		} catch (Exception e) {
			System.out.println("problème Police PixelFont");
			e.printStackTrace();
			return new Font("Arial", Font.BOLD, size);
		}
	}
}