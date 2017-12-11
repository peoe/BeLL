package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScadPrinter {

	/**
	 * Prints the given String into a File '&ltfilename&gt.scad'.
	 * 
	 * @param filename
	 *            the name of the File
	 * @param filetext
	 *            the text to be written into the File
	 */
	public static void printFile(String filename, String filetext) {
		try {
			// filewriter used for writing whole strings
			FileWriter fw = new FileWriter(new File(filename + ".scad"));
			fw.write(filetext);
			fw.close();
		} catch (IOException ioe) {
			System.out.println(ioe.getStackTrace());
		}
	}

}
