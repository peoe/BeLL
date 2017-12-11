package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import main.Main;

@SuppressWarnings("serial")
public class StartButton extends JButton {

	/**
	 * Constructor of the StartButton class.
	 */
	public StartButton() {
		super();
		setDefaults();
		addFunctionality();
	}

	/**
	 * Sets the default parameters for the StartButton.
	 */
	private void setDefaults() {
		setText("Start the conversion");
		setActionCommand("start");
		// sets width and height
		setPreferredSize(new Dimension(233, 27));
		setToolTipText("No file picked for conversion");
		// disable from the start
		setEnabled(false);
		// make sure disabled button can be seen
		setVisible(true);
	}

	/**
	 * Adds the ActionListeners to the StartButton.
	 */
	private void addFunctionality() {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ("start".equals(e.getActionCommand())) {
					// disable so user cannot start another conversion
					setEnabled(false);
					// start the conversion using parameters from gui
					Main.startConversion(Main.getGui().getFileChooserButton().getFilePath(),
							Main.getGui().getFolderNameField().getText());
					System.out.println("StartButton received command: start.");

					// disable start button before choosing a new file
					Main.getGui().getStartButton().setEnabled(false);
					Main.getGui().getStartButton().setToolTipText("Please choose a different file!");

					// enable showresultbutton
					Main.getGui().getShowResultButton().setToolTipText("Show the result file of the conversion.");
					Main.getGui().getShowResultButton().setEnabled(true);

					// reset output folder name
					Main.getGui().getFolderNameField().setText("");
				} else {
					System.out.println("StartButton received unknown command.");
				}
			}
		});
	}

}