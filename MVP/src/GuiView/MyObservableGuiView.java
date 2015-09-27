package GuiView;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MyObservableGuiView extends ObservableCommonGuiView {

	protected MazeWindow mainWindow;
	
	public MyObservableGuiView(String title, int width, int height) {
		mainWindow = new MazeWindow(title, width, height);
		mainWindow.setExitListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				exitRequest();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		mainWindow.setGenerateListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("generate 3d maze gui 15 15 15");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public void display(String[] strings) {
		// TODO Auto-generated method stub

	}

	@Override
	public void display(String string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exit() {
		mainWindow.exit();

	}

	@Override
	public void start() {
		mainWindow.run();
	}

	@Override
	public void exitRequest() {
		setChanged();
		notifyObservers("exit");
		
	}

	@Override
	public void display(Maze3d maze) {
		mainWindow.setMazeData(maze);
		
	}

	@Override
	public void display(Position charPosition) {
		mainWindow.setPositionData(charPosition);
		
	}

	@Override
	public void displayError(String string) {
		mainWindow.displayError(string);
		
	}

	@Override
	public void displayCrossSectionByX(int parseInt, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSectionByY(int parseInt, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSectionByZ(int parseInt, String string) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displaySolution(Solution<Position> result) {
		// TODO Auto-generated method stub
		
	}

}
