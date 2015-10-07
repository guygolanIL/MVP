package boot;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import GuiView.MyObservableGuiView;
import model.MyObservableModel;
import presenter.Presenter;
import presenter.Properties;

public class Initialize {
	protected Properties prop;
	protected void start()
	{
		StartWindow main =	new StartWindow();
		Thread start = new Thread(main);
		start.start();
		Thread task1 = new Thread(new Runnable() {
		
		@Override
		public void run() {
			try {
				start.join();
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			FileInputStream file;
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
	task1.start();
	
	
	}
}
