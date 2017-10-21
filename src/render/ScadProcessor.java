package render;
import java.util.ArrayList;

import graph.*;
import render.objects.Rotate;
import render.objects.Translate;
import render.objects.Union;

public class ScadProcessor {
	
	private double maxPrintWidth, maxPrintHeight;
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
			for (Edge e : n.getAdjacentEdges()){
				getWalls().add(new Wall(e, params));
			}
		}
		for(Face f: graph.getFaces()){
			getBasePlates().add(new BasePlate(f, params));
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
		for (Edge e : graph.getEdges()){
			boolean contained = false;
			for (Wall w : getWalls()){
				if (w.getE().getTwin().equals(e)){
					contained = true;
					break;
				}
			}
			if(!contained){
				getWalls().add(new Wall(e, params));
			}
		}
		
	}
	
	public ArrayList<Union> renderWallFiles(){
		ArrayList<Union> files = new ArrayList<>();
		ArrayList<Double> wallLengths = new ArrayList<>();
		double spaceLeft, minimum;
		int index;
		
		for (Wall w : getWalls()){
			minimum = 0;
			spaceLeft = 0;
			index = -1;
			for (int i = 0; i < wallLengths.size(); i++){
				spaceLeft = 0;
				spaceLeft = wallLengths.get(i);
				
				
				if ((spaceLeft > minimum) && (spaceLeft + w.getLength() <= getMaxPrintWidth())){
					minimum = spaceLeft;
					index = i;
				}
			}
			
		
			if (index == -1){
				wallLengths.add(w.getLength());
				index = wallLengths.size() - 1;
			} else {
				wallLengths.set(index, wallLengths.get(index) + w.getLength());
			}
			double renderWidth = 0.5 * params.getWallwidth() + index * (params.getWallwidth() + 8 * params.getEpsilon());
			int filesIndex = (int) renderWidth / (int) maxPrintHeight;
			if (filesIndex > files.size()-1){
				files.add(new Union());
			}
				files.get(filesIndex).getObjects().add((new Translate(new Rotate(w,-w.getE().toVector().angleInDegrees(), 0, 0, 1),
						minimum + w.getLength()*0.5,
						0.5 * params.getWallwidth() + (index - filesIndex * ((int)maxPrintHeight / (int)(params.getWallwidth() + 8 * params.getEpsilon()))) * (params.getWallwidth() + 8 * params.getEpsilon()),
						0.5 * params.getEpsilon() + 0.5 * (params.getHeight() - params.getBasePlateHeight()))));
				
			
		}
		return  files;
	}
	
	public ScadObject outputWalls(){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		
		for(Wall w : getWalls()){
			objectList.add(new Translate(w, w.getE().toVector().multiply(0.5).add( w.getE().getN1().getOrigin()), params.getBasePlateHeight() + 0.5 * params.getEpsilon() + 0.5 * (params.getHeight() - params.getBasePlateHeight())));
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
