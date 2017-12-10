package io;

import java.io.File;
import java.io.FilenameFilter;

public class SCADFinder {

	/**
	 * Returns an array of .scad Files in the result folder.
	 * 
	 * @param folderName
	 *            the name of the result folder
	 * @return array of .scad Files
	 */
	public static File[] findFiles(String folderName) {
		// get link to the scad folder
		File dir = new File(".\\" + folderName + "\\scad\\");

		// return all .scad files
		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".scad");
			}
		});
	}

}
