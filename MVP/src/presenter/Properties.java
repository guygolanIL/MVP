package presenter;

import java.io.Serializable;


import org.hibernate.annotations.Entity;

@SuppressWarnings({ "serial", "deprecation" })
/**
 * Properties for the game system.
 * 
 * @author Guy Golan && Amit Sandak
 *
 */

public class Properties implements Serializable {

	protected boolean debugMode; // on or off.
	protected int mazeMaxAxisX; // max X parameter.
	protected int mazeMaxAxisY; // max Y parameter.
	protected int mazeMaxAxisZ; // max Z parameter.
	protected String generateAlgorithm; // maze generating algorithm.
	protected String solveAlgorithm; // maze solving algorithm.

	protected int maxThreads; // max threads.
	protected String ui; // which ui to present.

	public Properties() {
		super();
	}

	/**
	 * Setting the defaults values for an empty properties.
	 */
	public void setDefaults() {
		this.debugMode = false;
		this.generateAlgorithm = "MyMaze3dGenerator";
		this.solveAlgorithm = "AstarManhattan";
		this.mazeMaxAxisX = 35;
		this.mazeMaxAxisY = 35;
		this.mazeMaxAxisZ = 35;
		this.maxThreads = 10;
		this.ui = "Graphic user interface";
	}
									//--------------REGULAR SETTERS AND GETTERS------------------
	public boolean isDebug() {
		return debugMode;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}


	public void setUi(String ui) {
		this.ui = ui;
	}

	public void setDebug(boolean debug) {
		this.debugMode = debug;
	}

	public int getMazeMaxAxisX() {
		return mazeMaxAxisX;
	}

	public void setMazeMaxAxisX(int mazeMaxAxisX) {
		this.mazeMaxAxisX = mazeMaxAxisX;
	}

	public int getMazeMaxAxisY() {
		return mazeMaxAxisY;
	}

	public void setMazeMaxAxisY(int mazeMaxAxisY) {
		this.mazeMaxAxisY = mazeMaxAxisY;
	}

	public int getMazeMaxAxisZ() {
		return mazeMaxAxisZ;
	}

	public void setMazeMaxAxisZ(int mazeMaxAxisZ) {
		this.mazeMaxAxisZ = mazeMaxAxisZ;
	}

	public String getGenerateAlgorithm() {
		return generateAlgorithm;
	}

	public void setGenerateAlgorithm(String generateAlgorithm) {
		this.generateAlgorithm = generateAlgorithm;
	}

	public String getSolveAlgorithm() {
		return solveAlgorithm;
	}

	public void setSolveAlgorithm(String solveAlgorithm) {
		this.solveAlgorithm = solveAlgorithm;
	}

	public int getMaxThreads() {
		return maxThreads;
	}

	public void setMaxThreads(int maxThreads) {
		this.maxThreads = maxThreads;
	}

	public String getUi() {
		return ui;
	}

}
