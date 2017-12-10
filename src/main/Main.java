package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.kabeja.parser.ParseException;

import gui.GUI;
import io.DXFReader;
import io.SCADFinder;
import io.STLConverter;
import io.ScadPrinter;
import render.*;
import render.objects.*;

public class Main {

	// gui for the program
	private static GUI gui;

	/**
	 * Main function of the program. Creates the GUI.
	 * 
	 * @param args
	 *            the arguments passed for the program
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		gui = new GUI("Convert model");
	}

	/**
	 * Starts the conversion of the model. Should be called from the
	 * StartButton.
	 * 
	 * @param targetFile
	 *            the File to be converted
	 */
	public static void startConversion(String targetFile, String folderName) {
		// setting up parameters for the modelling process
		Params p = new Params(0.25, 10.0, 2.0, 4.0, 4.0, 2.0, 75.0, 4.0, 4.0, 1.0, 6.0, 185.0, 153.0);

		try {
			// creating scadprocessor using targetfile and params object
			ScadProcessor proc = new ScadProcessor(DXFReader.getAutocadFile(targetFile), p);

			// for showing the result folder
			getGui().getShowResultButton().setResultFolder(folderName);

			// setting environment variables for command execution if OpenSCAD
			// cannot be found
			if (!new File(System.getenv("ProgramFiles") + "/OpenSCAD/openscad.exe").exists()) {
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(new FileNameExtensionFilter("Executables (.exe)", "exe"));

				// information for user
				JOptionPane.showMessageDialog(gui,
						"This application requires to have OpenSCAD installed. This program did not find it in: 'C:\\\\Program Files\\OpenSCAD\\'.\n"
								+ "If it is installed in another location use the following dialogue for linking to openscad.exe.",
						"OpenSCAD required for this application", JOptionPane.WARNING_MESSAGE);

				// get the path to the openscad.exe
				int returnVal = fc.showDialog(gui, "Link to openscad.exe");
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String path = fc.getSelectedFile().getAbsolutePath();
					// set path to the executable for stlconverter
					STLConverter.setOpenSCADLocation(path.substring(0, path.length() - 12));
				}
			} else {
				// normal action, if OpenSCAD is installed in assumed directory
				System.out.println("System variable for OpenSCAD not set, assuming standard location!");
				// set path to the executable for stlconverter
				String path = System.getenv("ProgramFiles") + "\\OpenSCAD\\openscad.exe";
				STLConverter.setOpenSCADLocation(path.substring(0, path.length() - 12));
			}

			// create the result folders
			createDirs(folderName);

			// printing the scads
			printSCAD(proc, folderName);

			// printing the stls
			printSTL(folderName);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Converts all .scad Files in the specified folder to .stl.
	 * 
	 * @param folderName
	 *            the name of the output folder
	 */
	private static void printSTL(String folderName) {
		File[] fs = SCADFinder.findFiles(folderName);
		// System.out.println(fs[0].getName());

		for (int i = 0; i < fs.length; i++) {
			System.out.println(System.getenv("ProgramFiles") + "\\OpenSCAD\\openscad.exe -o " + folderName + "\\stl\\"
					+ fs[i].getName().substring(0, fs[i].getName().lastIndexOf('.')) + ".stl " + folderName + "\\scad\\"
					+ fs[i].getName().substring(0, fs[i].getName().lastIndexOf('.')) + ".scad");
			// converting the .scad files
			try {
				STLConverter.convert(fs[i].getName().substring(0, fs[i].getName().lastIndexOf('.')), folderName);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Prints the .scad Files of the model.
	 * 
	 * @param proc
	 *            the ScadProcessor using for printing
	 * @see ScadProcessor
	 */
	private static void printSCAD(ScadProcessor proc, String folderName) {
		ArrayList<Union> walls = proc.renderWallFiles();
		ArrayList<Union> corners = proc.renderCornerFiles();
		// when baseplate conversion is ready
		// TODO ArrayList<Union> baseplates;

		// print walls
		for (int i = 0; i < walls.size(); i++) {
			ScadPrinter.printFile("./" + folderName + "/scad/walls" + (i + 1), walls.get(i).toString());
		}

		// print corners
		for (int i = 0; i < corners.size(); i++) {
			ScadPrinter.printFile("./" + folderName + "/scad/corners" + (i + 1), corners.get(i).toString());
		}

		// when baseplate conversion is ready
		// TODO print baseplates
		// for (int i = 0; i < baseplates.size(); i++) {
		// ScadPrinter.printFile("./" + folderName + "/scad/baseplates" + (i +
		// 1), baseplates.get(i).toString());
		// }
	}

	/**
	 * Creates the directories for the .scad and the .stl Files.
	 */
	private static void createDirs(String folderName) {
		try {
			Files.createDirectories(Paths.get("./" + folderName + "/scad"));
			Files.createDirectories(Paths.get("./" + folderName + "/stl"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// getters - setters
	/**
	 * Returns the GUI of the program.
	 * 
	 * @return GUI
	 */
	public static GUI getGui() {
		return gui;
	}

	/**
	 * Sets the GUI for the program.
	 * 
	 * @param gui
	 *            the GUI to be set for the program
	 */
	public static void setGui(GUI gui) {
		Main.gui = gui;
	}

}
