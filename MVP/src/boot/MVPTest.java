package boot;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import GuiView.MyObservableGuiView;
import cliView.MyObservableCLIView;
import model.MyObservableModel;
import presenter.Presenter;
import presenter.Properties;
import view.ObservableCommonView;

public class MVPTest {

	public static void main(String[] args) {
		String defaultXMLname = "properties.xml";
		Properties prop;
		try {
			FileInputStream in = new FileInputStream(defaultXMLname);		//tries to read the properties.xml default pathname.
			XMLDecoder decoder = new XMLDecoder(in);
			prop = (Properties)decoder.readObject();		//decoding the xml file.
			decoder.close();
					
		} catch (FileNotFoundException e) {				//if no properties.xml was found in directory, generating default properties.
			System.out.println("file not found, default properties will be loaded");
			prop = new Properties();
			prop.setDefaults();
		}
		ObservableCommonView view = null;
		switch (prop.getUi())				//according to the properties initializing the view.
		{
			case "Command line":
				view = new MyObservableCLIView(new BufferedReader(new InputStreamReader(System.in)) , new PrintWriter(System.out));
				break;
			case "Graphic user interface":
				view = new MyObservableGuiView("maze game", 800, 500);
				break;
			default:
				view = new MyObservableGuiView("maze game", 800, 500);	
		}
				
		MyObservableModel model = new MyObservableModel();
		Presenter p = new Presenter(model,view);		
				
		p.setProperties(prop);		//setting the properties of the system for the presenter.
		//p.setDebugMode(true);		//debug mode turned off.
				
		model.addObserver(p);		//presenter observing the model for changes.
		view.addObserver(p);		//presenter observing the view for changes.
				
		p.start();				//start of the user interface.
	}

}