package render;
import java.util.ArrayList;

import graph.*;
import render.objects.Translate;
import render.objects.Union;

public class ScadProcessor {
	
	private Double maxPrintWidth, maxPrintHeight;
	private Params params;
	private ArrayList<Wall> walls = new ArrayList<>();
	private ArrayList<Corner> corners = new ArrayList<>();
	private ArrayList<BasePlate> basePlates = new ArrayList<>();
	
	public Double getMaxPrintWidth() {
		return maxPrintWidth;
	}

	public void setMaxPrintWidth(Double maxPrintWidth) {
		this.maxPrintWidth = maxPrintWidth;
	}

	public Double getMaxPrintHeight() {
		return maxPrintHeight;
	}

	public void setMaxPrintHeight(Double maxPrintHeight) {
		this.maxPrintHeight = maxPrintHeight;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

	public ArrayList<Corner> getCorners() {
		return corners;
	}

	public void setCorners(ArrayList<Corner> corners) {
		this.corners = corners;
	}

	public ArrayList<BasePlate> getBasePlates() {
		return basePlates;
	}

	public void setBasePlates(ArrayList<BasePlate> basePlates) {
		this.basePlates = basePlates;
	}

	public ScadProcessor(Graph graph, double E, double CornerRadius, double PinMinLength, double PinPWidth, double PinPRadius, double PinDistance, double Height, double PinHeight, double BasePlateHeight, double BasePlatePinCircleHeight) {
		params = new Params(E, CornerRadius, PinMinLength, PinPWidth, PinPRadius, PinDistance, Height, PinHeight, BasePlateHeight, BasePlatePinCircleHeight);
		for(Node n : graph.getNodes()){
			getCorners().add(new Corner(n, params));
		}
		for(Face f: graph.getFaces()){
			getBasePlates().add(new BasePlate(f, params));
		}
		for(Edge e: graph.getEdges()){
			getWalls().add(new Wall(e, params));
		}
		
	}
	
	public ScadProcessor(Graph graph, Params params) {
		this.params = params;
		for(Node n : graph.getNodes()){
			getCorners().add(new Corner(n, params));
		}
		for(Face f: graph.getFaces()){
			getBasePlates().add(new BasePlate(f, params));
		}
		for(Edge e: graph.getEdges()){
			getWalls().add(new Wall(e, params));
		}
		
	}
	
	public ScadObject outputWalls(){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		
		for(Wall w : getWalls()){
			objectList.add(w);
		}
		
		return (new Union(objectList));
	}
	
	public ScadObject outputBasePlates(){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		for(Corner c : getCorners()){
			objectList.add(new Translate(c,c.getN().getOrigin(),0));
		}
		return (new Union(objectList));
	}
	
	public ScadObject outputCorners(){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		for(BasePlate bp: getBasePlates()){
			if(bp.getF().getArea()>0.0){
			objectList.add(bp);
			}
		}
		return (new Union(objectList));
	}

}
