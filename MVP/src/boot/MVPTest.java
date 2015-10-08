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
					FileInputStream in = new FileInputStream(defaultXMLname);
					XMLDecoder decoder = new XMLDecoder(in);
					 prop = (Properties)decoder.readObject();
					decoder.close();
					
				} catch (FileNotFoundException e) {
					System.out.println("file not found, default properties will be loaded");
					prop = new Properties();
					prop.setDefaults();
				}
				ObservableCommonView view = null;
				switch (prop.getUi())
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
				
				p.setProperties(prop);
				//view.setDebugMode(true);
				p.setDebugMode(true);
				
				model.addObserver(p);
				view.addObserver(p);
				
				view.start();
			}

}