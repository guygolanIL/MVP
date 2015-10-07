package boot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import GuiView.MyObservableGuiView;
import GuiView.StartApp;
import cliView.MyObservableCLIView;
import model.MyObservableModel;
import presenter.Presenter;

public class MVPTest {

	public static void main(String[] args) {
		Thread task1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
//			try {
//				PropertiesGenerate.main(args);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			}
		});
		task1.start();
		
		
				Thread task2 = new Thread(new Runnable() {
					
					@Override
					public void run() {
						//CLI:
						//MyObservableCLIView view = new MyObservableCLIView(new BufferedReader(new InputStreamReader(System.in)) , new PrintWriter(System.out));
						//GUI:
						try {
							task1.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						MyObservableGuiView view = new MyObservableGuiView("MAZE GAME", 1200, 650);
					
						
						
						MyObservableModel model = new MyObservableModel();
						Presenter p = new Presenter(model,view);
						
						view.setDebugMode(true);
						p.setDebugMode(true);
						
						model.addObserver(p);
						view.addObserver(p);
						
						view.start();
						
					}
				});
						task2.start();
				
		
	}
}
