package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ScadPrinter {
	
	// prints given strings
	/**
	 * Prints the given String into a file as &ltfilename&gt.scad.
	 * @param filename the name of the file
	 * @param filetext the text to be written into the file
	 */
	public static void printFile (String filename, String filetext) {		
		try {
			// filewriter is awesome
			FileWriter fw = new FileWriter(new File (filename + ".scad"));
			fw.write(filetext);
			fw.close();
		} catch (IOException ioe) {
			// catching the exceptions
			System.out.println(ioe.getStackTrace());
		}
	}

}
