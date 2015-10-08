package GuiView;

import java.io.Serializable;

public class MazeProperties implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
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
	
	public MazeProperties() {
		super();
		name = "Default";
		xAxis = 11;
		yAxis = 11;
		zAxis = 11;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
