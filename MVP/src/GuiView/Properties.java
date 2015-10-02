package GuiView;

import java.util.Random;

public class Properties {
	
	protected String name;
	protected boolean debugMode;
	protected String GUItype;
	protected String solveAlgorithm;
	protected String generateAlgorithm;
	
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
	public Properties() {
		super();
		debugMode = false;
		GUItype = "GUI";
		name = "Default";
		solveAlgorithm = "AstarManhattan";
		generateAlgorithm = "MyMaze3dGenerator";
		
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
