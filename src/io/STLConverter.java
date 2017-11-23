package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class STLConverter {
	//private static String openSCADLocation = System.getenv("ProgramFiles") + "/OpenSCAD/openscad.exe";
	private static String openSCADLocation = "";

	/**
	 * Converts a certain scad file into stl.
	 * The file names will be taken over.
	 * 
	 * @param fileName name of the file
	 * @param fodlerName the name of the output folder
	 */
	public static void convert (String fileName, String folderName) throws InterruptedException {
		Process p;
		ProcessBuilder b;
		try {
			b = new ProcessBuilder(openSCADLocation + "\\openscad.exe", "-o", folderName + "\\stl\\" + fileName + ".stl", folderName + "\\scad\\" + fileName + ".scad");
			//b.environment().put("PATH", openSCADLocation);
			
			//p = Runtime.getRuntime().exec("openscad -o " + folderName + "\\stl\\" + fileName + ".stl " + folderName + "\\scad\\" + fileName + ".scad");
			p = b.start();

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

	/**
	 * Sets the path to the OpenSCAD executable used by the STLConverter.
	 * @param openSCADLocation the path to the executable
	 */
	public static void setOpenSCADLocation(String openSCADLocation) {
		STLConverter.openSCADLocation = openSCADLocation;
	}
	
}
