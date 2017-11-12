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

	private String resultFile = "";
	
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
		setText("Show the result file");
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
					if(!Desktop.isDesktopSupported()){
			            System.out.println("Desktop is not supported");
			            return;
			        }
			        
			        Desktop desktop = Desktop.getDesktop();
			        resultFile += ".scad";
			        if(new File(resultFile).exists())
						try {
							System.out.println("exists" + new File(resultFile).getAbsolutePath());
							desktop.open(new File(resultFile));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					System.out.println("ShowResultButton received command: show." + resultFile);
				} else {
					System.out.println("ShowResultButton received unknown command.");
				}
			}
		});
	}

	public String getResultFile() {
		return resultFile;
	}

	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}

}