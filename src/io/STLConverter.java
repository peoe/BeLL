package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class STLConverter {

	/**
	 * Converts a certain scad file into stl.
	 * The file names will be taken over.
	 * 
	 * @param fileName name of the file
	 * @param fodlerName the name of the output folder
	 */
	public static void convert (String fileName, String folderName) throws InterruptedException {
		Process p;
		try {
			p = Runtime.getRuntime().exec("openscad -o " + folderName + "\\stl\\" + fileName + ".stl " + folderName + "\\scad\\" + fileName + ".scad");

			new Thread(new Runnable() {
			    public void run() {
			     BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			     String line = null; 

			     try {
			        while ((line = input.readLine()) != null)
			            System.out.println(line);
			     } catch (IOException e) {
			            e.printStackTrace();
			     }
			    }
			}).start();

			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
