package rend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import graph.*;
import rend.objects.*;

public class Corner implements ScadObject{
	
	private Vector p;
	private ArrayList<Vector> Corners;
	private double height;
	
	
	
	
	public Corner(Vector P, ArrayList<Vector> C, double H){
		p=P;
		Corners=C;
		height=H;
	}
	
	public Corner(Vector P, Graph f, double H){
		p=P;
		Corners = new ArrayList<>();
		ArrayList<Line> vtemp = f.getLinesPointingAway(P);
		System.out.println("\nvtemp:\n" + vtemp + "\n");
		for (int i=0;i<vtemp.size();i++){
			Corners.add(vtemp.get(i).getP2());
			System.out.println("\nCorner added:\n" + Corners.get(i) + "\n");
		}
		height=H;
	}

	public Vector getP() {
		return p;
	}

	public void setP(Vector p) {
		this.p = p;
	}

	public ArrayList<Vector> getCorners() {
		return Corners;
	}

	public void setCorners(ArrayList<Vector> corners) {
		Corners = corners;
	}
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public Vector getClosestVector(Vector v){
		if (!Corners.contains(v)){
			System.out.println("Corner not contained");
			return null;
		}
		
		ArrayList<Double> doubles = new ArrayList<>();
		
		for (Vector vec : Corners){
//		doubles.add(new Double(vs.get(i).angle(),5));
		doubles.add(v.angletoVector(vec));
		}
		return Corners.get((doubles.indexOf(Collections.min(doubles, null))));
	}

//	public void generateMinusTile(){
//		Cube c1 = new Cube(20,10,1, true);
//		Cube c2 = new Cube(10,9.5,1, true);
//		Translate tc2 = new Translate(Cube c2 = new Cube(10,9.5,1, true), 10, 0, 0)
//		ArrayList<ScadObject> temp = new ArrayList<>();
//		temp.add(c1);
//		temp.add(tc2);
//		MinusTile = new Difference(temp);
//	}
//	
//	
	final Translate PinPositive = new Translate(new Union(new ArrayList<>(Arrays.asList(new Cube(3,1.5,1, true),new Translate(new Cylinder(1, 3.75, 3.75, true), 5, 0, 0)))), -3.4375, 0, 0);
	
	
	
	
	
	private ScadObject getMinusTileCorner(double r, double w, double e){//r=10 w=3 e=0.25 
		Cube c1 = new Cube(r,w,1, true);
		Cube c2 = new Cube(r,w-2*e,1, true);
		Translate tc1 = new Translate(c1, r, 0, 0);
		Translate tc2 = new Translate(c2, r+e, 0, 0);
		ArrayList<ScadObject> temp = new ArrayList<>();
		temp.add(tc1);
		temp.add(tc2);
		return( new Difference(temp));
	}

	@Override
	public String printCommand() {
		//First part Wall fitting segment
		ArrayList<ScadObject> DifferenceAL = new ArrayList<>();
		Vector v;
		DifferenceAL.add(Base);
		for (int i=0;i<Corners.size();i++){
			v = new Vector(p,Corners.get(i));
			DifferenceAL.add(new Wall(new Line(p, Corners.get(i))));
			DifferenceAL.add(new Rotate(MinusTileCorner,v.angleD(), 0, 0, 1));
			System.out.println("\nangle to: " + Corners.get(i) + " is " + v.angleD() + "\n" );
		}
		Difference finalDifference = new Difference(DifferenceAL);
		//Second part base plate fitting segment
		Vector v2;
		Translate trtemp;
		Rotate rtemp;
		ArrayList<ScadObject> UnionAL = new ArrayList<>();
		for (int i=0;i<Corners.size();i++){
			v = new Vector(p, Corners.get(i));
			v2 = this.getClosestVector(v);
			trtemp = new Translate(PinPositive, 3.4375+9.5,0,0);
			rtemp = new Rotate(trtemp, v.angletoVectorD(v2)*0.5+v.angleD(), 0, 0, 1);
			UnionAL.add(rtemp);
			System.out.println("\n angle of" + v + " to " +v2 + " is " + v.bisectorOfAngleTo(v2) + "\n");
		}
		UnionAL.add(Base);
		UnionAL.add(new Translate(Base2, 0, 0,2.5*-(1.0/6.0)));
		
		Union Unionbottom = new Union(UnionAL);
		Scale su = new Scale(Unionbottom, 1, 1, 6);
		Scale sd = new Scale(finalDifference, 1, 1, this.height-6);
		Translate rsd = new Translate(sd, 0, 0, 3+(this.height-6)/2);
		Union finalUnion = new Union(new ArrayList<ScadObject>(Arrays.asList(su,rsd)));
		Translate result = new Translate(finalUnion, p, 3);
		
		
		
		return result.printCommand();
//		return null;
	}
	
	

}
