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
	// textfield for the filepath
	private JTextField filePathField = new JTextField();
	// button for choosing a file
	private FileChooserButton fileChooserButton = new FileChooserButton();
	// textfield for the output folder name
	private JTextField folderNameField = new JTextField();
	// label for the output folder name
	private JLabel folderNameLabel = new JLabel();
	// button for starting the conversion
	private StartButton startButton = new StartButton();
	// button for showing the result folder
	private ShowResultButton showResultButton = new ShowResultButton();

	/**
	 * Constructor of the GUI class.
	 * 
	 * @param title
	 *            the title of the GUI JFrame
	 */
	public GUI(String title) {
		super();
		setDefaults(title);
		addComponents();
		setVisible(true);
	}

	/**
	 * Sets the default parameters of the GUI JFrame.
	 * 
	 * @param title
	 *            the title of the GUI JFrame
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
			// update on removing text from the textfield
			@Override
			public void removeUpdate(DocumentEvent e) {
				// check if user can start the conversion
				if (!getFilePathField().getText().equals(new String(""))
						&& !getFolderNameField().getText().equals(new String(""))) {
					// update gui elements if filepath and output folder name
					// are given
					Main.getGui().getStartButton().setEnabled(true);
					Main.getGui().getStartButton().setToolTipText("Start the conversion process");
				} else {
					// add hint for user to enter an output folder name
					Main.getGui().getStartButton().setEnabled(false);
					Main.getGui().getStartButton().setToolTipText("Insert a output folder name");
				}
			}

			// update on inserting text from the textfield
			@Override
			public void insertUpdate(DocumentEvent e) {
				// see above
				if (!getFilePathField().getText().equals(new String(""))
						&& !getFolderNameField().getText().equals(new String(""))) {
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
	 * Adds all interface components of the GUI.
	 */
	private void addComponents() {
		getPane().add(filePathField);
		getPane().add(fileChooserButton);
		getPane().add(folderNameLabel);
		getPane().add(folderNameField);
		getPane().add(startButton);
		getPane().add(showResultButton);

		pack();

		// set initial focus on filechooserbutton
		fileChooserButton.grabFocus();
	}

	// getters - setters
	/**
	 * Returns the JTextField for the filepath.
	 * 
	 * @return JTextField
	 */
	public JTextField getFilePathField() {
		return filePathField;
	}

	/**
	 * Sets the JTextField for the filepath.
	 * 
	 * @param filePathField
	 *            the JTextField to be set for the filepath
	 */
	public void setFilePathField(JTextField filePathField) {
		this.filePathField = filePathField;
	}

	/**
	 * Returns the FileChooserButton of the GUI.
	 * 
	 * @return FileChooserButton of the GUI
	 */
	public FileChooserButton getFileChooserButton() {
		return fileChooserButton;
	}

	/**
	 * Sets the FileChooserButton of the GUI.
	 * 
	 * @param fileChooserButton
	 *            the FileChooserButton to be set for the GUI
	 */
	public void setFileChooserButton(FileChooserButton fileChooserButton) {
		this.fileChooserButton = fileChooserButton;
	}

	/**
	 * Returns the JTextField for the output folder name.
	 * 
	 * @return JTextField for output folder name
	 */
	public JTextField getFolderNameField() {
		return folderNameField;
	}

	/**
	 * Sets the JTextField for the output folder name.
	 * 
	 * @param folderNameField
	 *            the JTextField to be set for the output folder name
	 */
	public void setFolderNameField(JTextField folderNameField) {
		this.folderNameField = folderNameField;
	}

	/**
	 * Returns the StartButton of the GUI.
	 * 
	 * @return StartButton of GUI
	 */
	public StartButton getStartButton() {
		return startButton;
	}

	/**
	 * Sets the StartButton of the GUI.
	 * 
	 * @param startButton
	 *            the StartButton to be set for the GUI
	 */
	public void setStartButton(StartButton startButton) {
		this.startButton = startButton;
	}

	/**
	 * Returns the ShowResultButton of the GUI.
	 * 
	 * @return ShowResultButton of the GUI
	 */
	public ShowResultButton getShowResultButton() {
		return showResultButton;
	}

	/**
	 * Sets the ShowResultButton of the GUI.
	 * 
	 * @param showResultButton
	 *            the ShowResultButton to be set for the GUI
	 */
	public void setShowResultButton(ShowResultButton showResultButton) {
		this.showResultButton = showResultButton;
	}

	/**
	 * Returns the ContentPane of the JFrame.
	 * 
	 * @return ContentPane of the GUI JFrame
	 */
	private Container getPane() {
		return this.getContentPane();
	}

}