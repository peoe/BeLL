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
		setToolTipText("No file picked for conversion");
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
					Main.startConversion(Main.getGui().getFileChooserButton().getFilePath(), Main.getGui().getFolderNameField().getText());
					System.out.println("StartButton received command: start.");
					
					// disable start button before choosing a new file
					Main.getGui().getStartButton().setEnabled(false);
					Main.getGui().getStartButton().setToolTipText("Please choose a different file!");
					Main.getGui().getShowResultButton().setToolTipText("Show the result file of the conversion.");
					Main.getGui().getShowResultButton().setEnabled(true);
					Main.getGui().getFolderNameField().setText("");
				} else {
					System.out.println("StartButton received unknown command.");
				}
			}
		});
	}

}