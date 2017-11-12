package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	
	private JTextField filePathField = new JTextField();
	private FileChooserButton fileChooserButton = new FileChooserButton();
	private StartButton startButton = new StartButton();
	private ShowResultButton showResultButton = new ShowResultButton();
	
	// constructor
	/**
	 * Creates a new GUI object.
	 */
	public GUI (String title) {
		super();
		setDefaults(title);
		addComponents();
		setVisible(true);
	}
	
	/**
	 * Sets the default parameters for the JFrame.
	 */
	private void setDefaults(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// contentPane
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.setPreferredSize(new Dimension(480, 70));
		setContentPane(p);
		// filePathField
		filePathField.setPreferredSize(new Dimension(350, 27));
		filePathField.setToolTipText("The absolute filepath");
	}
	
	/**
	 * Returns the ContentPane of the JFrame.
	 * @return the frames ContentPane
	 */
	private Container getPane() {
		return this.getContentPane();
	}
	
	/**
	 * Adds all interface components for the GUI.
	 */
	private void addComponents() {
		// TODO Button for choosing File
		// TODO button for starting conversion
		
		getPane().add(filePathField);
		getPane().add(fileChooserButton);
		getPane().add(startButton);
		getPane().add(showResultButton);
		
		pack();
		
		// fileChooserButton
		fileChooserButton.grabFocus();
	}

	// getters - setters
	public JTextField getFilePathField() {
		return filePathField;
	}

	public void setFilePathField(JTextField filePathField) {
		this.filePathField = filePathField;
	}

	public FileChooserButton getFileChooserButton() {
		return fileChooserButton;
	}

	public void setFileChooserButton(FileChooserButton fileChooserButton) {
		this.fileChooserButton = fileChooserButton;
	}

	public StartButton getStartButton() {
		return startButton;
	}

	public void setStartButton(StartButton startButton) {
		this.startButton = startButton;
	}

	public ShowResultButton getShowResultButton() {
		return showResultButton;
	}

	public void setShowResultButton(ShowResultButton showResultButton) {
		this.showResultButton = showResultButton;
	}

}