package boot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import GuiView.MyObservableGuiView;
import cliView.MyObservableCLIView;
import model.MyObservableModel;
import presenter.Presenter;

public class MVPTest {

	public static void main(String[] args) {
		//CLI:
		//MyObservableCLIView view = new MyObservableCLIView(new BufferedReader(new InputStreamReader(System.in)) , new PrintWriter(System.out));
		//GUI:
		MyObservableGuiView view = new MyObservableGuiView("MAZE GAME", 800, 650);
	
		
		
		MyObservableModel model = new MyObservableModel();
		Presenter p = new Presenter(model,view);
		
		view.setDebugMode(true);
		p.setDebugMode(true);
		
		model.addObserver(p);
		view.addObserver(p);
		
		view.start();
	}
}
