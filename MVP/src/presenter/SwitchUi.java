package presenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import GuiView.MyObservableGuiView;
import cliView.MyObservableCLIView;
import model.MyObservableModel;
import view.ObservableCommonView;

public class SwitchUi extends CommonCommand {

	public SwitchUi(Presenter presenter) {
		super(presenter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCommand(String param) {
		ObservableCommonView view = null;
		switch (presenter.getProperties().getUi())
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
		

		view.addObserver(presenter);
		presenter.closeView();
		presenter.setView(view);
		view.start();

	}

}
