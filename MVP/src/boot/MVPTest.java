package boot;

import java.beans.XMLDecoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import GuiView.MyObservableGuiView;
import GuiView.StartWindow;
import cliView.MyObservableCLIView;
import model.MyObservableModel;
import presenter.Presenter;
import presenter.Properties;
import view.CLI;
import view.ObservableCommonView;
import view.View;

public class MVPTest {

	public static void main(String[] args) {
		
Thread startWindowThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				StartWindow startWindow = new StartWindow();
				startWindow.run();
				try {
					FileInputStream in = new FileInputStream(startWindow.getSelectedXMLpropertiesFile());
					XMLDecoder decoder = new XMLDecoder(in);
					Properties prop = (Properties)decoder.readObject();
					decoder.close();
					ObservableCommonView view = null;
					switch (prop.getUi())
					{
					case "Command line":
						view = new MyObservableCLIView(new BufferedReader(new InputStreamReader(System.in)) , new PrintWriter(System.out));
						break;
					case  "Graphic user interface":
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
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread mainView = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					startWindowThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// TODO Auto-generated method stub
				
			}
		});
		
		startWindowThread.start();
		mainView.start();
    }

}