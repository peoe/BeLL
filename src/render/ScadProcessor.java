package render;
import java.util.ArrayList;

import graph.*;
import io.Quicksort;
import render.objects.Rotate;
import render.objects.Translate;
import render.objects.Union;

public class ScadProcessor {

	private Params params;
	private ArrayList<Wall> walls = new ArrayList<>();
	private ArrayList<Corner> corners = new ArrayList<>();
	private ArrayList<BasePlate> basePlates = new ArrayList<>();

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
	
	public ScadProcessor(ArrayList<Line> ls, Params params) {
		Graph graph = new Graph(ls, params);
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
	
	public ArrayList<Union> renderBasePlateFiles(){
		ArrayList<Union> files = new ArrayList<>();
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<BasePlate> basePlates = new Quicksort(getBasePlates(), "ombbArea").sortArray();
		double xMin, yMin, usedWidth = 0.0, usedLength = 0.0;
		Vector rotatedNode;
		double faceEnlarge = 4 *params.getEpsilon() + params.getWallWidth();
		int fileIndex = -1;
		for (BasePlate b : basePlates){
			if (b.getF().getArea() > 0){
			xMin = Double.MAX_VALUE;
			yMin = Double.MAX_VALUE;
			for (Node n : b.getF().getNodes()){
				rotatedNode = n.getOrigin().rotate(b.getOmbbAngle());
				if (rotatedNode.getX() < xMin){
					xMin = rotatedNode.getX();
				}
				if (rotatedNode.getY() < yMin){
					yMin = rotatedNode.getY();
				}	
			}
			if (params.getMaxPrintWidth() - usedWidth - b.getWidth() + 2* faceEnlarge < 0){
				usedWidth = faceEnlarge;
				usedLength += basePlates.get(basePlates.indexOf(b) - 1).getWidth();
			}
			if ((usedLength + b.getLength()) > params.getMaxPrintHeight() || files.size() == 0){
				files.add(new Union());
				fileIndex++;
				usedLength = faceEnlarge;
				usedWidth = faceEnlarge;
			}
			files.get(fileIndex).getObjects().add(new Translate(new Rotate(b, Math.toDegrees(b.getOmbbAngle()), 0, 0, 1), -xMin + usedWidth, -yMin + usedLength, 0 ));
			usedWidth += b.getWidth() + 2 * faceEnlarge;
			}
			
		}
		
		return files;
		
	}
	
	public ArrayList<Union> renderCornerFiles(){
		ArrayList<Union> files = new ArrayList<>();
		double cornerRowLength = 0.0;
		double cornerLength = 0.0;
		double cornerWidth = 0.0;
		double differenceX = 1.0;
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<Corner> corners = new Quicksort(getCorners(), "width").sortArray();
		int fileIndex = -1;
		for (Corner c : corners){
			
			if (params.getMaxPrintWidth() - cornerWidth - c.getWidth() < 0) {
				cornerWidth = 0;
				cornerLength += corners.get(corners.indexOf(c) - 1 ).getWidth();
			}
			if ((cornerLength + c.getWidth() > params.getMaxPrintHeight()) || (files.size() == 0)) {
				files.add(new Union());
				fileIndex++;
				cornerLength = 0;
				cornerWidth = 0;
			}
			files.get(fileIndex).getObjects().add(new Translate(c, new Vector(c.getWidth() *0.5, c.getWidth() *0.5).add(new Vector(cornerWidth, cornerLength)),0));
			//area of Corner is a square so Width of c = Length of c
			cornerWidth += c.getWidth();
			
		}
	
	return files;
		
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
				
				if ((spaceLeft > minimum) && (spaceLeft + w.getLength() <= params.getMaxPrintWidth())){
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
			int filesIndex = (int) renderWidth / (int) params.getMaxPrintHeight();
			if (filesIndex > files.size()-1){
				files.add(new Union());
			}
				files.get(filesIndex).getObjects().add((new Translate(new Rotate(w,-w.getE().toVector().angleInDegrees(), 0, 0, 1),
						minimum + w.getLength()*0.5,
						0.5 * params.getWallwidth() + (index - filesIndex * ((int)params.getMaxPrintHeight() / (int)(params.getWallwidth() + 8 * params.getEpsilon()))) * (params.getWallwidth() + 8 * params.getEpsilon()),
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
	
	public ScadObject outputCorners(){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		for(Corner c : getCorners()){
			objectList.add(new Translate(c,c.getN().getOrigin(),0));
		}
		return (new Union(objectList));
	}
	
	public ScadObject outputBasePlates(){
		ArrayList<ScadObject> objectList = new ArrayList<>();
		for(BasePlate bp: getBasePlates()){
			if(bp.getF().getArea()>0.0){
			objectList.add(bp);
			}
		}
		return (new Union(objectList));
	}

}
