package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import main.Main;

@SuppressWarnings("serial")
public class FileChooserButton extends JButton {
	
	private String filePath = "";

	/**
	 * Creates a new FileChooserButton object.
	 */
	public FileChooserButton() {
		super();
		setDefaults();
		addFunctionality();
	}
	
	/**
	 * Sets the default parameters for the button. 
	 */
	private void setDefaults() {
		setText("Choose a file...");
		setActionCommand("chooseFile");
		setVisible(true);
	}
	
	/**
	 * Adds the ActionListeners to the button.
	 */
	private void addFunctionality() {
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ("chooseFile".equals(e.getActionCommand())) {
					JFileChooser fc = new JFileChooser();
					// FileFilter for .dxf files
					fc.setFileFilter(new FileFilter() {
			        	public String getDescription() {
			            	return "DXF Files (*.dxf)";
			        	}
			        	public boolean accept(File f) {
			        		if (f.isDirectory()) {
			        			return true;
			        		} else {
			        			String filename = f.getName().toLowerCase();
			        			return filename.endsWith(".dxf");
			        		}
			        	}
					});
					
					int returnVal = fc.showOpenDialog(Main.getGui());
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            Main.getGui().getFilePathField().setText(file.getAbsolutePath());
			            Main.getGui().getStartButton().setEnabled(true);
			            Main.getGui().getStartButton().setToolTipText("Start the conversion process.");
			            filePath = file.getAbsolutePath();
			            System.out.println("File chosen: " + file.getAbsolutePath());
			        } else {
			            System.out.println("File choosing canceled");
			        }
					
					System.out.println("FileChooserButton received command: chooseFile.");
				} else {
					System.out.println("FileChooserButton received unknown command.");
				}
			}
		});
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}