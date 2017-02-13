package rend;

import java.util.ArrayList;
import java.util.Arrays;

import graph.*;
import rend.objects.*;

public class Corner implements ScadObject{
	
	private Vector p;
	private ArrayList<Vector> Corners;
	private double height;
	private Difference MinusTile;
	
	
	
	public Corner(Vector P, ArrayList<Vector> C, double H){
		p=P;
		Corners=C;
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

	@Override
	public String printcommand() {
		//First part Wall fitting segment
		ArrayList<ScadObject> DifferenceAL = new ArrayList<>();
		Line v;
		DifferenceAL.add(Base);
		for (int i=0;i<Corners.size();i++){
			v = new Line(p, Corners.get(i));
			DifferenceAL.add(new RawWall(new Line(p, Corners.get(i))));
			DifferenceAL.add(new Rotate(MinusTileCorner,v.angle(), 0, 0, 1));
		}
		Difference finalDifference = new Difference(DifferenceAL);
		//Second part base plate fitting segment
		Line v2;
		Translate trtemp;
		Rotate rtemp;
		ArrayList<ScadObject> UnionAL = new ArrayList<>();
		for (int i=0;i<Corners.size();i++){
			v = new Line(p, Corners.get(i));
			v2 = new Line(p, Corners.get((i+1) % Corners.size()));
			trtemp = new Translate(PinPositive, 3.4375+9.5,0,0);
			rtemp = new Rotate(trtemp, v.angletoPV(v2)*0.5+v.angle(), 0, 0, 1);
			UnionAL.add(rtemp);
			System.out.println(v.angletoPV(v2)*0.5+v.angle());
		}
		UnionAL.add(Base);
		UnionAL.add(new Translate(Base2, 0, 0,2.5*-(1.0/6.0)));
		
		Union Unionbottom = new Union(UnionAL);
		Scale su = new Scale(Unionbottom, 1, 1, 6);
		Scale sd = new Scale(finalDifference, 1, 1, this.height-6);
		Translate rsd = new Translate(sd, 0, 0, 3+(this.height-6)/2);
		Union finalUnion = new Union(new ArrayList<ScadObject>(Arrays.asList(su,rsd)));
		Translate result = new Translate(finalUnion, new Line(p), 3);
		
		
		
		return result.printcommand();
	}
	
	

}
