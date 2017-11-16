package io;

import java.io.File;
import java.io.FilenameFilter;

public class SCADFinder {
	
	/**
	 * Returns an array of files in the given folder.
	 * @param folderName the name of the specified folder
	 * @return array of files in the specified folder
	 */
	public static File[] findFiles(String folderName){
        File dir = new File(".\\" + folderName + "\\scad\\");

        return dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".scad"); }
        });
    }
	
}
