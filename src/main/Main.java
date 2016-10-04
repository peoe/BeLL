package main;

import main.geometry.Line;
import main.geometry.Point;
import main.geometry.Point3D;

import java.util.List;

import org.kabeja.parser.ParseException;

import io.DXFReader;

public class Main {

	private final static int W = 10;
	
	public static void main(String[] args) {
		try {
			List<Line> list = DXFReader.getAutocadFile("C:/Users/Peter/Documents/Coding/Bell/BeLL/res/Haus_Johann.dxf");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).get()[0] + ", " + list.get(i).get()[0][1] + ", " + list.get(i).get()[1][0] + ", " + list.get(i).get()[1][1]);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void script() {
		Point[] gr = { new Point(), new Point(0, 100), new Point(200, 100), new Point(200, 0) };

		Point3D[] outerHedron = new Point3D[gr.length * 2];
		for (int i = 0; i < gr.length; i++) {
			outerHedron[i] = new Point3D(gr[i].getX(), gr[i].getY(), 0);
		}
		for (int i = 0; i < gr.length; i++) {
			outerHedron[i + gr.length] = new Point3D(gr[i].getX(), gr[i].getY(), 100);
		}
		
		Point3D[] innerHedron = new Point3D[gr.length * 2];
		for (int i = 0; i < gr.length; i++) {
			innerHedron[i] = new Point3D((gr[i].getX() == 0 ? W : gr[i].getX() - W), (gr[i].getY() == 0 ? W : gr[i].getY() - W), W);
		}
		for (int i = 0; i < gr.length; i++) {
			innerHedron[i + gr.length] = new Point3D((gr[i].getX() == 0 ? W : gr[i].getX() - W), (gr[i].getY() == 0 ? W : gr[i].getY() - W), 100);
		}

		String cnctd = "outerCube = [";
		for (Point3D p : outerHedron) {
			if (p != null) {
				cnctd += p.toString() + ", ";
			}
		}
		cnctd = cnctd.substring(0, cnctd.length() - 2);
		cnctd += "];\ninnerCube = [";
		for (Point3D p : innerHedron) {
			if (p != null) {
				cnctd += p.toString() + ", ";
			}
		}
		cnctd = cnctd.substring(0, cnctd.length() - 2);
		cnctd += "];\n\ndifference(){\n\tpolyhedron(outerCube,Faces);\n\tpolyhedron(innerCube,Faces);\n}";

		System.out.println(cnctd);
	}

}
