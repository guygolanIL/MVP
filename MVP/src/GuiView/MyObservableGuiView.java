package GuiView;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

public class MyObservableGuiView extends ObservableCommonGuiView {

	protected MazeWindow mainWindow;
	
	public MyObservableGuiView(String title, int width, int height) {
		mainWindow = new MazeWindow(title, width, height);
		mainWindow.setKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent key) {
				switch(key.keyCode)
				{
				case SWT.ARROW_UP:
					System.out.println("up key pressed");
					mainWindow.moveUp();
					break;
				case SWT.ARROW_DOWN:
					System.out.println("down key pressed");
					mainWindow.moveDown();
					break;
				case SWT.ARROW_LEFT:
					System.out.println("left key pressed");
					mainWindow.moveLeft();
					break;
				case SWT.ARROW_RIGHT:
					System.out.println("right key pressed");
					mainWindow.moveRight();
					break;
				case SWT.PAGE_UP:
					System.out.println("lvl up key pressed");
					mainWindow.moveLVLUp();
					break;
				case SWT.PAGE_DOWN:
					System.out.println("lvl down key pressed");
					mainWindow.moveLVLDown();
					break;
				}
				
			}
		});
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
				notifyObservers("generate 3d maze guy 29 29 29"); //stub
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
		mainWindow.display(string);

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
