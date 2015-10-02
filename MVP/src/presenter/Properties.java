package presenter;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

public class Properties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean debugMode;
	protected int mazeMaxAxisX;
	protected int mazeMaxAxisY;
	protected int mazeMaxAxisZ;
	protected String generateAlgorithm;
	protected String solveAlgorithm;
	
	protected int maxThreads;

	
	
	public Properties() {
		super();
		this.debugMode = false;
		this.generateAlgorithm = "MyMaze3dGenerator";
		this.solveAlgorithm = "AstarManhattan";
		this.mazeMaxAxisX = 35;
		this.mazeMaxAxisY = 35;
		this.mazeMaxAxisZ = 35;
		this.maxThreads = 10;
	}

	public boolean isDebug() {
		return debugMode;
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

	
}
