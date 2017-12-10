package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class STLConverter {
	// the location of openscad.exe
	private static String openSCADLocation = "";

	/**
	 * Converts a .scad File within the result folder into a .stl File with the
	 * same filename.
	 * 
	 * @param fileName
	 *            the name of the File
	 * @param fodlerName
	 *            the name of the result folder
	 */
	public static void convert(String fileName, String folderName) throws InterruptedException {
		Process p;
		ProcessBuilder b;
		try {
			b = new ProcessBuilder(openSCADLocation + "\\openscad.exe", "-o",
					folderName + "\\stl\\" + fileName + ".stl", folderName + "\\scad\\" + fileName + ".scad");

			// start the process
			p = b.start();

			// catch possible read/write errors
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

			// wait until process is finished
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets the path to the OpenSCAD executable used by the STLConverter.
	 * 
	 * @param openSCADLocation
	 *            the path to the OpenSCAD executable
	 */
	public static void setOpenSCADLocation(String openSCADLocation) {
		STLConverter.openSCADLocation = openSCADLocation;
	}

}
