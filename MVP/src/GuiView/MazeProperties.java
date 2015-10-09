package GuiView;

import java.io.Serializable;

/**
 * The  Maze Properties Class.
 * this class collecting all the data of the current maze.
 * 
 *  @author Guy Golan && Amit Sandak.
 */
public class MazeProperties implements Serializable {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The maze name. */
	protected String name;
	
	/** The x axis size. */
	protected int xAxis;
	
	/** The y axis size. */
	protected int yAxis;
	
	/** The z axis size. */
	protected int zAxis;
	
	/**
	 * Gets the x axis size.
	 *
	 * @return the x axis size
	 */
	public int getxAxis() {
		return xAxis;
	}
	
	/**
	 * Sets the x axis size.
	 *
	 * @param xAxis the new x axis size
	 */
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	
	/**
	 * Gets the y axis size.
	 *
	 * @return the y axis size
	 */
	public int getyAxis() {
		return yAxis;
	}
	
	/**
	 * Sets the y axis size.
	 *
	 * @param yAxis the new y axis size
	 */
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	
	/**
	 * Gets the z axis size.
	 *
	 * @return the z axis size
	 */
	public int getzAxis() {
		return zAxis;
	}
	
	/**
	 * Sets the z axis size.
	 *
	 * @param zAxis the new z axis size
	 */
	public void setzAxis(int zAxis) {
		this.zAxis = zAxis;
	}
	
	/**
	 * Instantiates a new maze properties.
	 * all the data will get default values.
	 */
	public MazeProperties() {
		super();
		name = "Default";
		xAxis = 11;
		yAxis = 11;
		zAxis = 11;
		
	}
	
	/**
	 * Gets the maze name.
	 *
	 * @return the maze name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the maze name.
	 *
	 * @param name the new maze name
	 */
	public void setName(String name) {
		this.name = name;
	}

}
