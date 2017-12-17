package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import graph.*;
import render.objects.Rotate;
import render.objects.Translate;
import render.objects.Union;

public class ScadProcessor {
	// params
	private Params params;
	// walls
	private ArrayList<Wall> walls = new ArrayList<>();
	// corners
	private ArrayList<Corner> corners = new ArrayList<>();
	// baseplates
	private ArrayList<BasePlate> basePlates = new ArrayList<>();

	/**
	 * Constructor of the ScadProcessor class using an ArrayList of Lines and a Params object.
	 * @param ls the ArrayList of Lines
	 * @param params the Params object 
	 */
	public ScadProcessor(ArrayList<Line> ls, Params params) {
		Graph graph = new Graph(ls, params);
		this.params = params;

		for (Node n : graph.getNodes()) {
			getCorners().add(new Corner(n, params));
		}

		for (Face f : graph.getFaces()) {
			getBasePlates().add(new BasePlate(f, params));
		}

		for (Edge e : graph.getEdges()) {
			boolean contained = false;

			for (Wall w : getWalls()) {
				if (w.getE().getTwin().equals(e)) {
					contained = true;
					break;
				}
			}

			if (!contained) {
				getWalls().add(new Wall(e, params));
			}
		}
	}

	/**
	 * Returns the oriented BasePlates in an ArrayList of Unions.
	 * @return ArrayList of BasePlate Unions
	 */
	public ArrayList<ScadObject> renderBasePlateFiles() {
		ArrayList<ScadObject> files = new ArrayList<>();
		ArrayList<BasePlate> basePlates = (ArrayList<BasePlate>) getBasePlates().clone();
		Collections.sort(basePlates, BasePlate.BasePlateOMBBComparator);
		
		Vector rotatedNode;

		double xMin, yMin, usedWidth = 0.0, usedLength = 0.0;
		double faceEnlarge = 4 * params.getEpsilon() + params.getWallWidth();

		int fileIndex = -1;

		for (BasePlate b : basePlates) {
			if (b.getF().getArea() > 0) {
				xMin = Double.MAX_VALUE;
				yMin = Double.MAX_VALUE;

				for (Node n : b.getF().getNodes()) {
					rotatedNode = n.getOrigin().rotate(b.getOmbbAngle());

					if (rotatedNode.getX() < xMin) {
						xMin = rotatedNode.getX();
					}

					if (rotatedNode.getY() < yMin) {
						yMin = rotatedNode.getY();
					}
				}

				if (params.getMaxPrintWidth() - usedWidth - b.getWidth() + 2 * faceEnlarge < 0) {
					usedWidth = faceEnlarge;
					usedLength += basePlates.get(basePlates.indexOf(b) - 1).getWidth();
				}

				if ((usedLength + b.getLength()) > params.getMaxPrintHeight() || files.size() == 0) {
					files.add(new Union());
					fileIndex++;
					usedLength = faceEnlarge;
					usedWidth = faceEnlarge;
				}

				((Union) files.get(fileIndex)).getObjects().add(new Translate(new Rotate(b, Math.toDegrees(b.getOmbbAngle()), 0, 0, 1), -xMin + usedWidth, -yMin + usedLength, 0));
				usedWidth += b.getWidth() + 2 * faceEnlarge;
			}
		}
		for (int i = 0; i < files.size(); i++){
			files.set(i, new Translate(new Rotate(files.get(i), 180, 1, 0, 0), 0, params.getMaxPrintHeight(), params.getBasePlateHeight()));
		}
		return files;
	}

	/**
	 * Returns the oriented Corners in an ArrayList of Unions.
	 * @return ArrayList of Corner Unions
	 */
	public ArrayList<Union> renderCornerFiles() {
		ArrayList<Union> files = new ArrayList<>();
		ArrayList<Corner> corners = (ArrayList<Corner>) getCorners().clone();
		Collections.sort(corners, Corner.CornerWidthComparator);
		
		double cornerLength = 0.0;
		double cornerWidth = 0.0;

		int fileIndex = -1;

		for (Corner c : corners) {
			if (params.getMaxPrintWidth() - cornerWidth - c.getWidth() < 0) {
				cornerWidth = 0;
				cornerLength += corners.get(corners.indexOf(c) - 1).getWidth();
			}

			if ((cornerLength + c.getWidth() > params.getMaxPrintHeight()) || (files.size() == 0)) {
				files.add(new Union());
				fileIndex++;
				cornerLength = 0;
				cornerWidth = 0;
			}

			files.get(fileIndex).getObjects().add(new Translate(c, new Vector(c.getWidth() * 0.5, c.getWidth() * 0.5).add(new Vector(cornerWidth, cornerLength)), 0));
			cornerWidth += c.getWidth();
		}

		return files;
	}

	/**
	 * Returns the oriented Walls in an ArrayList of Unions.
	 * @return ArrayList of Wall Unions
	 */
	public ArrayList<Union> renderWallFiles() {
		ArrayList<Union> files = new ArrayList<>();
		ArrayList<Wall> walls = (ArrayList<Wall>) getWalls().clone();
		Collections.sort(walls, Wall.WallLengthComparator);
		double rowLength = 0.0;
		double columnWidth = 0.0;

		int fileIndex = -1;

		for (Wall w : walls) {
			if (params.getMaxPrintWidth() - columnWidth - w.getLength() < 0) {
				columnWidth = 0;
				rowLength += params.getWallWidth() + 4 * params.getEpsilon();
			}

			if ((rowLength + params.getWallWidth() > params.getMaxPrintHeight()) || (files.size() == 0)) {
				files.add(new Union());
				fileIndex++;
				rowLength = 0;
				columnWidth = 0;
			}

			files.get(fileIndex).getObjects().add(new Translate(new Rotate(w, -w.getE().toVector().angleInDegrees(), 0, 0, 1), new Vector(w.getLength() * 0.5,  params.getWallWidth() * 0.5).add(new Vector(columnWidth, rowLength)), 0.5 * params.getEpsilon() + 0.5 * (params.getHeight() - params.getBasePlateHeight())));
			columnWidth += w.getLength();
		}
		
		return files;
	}

	/**
	 * Returns the Walls as Unions of ScadObjects.
	 * @return ScadObject of Walls
	 */
	public ScadObject outputWalls() {
		ArrayList<ScadObject> objectList = new ArrayList<>();

		for (Wall w : getWalls()) {
			objectList.add(new Translate(w, w.getE().toVector().multiply(0.5).add(w.getE().getN1().getOrigin()),
					params.getBasePlateHeight() + 0.5 * params.getEpsilon()
							+ 0.5 * (params.getHeight() - params.getBasePlateHeight())));
		}

		return (new Union(objectList));
	}

	/**
	 * Returns the Corners as Unions of ScadObjects.
	 * @return ScadObject of Corners
	 */
	public ScadObject outputCorners() {
		ArrayList<ScadObject> objectList = new ArrayList<>();

		for (Corner c : getCorners()) {
			objectList.add(new Translate(c, c.getN().getOrigin(), 0));
		}

		return (new Union(objectList));
	}

	/**
	 * Returns the BasePlates as Unions of ScadObjects.
	 * @return ScadObject of BasePlates
	 */
	public ScadObject outputBasePlates() {
		ArrayList<ScadObject> objectList = new ArrayList<>();

		for (BasePlate bp : getBasePlates()) {
			if (bp.getF().getArea() > 0.0) {
				objectList.add(bp);
			}
		}

		return (new Union(objectList));
	}

	// getters - setters
	/**
	 * Returns the Params object of the ScadProcessor.
	 * @return Params object of ScadProcessor
	 */
	public Params getParams() {
		return params;
	}

	/**
	 * Sets the Params object of the ScadProcessor.
	 * @param params the Params object to be set for ScadProcessor
	 */
	public void setParams(Params params) {
		this.params = params;
	}

	/**
	 * Returns the ArrayList of Walls of the ScadProcessor.
	 * @return ArrayList of Walls
	 */
	public ArrayList<Wall> getWalls() {
		return walls;
	}

	/**
	 * Sets the ArrayList of Walls of the ScadProcessor.
	 * @param walls the ArrayList to be set for ScadProcessor
	 */
	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}

	/**
	 * Returns the ArrayList of Corners of the ScadProcessor.
	 * @return ArrayList of Corners
	 */
	public ArrayList<Corner> getCorners() {
		return corners;
	}

	/**
	 * Sets the ArrayList of Corners of the ScadProcessor.
	 * @param corners the ArrayList to be set for ScadProcessor
	 */
	public void setCorners(ArrayList<Corner> corners) {
		this.corners = corners;
	}

	/**
	 * Returns the ArrayList of BasePlates of the ScadProcessor.
	 * @return ArrayList of BasePlates
	 */
	public ArrayList<BasePlate> getBasePlates() {
		return basePlates;
	}

	/**
	 * Sets the ArrayList of BasePlates of the ScadProcessor.
	 * @param basePlates the ArrayList to be set for ScadProcessor
	 */
	public void setBasePlates(ArrayList<BasePlate> basePlates) {
		this.basePlates = basePlates;
	}

}
