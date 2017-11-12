package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import main.Main;

@SuppressWarnings("serial")
public class StartButton extends JButton {

	/**
	 * Creates a new StartButton object.
	 */
	public StartButton() {
		super();
		setDefaults();
		addFunctionality();
	}

	/**
	 * Sets the default parameters for the button.
	 */
	private void setDefaults() {
		setText("Start the conversion");
		setActionCommand("start");
		setPreferredSize(new Dimension(233, 27));
		setToolTipText("Pick a file to convert first!");
		setEnabled(false);
		setVisible(true);
	}

	/**
	 * Adds the ActionListeners to the button.
	 */
	private void addFunctionality() {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ("start".equals(e.getActionCommand())) {
					setEnabled(false);
					Main.startConversion(Main.getGui().getFileChooserButton().getFilePath(), "result");
					System.out.println("StartButton received command: start.");
				} else {
					System.out.println("StartButton received unknown command.");
				}
			}
		});
	}

}