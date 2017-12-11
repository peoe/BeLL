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

	// filepath string
	private String filePath = "";

	/**
	 * Constructor of the FileChooserButton class.
	 */
	public FileChooserButton() {
		super();
		setDefaults();
		addFunctionality();
	}

	/**
	 * Sets the default parameters for the FileChooserButton.
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
				// when button is clicked
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

					// open jfilechooser
					int returnVal = fc.showOpenDialog(Main.getGui());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						// update gui element with filepath
						Main.getGui().getFilePathField().setText(file.getAbsolutePath());

						// determine if user can start conversion
						// file chosen and result folder name typed in -->
						// conversion ready
						if (!Main.getGui().getFolderNameField().getText().equals(new String(""))
								&& !Main.getGui().getFolderNameField().getText().equals(new String(""))) {
							// update gui elements
							Main.getGui().getStartButton().setEnabled(true);
							Main.getGui().getStartButton().setToolTipText("Start the conversion process");
						} else {
							// update hint for user to enter output folder name
							Main.getGui().getStartButton().setToolTipText("Insert a output folder name");
						}

						// update filepazh string
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

	// getters - setters
	/**
	 * Returns the filepath String of the FileChooserButton.
	 * 
	 * @return String of the filepath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the filepath String of the FileChooserButton.
	 * 
	 * @param filePath
	 *            the String to be set as filepath
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}