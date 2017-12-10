package gui;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ShowResultButton extends JButton {

	// string for the result folder name
	private String resultFolder = "";

	/**
	 * Constructor for the ShowResultButton class.
	 */
	public ShowResultButton() {
		super();
		setDefaults();
		addFunctionality();
	}

	/**
	 * Sets the default parameters for the ShowResultButton.
	 */
	private void setDefaults() {
		setText("Show the result fodler");
		setActionCommand("show");
		// set width and height
		setPreferredSize(new Dimension(233, 27));
		// disable from the start
		setEnabled(false);
		// add hint for the user
		setToolTipText("The conversion is not done yet.");
		// makes sure the disabled button can be seen
		setVisible(true);
	}

	/**
	 * Adds the ActionListeners to the ShowResultButton.
	 */
	private void addFunctionality() {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ("show".equals(e.getActionCommand())) {
					try {
						// open the result folder in explorer
						Desktop.getDesktop().open(new File(".\\" + resultFolder));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					System.out.println("ShowResultButton received unknown command.");
				}
			}
		});
	}

	// getters - setters
	/**
	 * Returns the String for the result folder name.
	 * 
	 * @return String of result folder name
	 */
	public String getResultFodler() {
		return resultFolder;
	}

	/**
	 * Sets the String for the result folder name.
	 * 
	 * @param resultFolder
	 *            the String to be set as result folder name
	 */
	public void setResultFolder(String resultFolder) {
		this.resultFolder = resultFolder;
	}

}