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

	private String resultFolder = "";
	
	/**
	 * Creates a new StartButton object.
	 */
	public ShowResultButton() {
		super();
		setDefaults();
		addFunctionality();
	}
	
	/**
	 * Sets the default parameters for the button. 
	 */
	private void setDefaults() {
		setText("Show the result fodler");
		setActionCommand("show");
		setPreferredSize(new Dimension(233, 27));
		setEnabled(false);
		setToolTipText("The conversion is not done yet.");
		setVisible(true);
	}
	
	/**
	 * Adds the ActionListeners to the button.
	 */
	private void addFunctionality() {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ("show".equals(e.getActionCommand())) {
					try {
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

	public String getResultFodler() {
		return resultFolder;
	}

	public void setResultFolder(String resultFolder) {
		this.resultFolder = resultFolder;
	}

}