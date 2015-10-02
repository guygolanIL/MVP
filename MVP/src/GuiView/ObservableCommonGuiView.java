package GuiView;


import view.ObservableCommonView;

public abstract class ObservableCommonGuiView extends ObservableCommonView {
	protected Properties properties;

	public ObservableCommonGuiView(Properties properties) {
		super();
		this.properties = properties;
	}
	
	public void setDebugMode(boolean debug) {
		properties.setDebugMode(debug);
		
	}
	
	


}
