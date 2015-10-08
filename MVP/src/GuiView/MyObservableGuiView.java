package GuiView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import cliView.MyObservableCLIView;
import model.MyObservableModel;
import presenter.Presenter;
import presenter.Properties;
import view.ObservableCommonView;

public class MyObservableGuiView extends ObservableCommonGuiView {

	protected MazeWindow mainWindow;
	
	
	public MyObservableGuiView(String title, int width, int height) {
		super(new Properties());
		mainWindow = new MazeWindow(title, width, height , properties);
		mainWindow.setClueListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("clue "+mainWindow.mazeProperties.getName()+" "+properties.getSolveAlgorithm());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
mainWindow.setSolveListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("solve "+mainWindow.mazeProperties.getName()+" "+properties.getSolveAlgorithm());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
mainWindow.setPropertiesUpdateListener(new SelectionListener() {
	
	@Override
	public void widgetSelected(SelectionEvent arg0) {
	
		setChanged();
		notifyObservers("propertiesUpdate "+mainWindow.getSelectedXMLpropertiesFile());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}
});

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
					if (properties.isDebug() == true)
						System.out.println("up key pressed");
					setChanged();
					notifyObservers("movementRequest UP " + mainWindow.mazeProperties.name);
					break;
				case SWT.ARROW_DOWN:
					if (properties.isDebug() == true)
						System.out.println("down key pressed");
					setChanged();
					notifyObservers("movementRequest DOWN "+ mainWindow.mazeProperties.name);
					break;
				case SWT.ARROW_LEFT:
					if (properties.isDebug() == true)
						System.out.println("left key pressed");
					setChanged();
					notifyObservers("movementRequest LEFT "+ mainWindow.mazeProperties.name);
					break;
				case SWT.ARROW_RIGHT:
					if (properties.isDebug() == true)
						System.out.println("right key pressed");
					setChanged();
					notifyObservers("movementRequest RIGHT "+ mainWindow.mazeProperties.name);
					break;
				case SWT.PAGE_UP:
					if (properties.isDebug() == true)
						System.out.println("lvl up key pressed");
					setChanged();
					notifyObservers("movementRequest LVLUP "+ mainWindow.mazeProperties.name);
					break;
				case SWT.PAGE_DOWN:
					if (properties.isDebug() == true)
						System.out.println("lvl down key pressed");
					setChanged();
					notifyObservers("movementRequest LVLDOWN "+ mainWindow.mazeProperties.name);
					break;
				}
				
			}
		});
		mainWindow.setExitListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent arg0) {
				exitRequest();
				
			}
		});
		
		mainWindow.setGenerateListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Display.getDefault().syncExec(new Runnable() {
				    public void run() {
				    	
				 		
				    	
				    	setChanged();
				    	notifyObservers("generate 3d maze "+ mainWindow.mazeProperties.getName()+" "+ mainWindow.mazeProperties.getxAxis()+" "+ mainWindow.mazeProperties.getyAxis()+" "+ mainWindow.mazeProperties.getzAxis());
				    }
				});
				    
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
	public void display(Solution<Position> solution) {
		mainWindow.setSolution(solution);
		
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
	public void setProperties(presenter.Properties prop) {
		if (properties.getUi()!=prop.getUi())
		{
			setChanged();
			notifyObservers("switchUi");
		}
		else
			this.properties = prop;
		
	}

}
