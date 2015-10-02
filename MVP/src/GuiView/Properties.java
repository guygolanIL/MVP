package GuiView;

import java.util.Random;

public class Properties {
	
	protected String name;
	protected boolean debugMode;
	protected String GUItype;
	public Properties() {
		super();
		debugMode = false;
		GUItype = "GUI";
		name = "Default";
		
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
