package GuiView;

import java.io.Serializable;

public class MazeProperties implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected boolean debugMode;
	protected String GUItype;
	protected String solveAlgorithm;
	protected String generateAlgorithm;
	protected int xAxis;
	protected int yAxis;
	protected int zAxis;
	
	public int getxAxis() {
		return xAxis;
	}
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	public int getyAxis() {
		return yAxis;
	}
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	public int getzAxis() {
		return zAxis;
	}
	public void setzAxis(int zAxis) {
		this.zAxis = zAxis;
	}
	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}
	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}
	public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}
	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}
	public MazeProperties() {
		super();
		debugMode = false;
		GUItype = "GUI";
		name = "Default";
		solveAlgorithm = "AstarManhattan";
		generateAlgorithm = "MyMaze3dGenerator";
		xAxis = 5;
		yAxis = 5;
		zAxis = 5;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDebugMode() {
		return debugMode;
	}
	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}
	public String getGUItype() {
		return GUItype;
	}
	public void setGUItype(String gUItype) {
		GUItype = gUItype;
	}

	
	
	

}
