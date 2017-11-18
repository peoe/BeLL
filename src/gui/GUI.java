package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.Main;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JTextField filePathField = new JTextField();
	private FileChooserButton fileChooserButton = new FileChooserButton();
	private JTextField folderNameField = new JTextField();
	private JLabel folderNameLabel = new JLabel();
	private StartButton startButton = new StartButton();
	private ShowResultButton showResultButton = new ShowResultButton();

	// constructor
	/**
	 * Creates a new GUI object.
	 */
	public GUI(String title) {
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
		p.setPreferredSize(new Dimension(480, 100));
		setContentPane(p);
		// filePathField
		filePathField.setPreferredSize(new Dimension(350, 27));
		filePathField.setToolTipText("The absolute filepath");
		// folderNameLabel
		folderNameLabel.setText("Name of the output folder:");
		// folderNameField
		folderNameField.setText("");
		folderNameField.setPreferredSize(new Dimension(322, 27));
		folderNameField.setToolTipText("The name of the output folder");
		folderNameField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (!getFilePathField().getText().equals(new String("")) && !getFolderNameField().getText().equals(new String(""))) {
	            	Main.getGui().getStartButton().setEnabled(true);
	            	Main.getGui().getStartButton().setToolTipText("Start the conversion process");
	            } else {
	            	Main.getGui().getStartButton().setEnabled(false);
	            	Main.getGui().getStartButton().setToolTipText("Insert a output folder name");
	            }
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (!getFilePathField().getText().equals(new String("")) && !getFolderNameField().getText().equals(new String(""))) {
	            	Main.getGui().getStartButton().setEnabled(true);
	            	Main.getGui().getStartButton().setToolTipText("Start the conversion process");
	            } else {
	            	Main.getGui().getStartButton().setEnabled(false);
	            	Main.getGui().getStartButton().setToolTipText("Insert a output folder name");
	            }
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}

	/**
	 * Returns the ContentPane of the JFrame.
	 * 
	 * @return the frames ContentPane
	 */
	private Container getPane() {
		return this.getContentPane();
	}

	/**
	 * Adds all interface components for the GUI.
	 */
	private void addComponents() {
		getPane().add(filePathField);
		getPane().add(fileChooserButton);
		getPane().add(folderNameLabel);
		getPane().add(folderNameField);
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

	public JTextField getFolderNameField() {
		return folderNameField;
	}

	public void setFolderNameField(JTextField folderNameField) {
		this.folderNameField = folderNameField;
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